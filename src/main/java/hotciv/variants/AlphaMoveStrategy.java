package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class AlphaMoveStrategy implements MoveStrategy {


    @Override
    public void startMoveCount(HashMap<Position, UnitImpl> unitHashMap) {
        for (Unit u : unitHashMap.values()) {
                u.setMoveCount(1);
        }
    }

    @Override
    public boolean doesTileAllowMove(Position from, Position to, Game game) {
        boolean tileEqualsMountains = game.getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS);
        if (tileEqualsMountains) {
            return false;
        }
        boolean tileEqualsOceans = game.getTileAt(to).getTypeString().equals(GameConstants.OCEANS);
        if (tileEqualsOceans) {
            return false;
        }
        return true;
    }

    @Override
    public void setMoveCount(HashMap<Position, UnitImpl> unitHashMap) {
        for(Unit u : unitHashMap.values()){
            u.setMoveCount(1); }
    }

}
