package hotciv.standard;

import hotciv.framework.*;
import hotciv.utility.Utility;
import hotciv.variants.*;

import java.util.ArrayList;
import java.util.List;


import javax.swing.plaf.nimbus.State;
import java.util.HashMap;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  private Player playerTurn = Player.RED;
  private int age = -4000;
  private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();
  private HashMap<Position, TileImpl> tileHashMap = new HashMap<>();
  private HashMap<Position, UnitImpl> unitHashMap = new HashMap<>();
  private WinningStrategy winnerStrategy;
  private AgeingStrategy ageingStrategy;
  private UnitStrategy unitstrategy;
  private WorldStrategy worldstrategy;
  private AttackStrategy attackStrategy;
  private DieRollStrategy dieRollStrategy;
  private UnitProductionStrategy unitProductionStrategy;
  private MoveStrategy moveStrategy;
  private String[] map = {""};
  private AbstractFactory abstractFactory;
  private ArrayList<GameObserver> observers;


  //Constructor
  public GameImpl(AbstractFactory abstractFactory){
    this.abstractFactory = abstractFactory;
    this.winnerStrategy = abstractFactory.winningStrategy();
    this.ageingStrategy = abstractFactory.ageingStrategy();
    this.unitstrategy = abstractFactory.unitStrategy();
    this.worldstrategy = abstractFactory.worldStrategy();
    this.attackStrategy = abstractFactory.attackStrategy();
    this.unitProductionStrategy = abstractFactory.unitProductionStrategy();
    this.moveStrategy = abstractFactory.moveStrategy();
    cityHashMap=(worldstrategy.citySetup());
    unitHashMap=(worldstrategy.unitSetup());
    tileHashMap=(worldstrategy.tileSetup(map));
    dieRollStrategy = new DieRollStrategyEpsilon();
    moveStrategy.startMoveCount(unitHashMap);

    this.observers = new ArrayList<>();
  }


  public Tile getTileAt( Position p ) {
    return tileHashMap.get(p);
  }

  public Unit getUnitAt( Position p ) { return unitHashMap.get(p); }

  public City getCityAt( Position p ) {
    return cityHashMap.get(p); }

  public Player getPlayerInTurn() { return playerTurn; }

  public Player getWinner() {
    return winnerStrategy.winnerStrategy(this);
  }

  public int getAge() { return age; }

  public boolean moveUnit( Position from, Position to ) {
    int row = from.getRow() - to.getRow();
    int moveCount = getUnitAt(from).getMoveCount();
    int column = from.getColumn() - to.getColumn();

    if (getUnitAt(from).getMoveable()) {
      if (!(Math.abs(row) <= moveCount && Math.abs(column) <= moveCount)) {
        return false;
      }

      if(!moveStrategy.doesTileAllowMove(from, to, this)) {
        return false;
      }

      boolean notThePlayersUnit = !playerTurn.equals(getUnitAt(from).getOwner());
      if (notThePlayersUnit) {
        return false;
      }
      boolean unitAtToNotNull = getUnitAt(to) != null;
      boolean unitOwnerTheSame = unitAtToNotNull && (getUnitAt(from).getOwner().equals(getUnitAt(to).getOwner()));
      if (unitOwnerTheSame) {
        return false;
      }
      boolean unitOwnerIsDifferent = unitAtToNotNull && (!getUnitAt(from).getOwner().equals(getUnitAt(to).getOwner()));
      if (unitOwnerIsDifferent) {
        winnerStrategy.winnerStrategy(this);
        attackStrategy.attack(this, from, to, dieRollStrategy, dieRollStrategy);
        if(attackStrategy.attack(this, from, to, dieRollStrategy, dieRollStrategy)){
          winnerStrategy.incrementBattlesWonBy(getUnitAt(from).getOwner());
        }
      }

      boolean cityToIsDifferentFromUnitOwnerFrom = ((getCityAt(to) != null) && (getUnitAt(from) != null) && !getCityAt(to).getOwner().equals(getUnitAt(from).getOwner()));
      if (cityToIsDifferentFromUnitOwnerFrom) {
        overTakeCity(from, to);
        return true;
      } else {
        makeActualMove(from, to);
        return true;
      }
    } else return false;
  }


  private void overTakeCity(Position from, Position to) {
    String productionType = getCityAt(to).getProduction();
    String workforce = getCityAt(to).getWorkforceFocus();
    Player newCityOwner = getUnitAt(from).getOwner();
    cityHashMap.remove(to);
    cityHashMap.put((to), new CityImpl(newCityOwner, productionType, workforce));
    notifyWorldChange(to);
    makeActualMove(from, to);

  }


  public void makeActualMove(Position from, Position to){
      Player owner = unitHashMap.get(from).getOwner();
      String type = unitHashMap.get(from).getTypeString();
      unitHashMap.remove(from);
      unitHashMap.put((to), new UnitImpl(type, owner));
      Unit movingUnit = unitHashMap.get(to);
      movingUnit.setMoveCount(0);
      notifyWorldChange(from);
      notifyWorldChange(to);
  }

  public void removeUnit(Position p){
    unitHashMap.remove(p);
    notifyWorldChange(p);
  }

  public HashMap getCityHashMap(){
    return cityHashMap;
  }

  public HashMap getUnitHashMap(){
    return unitHashMap;
  }



  @Override
  public void addObserver(GameObserver observer) {
   observers.add(observer);
  }

  @Override
  public void setTileFocus(Position position) {
    notifyTileFocusChange(position);
  }

  public void endOfTurn() {
    switch(playerTurn){
      case RED:
        playerTurn = Player.BLUE;
        notifyTurnEnd();
        break;
      case BLUE:
        playerTurn= Player.RED;
        endOfRound();
    }
  }

  public void endOfRound(){
    winnerStrategy.incrementRoundsPlayed();
    age = ageingStrategy.ageing(age);
    notifyTurnEnd();
    for(City c : cityHashMap.values()){
      c.addTreasury(6);
    }
    moveStrategy.setMoveCount(unitHashMap);

    for(Position p: cityHashMap.keySet()){
      unitProductionStrategy.newUnit(p, this);
      notifyWorldChange(p);
    }

  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {
    Player owner = getCityAt(p).getOwner();
    String unitType = getCityAt(p).getProduction();
    int treasury = getCityAt(p).getTreasury();
    cityHashMap.replace((p), new CityImpl(owner, unitType, balance));
    notifyWorldChange(p);
    getCityAt(p).addTreasury(treasury);
  }

  public void changeProductionInCityAt( Position p, String unitType ) {
    Player owner = getCityAt(p).getOwner();
    String workForce = getCityAt(p).getWorkforceFocus();
    int treasury = getCityAt(p).getTreasury();
    cityHashMap.put((p), new CityImpl(owner, unitType, workForce)) ;
    notifyWorldChange(p);
    getCityAt(p).addTreasury(treasury);
  }

  public void performUnitActionAt( Position p ) {
    if(getUnitAt(p) != null) {
      unitstrategy.performUnitAction(p, cityHashMap, unitHashMap);
      notifyWorldChange(p);
    }
  }

  public void notifyTurnEnd(){
    for (GameObserver g : observers){
      g.turnEnds(getPlayerInTurn(), getAge());
    }
  }

  public void notifyWorldChange(Position position){
    for (GameObserver g : observers){
      g.worldChangedAt(position);
    }
  }

  public void notifyTileFocusChange(Position position){
    for (GameObserver g : observers){
      g.tileFocusChangedAt(position);
    }
  }
}

