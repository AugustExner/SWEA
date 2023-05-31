package hotciv.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class ThetaWorldStrategy implements WorldStrategy {
    private String[] layout;
    private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitHashMap = new HashMap<>();
    @Override
    public HashMap tileSetup(String[] map) {
        layout = new String[]{
                "...oododdoo.....",
                "..ohhdddddddddd.",
                ".oddddMddd...dd.",
                ".odMMddododdddoo",
                "...ofodddddddo..",
                ".ofddddoddddddo.",
                "...odd..dddddd..",
                ".oddddddddddoM..",
                ".oddddddddddddf.",
                "offddddoddddddoo",
                "oodddodo...oddoo",
                ".ooMMdddd...ddd.",
                "..oodddddfoddd..",
                "....oddddMMdo...",
                "..ooddddddMd....",
                ".....oddddddoo..",
        };
        HashMap<Position, TileImpl> theWorld = new HashMap<Position,TileImpl>();
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                if ( tileChar == 'd' ) { type = GameConstants.DESSERT; }
                Position p = new Position(r,c);
                theWorld.put(p, new TileImpl(type));
            }
        }
        return theWorld;
    }

    @Override
    public HashMap citySetup() {
        cityHashMap.put(new Position(8, 12), new CityImpl(Player.RED, GameConstants.ARCHER, GameConstants.productionFocus));
        cityHashMap.put(new Position(4, 5), new CityImpl(Player.BLUE, GameConstants.SETTLER, GameConstants.productionFocus));
        return cityHashMap;
    }

    @Override
    public HashMap unitSetup() {
        unitHashMap.put(new Position(3, 8), new UnitImpl(GameConstants.ARCHER, Player.RED));
        unitHashMap.put(new Position(4, 4), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        unitHashMap.put(new Position(5, 5), new UnitImpl(GameConstants.SETTLER, Player.RED));
        unitHashMap.put(new Position(9, 6), new UnitImpl(GameConstants.SANDWORM, Player.BLUE));
        return unitHashMap;
    }
}
