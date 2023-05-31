package hotciv.broker;

import frds.broker.ipc.socket.SocketServerRequestHandler;
import hotciv.framework.AbstractFactory;
import hotciv.framework.Game;
import hotciv.standard.AbstractFactories.SemiCivFactory;
import hotciv.standard.GameImpl;
import hotciv.stub.StubGame;


public class HotCivServer {
    private AbstractFactory abstractFactory = new SemiCivFactory();
    public static void main(String[] args) throws Exception {
        new HotCivServer(args[0]); // No error handling!
    }

    public HotCivServer(String string) {
        int port = 37123;
        Game game = new GameImpl(abstractFactory);
        HotCivRootInvoker invoker = new HotCivRootInvoker(game);

        // Configure a socket based server request handler
        SocketServerRequestHandler srh =
                new SocketServerRequestHandler();
        srh.setPortAndInvoker(port, invoker);


        // Welcome
        System.out.println("=== HotCiv Socket based Server Request Handler (port:"
                + port + ") ===");
        System.out.println(" Use ctrl-c to terminate!");
        System.out.println(" IP-address:" + "localhost");
        srh.start();
    }
}


