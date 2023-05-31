package hotciv.standard.AbstractFactories;

import hotciv.framework.*;
import hotciv.variants.*;

public class BetaCivFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {
        return new AlphaUnitStrategy();
    }

    @Override
    public WinningStrategy winningStrategy() {
        return new WinnerStrategyBetaCiv();
    }

    @Override
    public WorldStrategy worldStrategy() {
        return new AlphaWorldStrategy();
    }

    @Override
    public AgeingStrategy ageingStrategy() {
        return new WorldAgeingBeta();
    }

    @Override
    public AttackStrategy attackStrategy() { return new AttackStrategyAlpha(); }

    @Override
    public UnitProductionStrategy unitProductionStrategy() { return new AlphaUnitProductionStrategy(); }

    @Override
    public MoveStrategy moveStrategy() { return new AlphaMoveStrategy(); }

}
