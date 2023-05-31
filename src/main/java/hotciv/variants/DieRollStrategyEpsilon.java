package hotciv.variants;

import hotciv.framework.DieRollStrategy;
import java.util.Random;

public class DieRollStrategyEpsilon implements DieRollStrategy {
    @Override
    public int getDieRoll() {
       Random ran = new Random();
       int diceRoll = ran.nextInt(5)+1;
       return diceRoll;
    }
}


