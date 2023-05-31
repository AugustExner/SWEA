package hotciv.visual;
import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.socket.SocketClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotciv.broker.GameProxy;
import hotciv.framework.Game;
import hotciv.view.tool.CompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;



public class HotCivClient {
    Game game;

    public HotCivClient(String hostname) {
        System.out.println("=== HotCiv MANUAL TEST Client (Socket) (host:" + hostname + ") ===");
        ClientRequestHandler crh =
                new SocketClientRequestHandler();
        crh.setServer(hostname, 37123);


        Requestor requestor = new StandardJSONRequestor(crh);
       GameProxy game = new GameProxy(requestor);

        DrawingEditor editor =
                new MiniDrawApplication("Click and/or drag any item to see all game actions",
                        new HotCivFactory4(game));
        editor.open();
        editor.showStatus("Click and drag any item to see Game's proper response.");

        editor.setTool(new CompositionTool(editor, game));
    }

    public static void main(String[] args) {
        new HotCivClient(args[0]);

    }

}
