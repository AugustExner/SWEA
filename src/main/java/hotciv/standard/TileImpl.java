package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Tile;

import java.util.UUID;

public class TileImpl implements Tile {
    private String type;
    private String id;

    //Constructor
    public TileImpl(String type){
        this.type = type;
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public String getId() {
        return id;
    }
}
