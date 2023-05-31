package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.*;

public class ZetaCivFactory implements AbstractFactory {
    @Override
    public UnitStrategy unitStrategy() {
        return new AlphaUnitStrategy();
    }

    @Override
    public WinningStrategy winningStrategy() { return new WinnerStrategyZetaCiv();
    }

    @Override
    public WorldStrategy worldStrategy() {
        return new AlphaWorldStrategy();
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
