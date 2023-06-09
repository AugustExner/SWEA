package hotciv.stub;

import hotciv.framework.Player;
import hotciv.framework.Unit;

public class StubUnit implements Unit {
    private String type;
    private Player owner;

    public StubUnit(String type, Player owner) {
        this.type = type;
        this.owner = owner;
    }

    public String getTypeString() {
        return type;
    }

    @Override
    public void setMoveCount(int number) {

    }

    public Player getOwner() {
        return owner;
    }

    public int getMoveCount() {
        return 1;
    }

    public int getDefensiveStrength() {
        return 0;
    }

    public int getAttackingStrength() {
        return 0;
    }

    @Override
    public void changeDefensiveStrength(int value) {

    }

    @Override
    public void setMoveable(boolean move) {

    }

    @Override
    public boolean getMoveable() {
        return true;
    }

    @Override
    public String getId() {
        return null;
    }
}
