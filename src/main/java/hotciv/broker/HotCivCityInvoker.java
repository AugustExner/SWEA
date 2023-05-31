package hotciv.broker;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.framework.*;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

public class HotCivCityInvoker implements Invoker {
    private NameService nameService;
    private Gson gson;

    public HotCivCityInvoker(NameService nameService) {
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
        City city = nameService.getCity(id);
        System.out.println(city);
            if (requestObject.getOperationName().equals(OperationNames.GET_CITY_OWNER)) {
                Player owner = city.getOwner();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(owner));

            } else if (requestObject.getOperationName().equals(OperationNames.GET_SIZE)) {
                int size = city.getSize();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(size));

            } else if (requestObject.getOperationName().equals(OperationNames.GET_TREASURY)) {
                int treasury = city.getTreasury();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(treasury));

            } else if (requestObject.getOperationName().equals(OperationNames.GET_PRODUCTION)) {
                String production = city.getProduction();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(production));

            } else if (requestObject.getOperationName().equals(OperationNames.GET_WORKFORCEFOCUS)) {
                String workforce = city.getWorkforceFocus();
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(workforce));

            } else if (requestObject.getOperationName().equals(OperationNames.MINUS_TREASURY)) {
                city.minusTreasury(5);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");

            } else if (requestObject.getOperationName().equals(OperationNames.ADD_TREASURY)) {
                city.addTreasury(5);
                reply = new ReplyObject(HttpServletResponse.SC_CREATED, "");

            } else {
                reply = new ReplyObject(HttpServletResponse.SC_NOT_IMPLEMENTED, "Unknown operation");

            }
            return gson.toJson(reply);

    }
}
