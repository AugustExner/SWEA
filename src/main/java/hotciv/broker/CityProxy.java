package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.OperationNames;
import hotciv.framework.Player;

public class CityProxy implements City, ClientProxy {

    private final Requestor requestor;
    private final String objectID;

    public CityProxy(Requestor requestor, String objectID) {
        this.requestor = requestor;
        this.objectID = objectID;
    }

    @Override
    public Player getOwner() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_CITY_OWNER, Player.class);
    }

    @Override
    public int getSize() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_SIZE, Integer.class);
    }

    @Override
    public int getTreasury() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_TREASURY, Integer.class);
    }

    @Override
    public String getProduction() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_PRODUCTION, String.class);
    }

    @Override
    public String getWorkforceFocus() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_WORKFORCEFOCUS, String.class);
    }

    @Override
    public void minusTreasury(int amount) {
        requestor.sendRequestAndAwaitReply(getId(), OperationNames.MINUS_TREASURY, Void.class);
    }

    @Override
    public void addTreasury(int amount) {
        requestor.sendRequestAndAwaitReply(getId(), OperationNames.ADD_TREASURY, Void.class);
    }

    @Override
    public String getId() {
        return objectID;
    }
}
