package hotciv.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;

import java.util.HashMap;
import java.util.Map;

/** FakeObject implementation for Game. Base your
 * development of Tools and CivDrawing on this test double,
 * and gradually add EVIDENT TEST = simple code
 * to it, to support your development of all features
 * necessary for a complete CivDrawing and your suite
 * of Tools.
 *
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

public class FakeObjectGame implements Game {

  private Map<Position, Unit> unitMap;
  private Map<Position, City> cityMap;


  public Unit getUnitAt(Position p) {
    return unitMap.get(p);
  }

  public boolean moveUnit(Position from, Position to) {
      int row = from.getRow() - to.getRow();
      int moveCount = getUnitAt(from).getMoveCount();
      int column = from.getColumn() - to.getColumn();
      if ((Math.abs(row) <= moveCount && Math.abs(column) <= moveCount)) {
        // Using print statements to aid in debugging and development
        System.out.println("-- FakeObjectGame / moveUnit called: " + from + "->" + to);
        Unit unit = getUnitAt(from);
        if (unit == null) return false;

        System.out.println("-- moveUnit found unit at: " + from);
        // Remember to inform game observer on any change on the tiles
        unitMap.remove(from);
        gameObserver.worldChangedAt(from);
        unitMap.put(to, unit);
        gameObserver.worldChangedAt(to);
      }
      return true;
  }


  @Override
  public void makeActualMove(Position from, Position to) {

  }

  // === Turn handling ===
  private Player inTurn;
  public void endOfTurn() {
    System.out.println( "-- FakeObjectGame / endOfTurn called." );
    inTurn = (getPlayerInTurn() == Player.RED ?
              Player.BLUE : 
              Player.RED );
    // no age increments implemented...
    gameObserver.turnEnds(inTurn, -4000);
  }

  public Player getPlayerInTurn() { return inTurn; }

  // === Observer handling ===
  protected GameObserver gameObserver;
  // observer list is fake code, only having a single
  // one, suffices for development.
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  } 

  public FakeObjectGame() {
    defineWorld();
    // Put some units into play
    unitMap = new HashMap<>();
    cityMap = new HashMap<>();
    cityMap.put(new Position(1,0), new StubCity());
    unitMap.put(new Position(2,0), new StubUnit( GameConstants.ARCHER, Player.RED ));
    unitMap.put(new Position(3,2), new StubUnit( GameConstants.LEGION, Player.BLUE ));
    unitMap.put(new Position(4,2), new StubUnit( GameConstants.SETTLER, Player.RED ));
    unitMap.put(new Position(6,3), new StubUnit( ThetaConstants.SANDWORM, Player.RED ));
    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  protected Map<Position,Tile> world; 
  public Tile getTileAt( Position p ) { return world.get(p); }

  /** define the world.
   */
  protected void defineWorld() {
    world = new HashMap<Position,Tile>();
    for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
      for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
        Position p = new Position(r,c);
        world.put( p, new StubTile(GameConstants.PLAINS));
      }
    }
    // Create a little area around the theta unit of special terrain
    world.put(new Position(5,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,2), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,3), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(6,5), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,3), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,4), new StubTile(ThetaConstants.DESERT));
    world.put(new Position(7,5), new StubTile(ThetaConstants.DESERT));
  }

  // TODO: Add more fake object behaviour to test MiniDraw updating

  public City getCityAt( Position p ) { return cityMap.get(p); }


  public Player getWinner() { return null; }
  public int getAge() { return 0; }

  @Override
  public HashMap getCityHashMap() {
    return null;
  }

  @Override
  public void removeUnit(Position p) {

  }

  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}

  public void performUnitActionAt( Position p ) {
    System.out.println("Unit action is invoked at " + p);
  }

  @Override
  public HashMap getUnitHashMap() {
    return null;
  }

  public void setTileFocus(Position position) {
    // TODO: setTileFocus implementation pending.
    gameObserver.tileFocusChangedAt(position);
    System.out.println("-- FakeObjectGame / setTileFocus called.");
    System.out.println(" *** IMPLEMENTATION PENDING ***");
  }
}

