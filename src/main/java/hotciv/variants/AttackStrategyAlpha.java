package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class AttackStrategyAlpha implements AttackStrategy {

    @Override
    public boolean attack(Game game, Position from, Position to, DieRollStrategy attackRollStrategy, DieRollStrategy defenseRollStrategy) {
        game.removeUnit(to);
        return true;
    }
}
