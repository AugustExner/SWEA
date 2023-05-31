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

public class HotCivUnitInvoker implements Invoker {
    private Gson gson;
    private Position from = new Position(2,2);
    private Position to = new Position(2,3);
    private NameService nameService;

    public HotCivUnitInvoker(NameService nameService) {
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
        Unit unit = nameService.getUnit(id);

        if(requestObject.getOperationName().equals(OperationNames.GET_TYPE_STRING)){
            String getTypeString = unit.getTypeString();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(getTypeString));
        } else if(requestObject.getOperationName().equals(OperationNames.SET_MOVE_COUNT)){
           unit.setMoveCount(1);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED,"");
        } else if(requestObject.getOperationName().equals(OperationNames.GET_OWNER)){
            Player owner = unit.getOwner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(owner));
        } else if(requestObject.getOperationName().equals(OperationNames.GET_MOVE_COUNT)){
            int getMoveCount = unit.getMoveCount();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(getMoveCount));
        } else if(requestObject.getOperationName().equals(OperationNames.GET_DEFENSIVE_STRENGTH)){
            int defStrength = unit.getDefensiveStrength();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(defStrength));
        } else if(requestObject.getOperationName().equals(OperationNames.GET_ATTACKING_STRENGTH)){
            int attackingStrength = unit.getAttackingStrength();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(attackingStrength));
        } else if(requestObject.getOperationName().equals(OperationNames.CHANGE_DEFENSIVE_STRENGTH)){
            unit.changeDefensiveStrength(3);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");
        } else if(requestObject.getOperationName().equals(OperationNames.GET_MOVEABLE)){
            boolean getMoveAble = unit.getMoveable();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(getMoveAble));
        } else if(requestObject.getOperationName().equals(OperationNames.SET_MOVE_ABLE)){
            unit.setMoveable(true);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");
        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED, "Unknown operation");
        }
        return gson.toJson(reply);
    }

}
