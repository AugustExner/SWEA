package hotciv.standard.AbstractFactories;

import hotciv.framework.*;
import hotciv.variants.*;

public class DeltaCivFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {
        return new AlphaUnitStrategy();
    }

    @Override
    public WinningStrategy winningStrategy() {
        return new WinnerStrategyAlphaCiv();
    }

    @Override
    public WorldStrategy worldStrategy() {
        return new DeltaWorldStrategy();
    }

    @Override
    public AgeingStrategy ageingStrategy() {
        return new WorldAgeingAlpha();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new AttackStrategyAlpha();
    }

    @Override
    public UnitProductionStrategy unitProductionStrategy() { return new AlphaUnitProductionStrategy(); }

    @Override
    public MoveStrategy moveStrategy() { return new AlphaMoveStrategy(); }

}
