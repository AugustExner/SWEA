package hotciv.standard.AbstractFactories;

import hotciv.framework.*;
import hotciv.variants.*;

public class MapFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {
        return new AlphaUnitStrategy();
    }

    @Override
    public AttackStrategy attackStrategy() {
        return new AttackStrategyAlpha();
    }

    @Override
    public WorldStrategy worldStrategy() {
        return new MapAdapter();
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
    public UnitProductionStrategy unitProductionStrategy() {
        return new AlphaUnitProductionStrategy();
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new AlphaMoveStrategy();
    }
}
