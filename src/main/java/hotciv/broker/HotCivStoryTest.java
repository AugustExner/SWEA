package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.framework.Game;
import hotciv.framework.Position;

public class HotCivStoryTest {
    Game game;

    public static void main(String[] args) {
        new HotCivStoryTest(args[0]);
    }
    public HotCivStoryTest(String hostname) {
        System.out.println("=== HotCiv MANUAL TEST Client (Socket) (host:" + hostname + ") ===");
        ClientRequestHandler crh =
                new SocketClientRequestHandler();
        crh.setServer(hostname, 37123);

        Requestor requestor = new StandardJSONRequestor(crh);
        game = new GameProxy(requestor);
        simpleTest();
    }



    private void simpleTest() {
        System.out.println("===Testing Simple Methods===");
        System.out.println(" -> Game age              : " + game.getAge());
        System.out.println(" -> Game winner           : " + game.getWinner());
        System.out.println(" -> Game PlayerInTurn     : " + game.getPlayerInTurn());
        System.out.println(" -> Game move             : " + game.moveUnit(new Position(2,2), new Position(2,3)));
        System.out.println(" -> Turn ends");
        game.endOfTurn();
        System.out.println(" -> Game PlayerInTurn     : " + game.getPlayerInTurn());
    }
}
