package hotciv.framework;

import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface MoveStrategy {

    void startMoveCount(HashMap<Position, UnitImpl> unitHashMap);

    boolean doesTileAllowMove(Position from, Position to, Game game);

    void setMoveCount(HashMap<Position, UnitImpl> unitHashMap);
}
