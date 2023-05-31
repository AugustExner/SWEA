package hotciv.variants;

import hotciv.framework.DieRollStrategy;

public class StubForDieRoll implements DieRollStrategy {
    int currentDie;
    public void setDieRoll(int die){
        currentDie = die;
    }

    @Override
    public int getDieRoll() {
        return currentDie;
    }
}
