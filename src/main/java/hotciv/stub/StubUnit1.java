package hotciv.stub;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import java.util.UUID;

public class StubUnit1 implements Unit {
    private int moveCount = 0;
    private int defStrength = 0;
    private String id;


    private boolean moveable = false;

    public StubUnit1(){
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return GameConstants.ARCHER;
    }

    @Override
    public void setMoveCount(int number) {
    moveCount = number;
    }

    @Override
    public Player getOwner() {
        return Player.RED;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return defStrength;
    }

    @Override
    public int getAttackingStrength() {
        return 12;
    }

    @Override
    public void changeDefensiveStrength(int value) {
    defStrength = value;
    }

    @Override
    public void setMoveable(boolean move) {
    moveable = move;
    }

    @Override
    public boolean getMoveable() {
        return moveable;
    }

    @Override
    public String getId() {
        return id;
    }
}
