package hotciv.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WorldStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class AlphaWorldStrategy implements WorldStrategy {
    private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();
    private HashMap<Position, TileImpl> tileHashMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitHashMap = new HashMap<>();

    @Override
    public HashMap tileSetup(String[] map) {
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for(int j = 0; j < GameConstants.WORLDSIZE; j++) {
                tileHashMap.put(new Position(i,j), new TileImpl(GameConstants.PLAINS));
            }
        }
        tileHashMap.put(new Position(1,0), new TileImpl(GameConstants.OCEANS));
        tileHashMap.put(new Position(0,1), new TileImpl(GameConstants.HILLS));
        tileHashMap.put(new Position(2,2), new TileImpl(GameConstants.MOUNTAINS));
        return tileHashMap;
    }

    @Override
    public HashMap citySetup() {
        cityHashMap.put(new Position(1,1), new CityImpl(Player.RED, GameConstants.ARCHER, GameConstants.productionFocus));
        cityHashMap.put(new Position(4,1), new CityImpl(Player.BLUE, GameConstants.SETTLER, GameConstants.productionFocus));
        return cityHashMap;
    }

    @Override
    public HashMap unitSetup() {
        unitHashMap.put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        unitHashMap.put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        unitHashMap.put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return unitHashMap;
    }
}

