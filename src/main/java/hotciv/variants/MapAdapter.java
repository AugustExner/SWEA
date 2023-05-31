package hotciv.variants;
import hotciv.framework.*;
import hotciv.framework.Position;
import hotciv.framework.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

public class MapAdapter implements WorldStrategy {
    private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();
    private HashMap<Position, TileImpl> tileHashMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitHashMap = new HashMap<>();
    private WorldStrategy worldStrategy;
    private ThirdPartyFractalGenerator thirdPartyFractalGenerator;

    public MapAdapter() {
        thirdPartyFractalGenerator = new ThirdPartyFractalGenerator();
        worldStrategy = new DeltaWorldStrategy();
        cityHashMap = new HashMap<>();
        unitHashMap = new HashMap<>();
        tileHashMap = new HashMap<>();
    }

    @Override
    public HashMap tileSetup(String[] map) {
        HashMap<Position,TileImpl> theWorld = new HashMap<Position,TileImpl>();
        thirdPartyFractalGenerator = new ThirdPartyFractalGenerator();
        for(int r = 0; r < GameConstants.WORLDSIZE; r++){
            for(int c = 0; c < GameConstants.WORLDSIZE; c++){
                char tile = thirdPartyFractalGenerator.getLandscapeAt(r,c);
                String type = "error";
                if ( tile == '.' ) { type = GameConstants.OCEANS; }
                if ( tile == 'o' ) { type = GameConstants.PLAINS; }
                if ( tile == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tile == 'f' ) { type = GameConstants.FOREST; }
                if ( tile == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                theWorld.put(p, new TileImpl(type));
            }
        }
        return theWorld;
    }

    @Override
    public HashMap citySetup() {
        return cityHashMap;
    }

    @Override
    public HashMap unitSetup() {
        return unitHashMap;
    }
}
