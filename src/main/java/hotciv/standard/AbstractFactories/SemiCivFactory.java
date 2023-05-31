package hotciv.standard.AbstractFactories;

import hotciv.framework.*;
import hotciv.variants.*;

public class SemiCivFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {return new GammaUnitStrategy(); }

    @Override
    public AttackStrategy attackStrategy() {
        return new AttackStrategyEpsilon();
    }

    @Override
    public WorldStrategy worldStrategy() {
        return new DeltaWorldStrategy();
    }

    @Override
    public WinningStrategy winningStrategy() {
        return new WinnerStrategyEpsilonCiv();
    }

    @Override
    public AgeingStrategy ageingStrategy() {
        return new WorldAgeingBeta();
    }

    @Override
    public UnitProductionStrategy unitProductionStrategy() { return new AlphaUnitProductionStrategy(); }

    @Override
    public MoveStrategy moveStrategy() { return new AlphaMoveStrategy(); }
}


