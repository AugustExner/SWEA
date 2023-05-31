package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.OperationNames;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitProxy implements Unit, ClientProxy {
    private final Requestor requestor;
    private final String objectID;

    public UnitProxy(Requestor requestor, String objectID) {
        this.requestor = requestor;
        this.objectID = objectID;
    }
    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_TYPE_STRING, String.class);
    }

    @Override
    public void setMoveCount(int number) {
        requestor.sendRequestAndAwaitReply(getId(), OperationNames.SET_MOVE_COUNT, void.class);
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_OWNER, Player.class);
    }

    @Override
    public int getMoveCount() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_MOVE_COUNT, int.class);
    }

    @Override
    public int getDefensiveStrength() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_DEFENSIVE_STRENGTH, int.class);
    }

    @Override
    public int getAttackingStrength() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_ATTACKING_STRENGTH, int.class);
    }

    @Override
    public void changeDefensiveStrength(int value) {
        requestor.sendRequestAndAwaitReply(getId(), OperationNames.CHANGE_DEFENSIVE_STRENGTH, void.class);
    }

    @Override
    public void setMoveable(boolean move) {
        requestor.sendRequestAndAwaitReply(getId(), OperationNames.SET_MOVE_ABLE, void.class);
    }

    @Override
    public boolean getMoveable() {
       return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_MOVEABLE, boolean.class);
    }

    @Override
    public String getId() {
        return objectID;
    }
}
