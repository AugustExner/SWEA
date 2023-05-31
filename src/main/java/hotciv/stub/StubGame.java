package hotciv.stub;

import frds.broker.Servant;
import hotciv.framework.*;

import java.util.HashMap;

public class StubGame implements Game, Servant {
    private Player playerInTurn = Player.YELLOW;
    private int age = 42;
    @Override
    public Tile getTileAt(Position p) {
        Position tilePosition = new Position(3,3);
        if(p.equals(tilePosition)){
            return new StubTile();
        }
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        Position unitPosition = new Position(4,4);
        if(p.equals(unitPosition)) {
            return new StubUnit1();
        }
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        Position cityPosition = new Position (6,6);
        if(p.equals(cityPosition)) {
            return new StubCity();
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    @Override
    public Player getWinner() {
        return Player.GREEN;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public HashMap getCityHashMap() {
        return null;
    }

    @Override
    public void removeUnit(Position p) {

    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return true;
    }

    @Override
    public void makeActualMove(Position from, Position to) {

    }

    @Override
    public void endOfTurn() {
    playerInTurn = Player.GREEN;
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    age = 308;
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
    age = 221046;
    }

    @Override
    public void performUnitActionAt(Position p) {
    age = 3232199;
    }

    @Override
    public HashMap getUnitHashMap() {
        return null;
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
