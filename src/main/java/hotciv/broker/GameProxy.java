package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;

import java.util.HashMap;

public class GameProxy implements Game, ClientProxy {
    private final Requestor requestor;
    private GameObserver gameObserver;

    public GameProxy(Requestor requestor) {
        this.requestor = requestor;
    }

    @Override
    public Tile getTileAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply("", OperationNames.GET_TILE_AT, String.class, p);
        if(id != null) {

            return new TileProxy(requestor, id);
        }
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply("", OperationNames.GET_UNIT_AT, String.class, p);
        if(id != null) {
            Unit proxy = new UnitProxy(requestor, id);
            return proxy;
        }
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        String id = requestor.sendRequestAndAwaitReply("", OperationNames.GET_CITY_AT, String.class, p);
        if(id != null){
            City proxy = new CityProxy(requestor,id);
            return proxy;
        }
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return requestor.sendRequestAndAwaitReply("", OperationNames.GET_PLAYER_IN_TURN, Player.class);
    }

    @Override
    public Player getWinner() {
        return requestor.sendRequestAndAwaitReply("", OperationNames.GET_WINNER, Player.class);

    }

    @Override
    public int getAge() {
        return requestor.sendRequestAndAwaitReply("",OperationNames.AGE, Integer.class);
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
        notifyWorldChanges(from);
        notifyWorldChanges(to);
        return requestor.sendRequestAndAwaitReply("", OperationNames.MOVE_UNIT, Boolean.class, from, to);
    }

    @Override
    public void makeActualMove(Position from, Position to) {

    }

    @Override
    public void endOfTurn() {
         requestor.sendRequestAndAwaitReply("", OperationNames.END_OF_TURN, Void.class);
        notifyTurnEnds();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        requestor.sendRequestAndAwaitReply("", OperationNames.CHANGE_WORKFORCE_FOCUS, Void.class, p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        requestor.sendRequestAndAwaitReply("", OperationNames.CHANGE_PRODUCTION_FOCUS, Void.class, p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        requestor.sendRequestAndAwaitReply("", OperationNames.PERFORM_UNITACTION, Void.class,p);
        notifyWorldChanges(p);
    }

    @Override
    public HashMap getUnitHashMap() {
        return null;
    }

    @Override
    public void addObserver(GameObserver observer) {
        gameObserver = observer;

    }

    public void notifyWorldChanges(Position position) {
        gameObserver.worldChangedAt(position);
    }
    public void notifyChangeOfTileFocus(Position position) {
        gameObserver.tileFocusChangedAt(position);
    }
    public void notifyTurnEnds() {
        gameObserver.turnEnds(getPlayerInTurn(), getAge());
    }



    @Override
    public void setTileFocus(Position position) {
        notifyChangeOfTileFocus(position);
    }
}
