package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ClientProxy;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;




public class TileInvoker implements Invoker {

    private NameService nameService;
    private Gson gson;
    private Position from = new Position(2,2);


    public TileInvoker(NameService nameService) {
    this.nameService = nameService;
    gson = new Gson();
    }

    @Override
    public String handleRequest(String request) {
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        JsonArray array =
                JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply;
        String id = requestObject.getObjectId();
        Tile tile = nameService.getTile(id);

        if(requestObject.getOperationName().equals(OperationNames.GET_TILE_TYPE_STRING)){
            String tileTypeString = tile.getTypeString();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(tileTypeString));
        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED, "Unknown operation");
        }
        return gson.toJson(reply);
    }
}
