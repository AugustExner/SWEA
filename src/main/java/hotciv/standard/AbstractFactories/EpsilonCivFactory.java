package hotciv.standard.AbstractFactories;

import hotciv.framework.*;
import hotciv.variants.*;

public class EpsilonCivFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {
        return new AlphaUnitStrategy();
    }

    @Override
    public WinningStrategy winningStrategy() { return new WinnerStrategyEpsilonCiv(); }

    @Override
    public WorldStrategy worldStrategy() {
        return new AlphaWorldStrategy();
    }

    @Override
    public AgeingStrategy   ageingStrategy() {
        return new WorldAgeingAlpha();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new AttackStrategyEpsilon();
    }

    @Override
    public UnitProductionStrategy unitProductionStrategy() { return new AlphaUnitProductionStrategy(); }

    @Override
    public MoveStrategy moveStrategy() { return new AlphaMoveStrategy(); }

}
