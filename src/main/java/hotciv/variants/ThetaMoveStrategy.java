package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class ThetaMoveStrategy implements MoveStrategy {
    MoveStrategy alphaState = new AlphaMoveStrategy();

    @Override
    public void startMoveCount(HashMap<Position, UnitImpl> unitHashMap) {
        for (Unit u : unitHashMap.values()) {
            if (u.getTypeString().equals(GameConstants.SANDWORM)) {
                u.setMoveCount(2);
            } else alphaState.startMoveCount(unitHashMap);
        }
    }

    @Override
    public boolean doesTileAllowMove(Position from, Position to, Game game) {
        if(game.getUnitAt(from).getTypeString().equals(GameConstants.SANDWORM)){
            boolean tileEqualsDessert = game.getTileAt(to).getTypeString().equals(GameConstants.DESSERT);
            if (tileEqualsDessert) {
                return true;
            } else return false;
        } else return alphaState.doesTileAllowMove(from, to, game);
    }

    @Override
    public void setMoveCount(HashMap<Position, UnitImpl> unitHashMap) {
        for (Unit u : unitHashMap.values()) {
            if (u.getTypeString().equals(GameConstants.SANDWORM)) {
                u.setMoveCount(2);
            } else alphaState.setMoveCount(unitHashMap);
        }
    }

}
