package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.ClientProxy;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;
import hotciv.standard.TileImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class HotCivGameInvoker implements Invoker {
    private Gson gson;
    private final Game servant;
    private Position from = new Position(2,2);
    private Position to = new Position(2,3);
    private NameService nameService;


    public HotCivGameInvoker(Game servant, NameService nameService) {
        this.servant = servant;
        this.nameService =  nameService;
        gson = new Gson();
    }

    @Override
    public String handleRequest( String request) {
        Game game = servant;
        RequestObject requestObject =
                gson.fromJson(request, RequestObject.class);
        JsonArray array =
                JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

        ReplyObject reply =null;
try {


    if (requestObject.getOperationName().equals(OperationNames.GET_PLAYER_IN_TURN)) {
        Player playerTurn = servant.getPlayerInTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(playerTurn));

    } else if (requestObject.getOperationName().equals(OperationNames.GET_WINNER)) {
        Player winner = servant.getWinner();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(winner));

    } else if (requestObject.getOperationName().equals(OperationNames.AGE)) {
        int age = servant.getAge();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(age));

    } else if (requestObject.getOperationName().equals(OperationNames.MOVE_UNIT)) {
        Boolean moveUnit = servant.moveUnit(from, to);
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(moveUnit));

    } else if (requestObject.getOperationName().equals(OperationNames.END_OF_TURN)) {
        servant.endOfTurn();
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");

    } else if (requestObject.getOperationName().equals(OperationNames.CHANGE_WORKFORCE_FOCUS)) {
        servant.changeWorkForceFocusInCityAt(from, GameConstants.foodFocus);
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");

    } else if (requestObject.getOperationName().equals(OperationNames.CHANGE_PRODUCTION_FOCUS)) {
        servant.changeProductionInCityAt(from, GameConstants.ARCHER);
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");

    } else if (requestObject.getOperationName().equals(OperationNames.PERFORM_UNITACTION)) {
        servant.performUnitActionAt(from);
        reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");

    } else if (requestObject.getOperationName().equals(OperationNames.GET_TILE_AT)) {
        Position p = gson.fromJson(array.get(0), Position.class);
        Tile tile = game.getTileAt(p);
        if (tile != null) {
            nameService.putTile(tile.getId(), tile);
            String id = tile.getId();

            reply = new ReplyObject(HttpServletResponse.SC_OK, gson.toJson(id));
        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NO_CONTENT, null);
        }


    } else if (requestObject.getOperationName().equals(OperationNames.GET_UNIT_AT)) {
        Position p = gson.fromJson(array.get(0), Position.class);
        Unit unit = game.getUnitAt(p);
        if (unit != null) {
            nameService.putUnit(unit.getId(), unit);
            String id = unit.getId();

            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NO_CONTENT, null);
        }


    } else if (requestObject.getOperationName().equals(OperationNames.GET_CITY_AT)) {
        Position p = gson.fromJson(array.get(0), Position.class);
        City city = game.getCityAt(p);
        if (city != null) {
            String id = city.getId();
            nameService.putCity(id, city);
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(id));
        } else {
            reply = new ReplyObject(HttpServletResponse.SC_NO_CONTENT, null);
        }

    } else {
        reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED, "Unknown operation");

    }
} catch (UnknownServantException e) {
    reply = new ReplyObject(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
}
        return gson.toJson(reply);
    }


}
