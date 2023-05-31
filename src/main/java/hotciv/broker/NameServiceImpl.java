package hotciv.broker;

import hotciv.framework.City;
import hotciv.framework.NameService;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import java.util.HashMap;

import java.util.Map;

public class NameServiceImpl implements NameService {
    private Map<String, Unit> units;
    private Map<String, City> cities;
    private Map<String, Tile> tiles;


    public NameServiceImpl(){
        units = new HashMap<>();
        cities = new HashMap<>();
        tiles = new HashMap<>();
    }
    @Override
    public void putUnit(String objectId, Unit unit) {
        units.put(objectId, unit);
    }

    @Override
    public Unit getUnit(String objectId) {
        return units.get(objectId);
    }

    @Override
    public void putCity(String objectId, City city) {
        cities.put(objectId, city);
    }

    @Override
    public City getCity(String objectId) {
        return cities.get(objectId);
    }

    @Override
    public void putTile(String objectId, Tile tile) {
        tiles.put(objectId, tile);
    }

    @Override
    public Tile getTile(String objectId) {
        return tiles.get(objectId);
    }
}
