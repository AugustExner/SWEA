package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility2;

import java.util.HashMap;

public class AttackStrategyEpsilon implements AttackStrategy {
    private GameImpl game;
    private DieRollStrategy attackDie;
    private DieRollStrategy defenseDie;

    @Override
    public boolean attack(Game game, Position from, Position to, DieRollStrategy attackRollStrategy, DieRollStrategy defenseRollStrategy) {
        int attackStrength = game.getUnitAt(from).getAttackingStrength();
        int denfenseStrength = game.getUnitAt(to).getDefensiveStrength();
        int terrainAttackStrength = Utility2.getTerrainFactor(game, from);
        int terrainDefenseStrength = Utility2.getTerrainFactor(game, to);
        int friendlyAttackStrength = Utility2.getFriendlySupport(game, from, game.getUnitAt(from).getOwner());
        int friendlyDefenseStrength = Utility2.getFriendlySupport(game, to, game.getUnitAt(to).getOwner());



        int totalAttackStrength = (attackStrength + friendlyAttackStrength) * terrainAttackStrength;
        int totalDefenseStrength = (denfenseStrength + friendlyDefenseStrength) * terrainDefenseStrength;

        int attackRoll = attackRollStrategy.getDieRoll();
        int defenseRoll = defenseRollStrategy.getDieRoll();

        if((totalAttackStrength * attackRoll) > (totalDefenseStrength * defenseRoll)){
            game.removeUnit(to);
            game.moveUnit(from, to);
            return true;
        } else
            game.removeUnit(from);
            return false;
    }
}
