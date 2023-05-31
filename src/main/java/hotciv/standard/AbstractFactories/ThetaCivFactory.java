package hotciv.standard.AbstractFactories;

import hotciv.framework.*;
import hotciv.variants.*;

public class ThetaCivFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {
        return new ThetaUnitStrategy();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new AttackStrategyAlpha();
    }

    @Override
    public WorldStrategy worldStrategy() {
        return new ThetaWorldStrategy();
    }

    @Override
    public WinningStrategy winningStrategy() {
        return new WinnerStrategyAlphaCiv();
    }

    @Override
    public AgeingStrategy ageingStrategy() {
        return new WorldAgeingAlpha();
    }

    @Override
    public UnitProductionStrategy unitProductionStrategy() { return new ThetaUnitProductionStrategy(); }

    @Override
    public MoveStrategy moveStrategy() { return new ThetaMoveStrategy(); }
}
