package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.Game;
import hotciv.framework.NameService;
import hotciv.framework.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HotCivRootInvoker implements Invoker {
    private final NameService nameService;
    private final Map<String, Invoker> invokerMap;
    private final Game servant;
    private Gson gson;

    public HotCivRootInvoker(Game servant) {
        gson = new Gson();
        this.servant = servant;
        nameService = new NameServiceImpl();
        invokerMap = new HashMap<>();



        Invoker gameInvoker = new HotCivGameInvoker(servant, nameService);
        invokerMap.put(OperationNames.GAME_PREFIX, gameInvoker);
        Invoker unitInvoker = new HotCivUnitInvoker(nameService);
        invokerMap.put(OperationNames.UNIT_PREFIX, unitInvoker);
        Invoker cityInvoker = new HotCivCityInvoker(nameService);
        invokerMap.put(OperationNames.CITY_PREFIX, cityInvoker);
        Invoker tileInvoker = new TileInvoker(nameService);
        invokerMap.put(OperationNames.TILE_PREFIX, tileInvoker);
    }


    @Override
    public String handleRequest(String request) {

        RequestObject requestObject = gson.fromJson(request,RequestObject.class);
        String operationName = requestObject.getOperationName();

        String type = operationName.substring(0, operationName.indexOf(OperationNames.SEPARATOR));
        Invoker subInvoker = invokerMap.get(type);

        String reply;
        try {
            reply = subInvoker.handleRequest(request);

        } catch (UnknownServantException e) {
            reply = gson.toJson(
                    new ReplyObject(
                            HttpServletResponse.SC_NOT_FOUND,
                            e.getMessage()));
        }

        return reply;
    }

}

