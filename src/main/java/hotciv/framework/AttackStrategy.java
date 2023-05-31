package hotciv.framework;

import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface AttackStrategy {

    public boolean attack(Game game, Position from, Position to,DieRollStrategy attackRollStrategy, DieRollStrategy defenseRollStrategy);
}
