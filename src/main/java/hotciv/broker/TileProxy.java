package hotciv.broker;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotciv.framework.*;


public class TileProxy implements Tile, ClientProxy {
    private final Requestor requestor;
    private final String objectID;

    public TileProxy(Requestor requestor, String objectID) {
        this.requestor = requestor;
        this.objectID = objectID;
    }


    @Override
    public String getTypeString() {
        return requestor.sendRequestAndAwaitReply(getId(), OperationNames.GET_TILE_TYPE_STRING,String.class);
    }

    @Override
    public String getId() {
        return objectID;
    }
}
