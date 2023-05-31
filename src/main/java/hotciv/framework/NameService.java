package hotciv.framework;

public interface NameService {
        void putUnit(String objectId, Unit unit);
        Unit getUnit(String objectId);

        void putCity(String objectId, City city);
        City getCity(String objectId);

        void putTile(String objectId, Tile tile);
        Tile getTile(String objectId);
}



