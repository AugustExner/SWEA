package hotciv.variants;

import hotciv.framework.WorldStrategy;

import java.util.HashMap;
import java.util.*;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public class DeltaWorldStrategy implements WorldStrategy {
    private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitHashMap = new HashMap<>();
    private String[] layout;

    @Override
    public HashMap tileSetup(String[] map) {
        String[] validLetters = {".", "o", "M", "f", "h"};
        if(map.length == 16){
            for(int i=0;i<map.length;i++){
                if(map[i].length()==16){
                    if( map[i].equals(validLetters)){
                        layout = map;
                    }
                }
            }
        } else {
            layout =
                    new String[]{
                            "...ooMooooo.....",
                            "..ohhoooofffoo..",
                            ".oooooMooo...oo.",
                            ".ooMMMoooo..oooo",
                            "...ofooohhoooo..",
                            ".ofoofooooohhoo.",
                            "...ooo..........",
                            ".ooooo.ooohooM..",
                            ".ooooo.oohooof..",
                            "offfoooo.offoooo",
                            "oooooooo...ooooo",
                            ".ooMMMoooo......",
                            "..ooooooffoooo..",
                            "....ooooooooo...",
                            "..ooohhoo.......",
                            ".....ooooooooo..",
                    };
        }
        HashMap<Position,TileImpl> theWorld = new HashMap<Position,TileImpl>();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
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
            return unitHashMap;
    }

}
