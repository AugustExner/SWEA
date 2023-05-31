package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

import java.util.UUID;

public class UnitImpl implements Unit {

    private String type;
    private Player owner;
    private int move;
    private int archerDefStrength = 3;
    private int archerAttackStrength = 2;
    private int settlerDefStrength = 3;
    private int settlerAttackStrength = 0;
    private int legionDefStrength = 2;
    private int legionAttackStrength = 4;
    private int sandwormAttackStrength = 0;
    private int sandwormDefenseStrength = 10;
    private boolean moveUnit = true;
    private int archerCost = 10;
    private int legionCost = 15;
    private int settlerCost = 30;
    private String id;


    //Constructor
    public UnitImpl(String type, Player owner) {
        this.type = type;
        this.owner = owner;
        id = UUID.randomUUID().toString();
    }

    @Override
    public String getTypeString() {
        return type;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return move;
    }

    @Override
    public int getDefensiveStrength() {
        if (getTypeString().equals(GameConstants.ARCHER)) {
            return archerDefStrength;
        } else if (getTypeString().equals(GameConstants.LEGION)) {
            return legionDefStrength;
        } else if (getTypeString().equals(GameConstants.SETTLER)) {
            return settlerDefStrength;
        } else if(getTypeString().equals(GameConstants.SANDWORM)){
            return sandwormDefenseStrength;
        } else {
            return 0;
        }
    }

    @Override
    public int getAttackingStrength() {
        if (getTypeString().equals(GameConstants.ARCHER)) {
            return archerAttackStrength;
        } else if (getTypeString().equals(GameConstants.LEGION)) {
            return legionAttackStrength;
        } else if (getTypeString().equals(GameConstants.SETTLER)) {
            return settlerAttackStrength;
        } else if(getTypeString().equals(GameConstants.SANDWORM)){
            return sandwormAttackStrength;
        } else {
            return 0;
        }
    }

    @Override
    public void setMoveCount(int number) {
        move = number;
    }

    public void changeDefensiveStrength(int value) {
        if (getTypeString().equals(GameConstants.ARCHER)) {
            archerDefStrength = value;
        } else if (getTypeString().equals(GameConstants.LEGION)) {
            legionDefStrength = value;
        } else if (getTypeString().equals(GameConstants.SETTLER)) {
            settlerDefStrength = value;
        }
    }

    public void setMoveable(boolean move){
        moveUnit = move;
    }

    public boolean getMoveable(){
        return moveUnit;
    }

    @Override
    public String getId() {
        return id;
    }


}
