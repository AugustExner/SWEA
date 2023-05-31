package hotciv.framework;

public interface AbstractFactory {
    UnitStrategy unitStrategy();
    AttackStrategy attackStrategy();
    WorldStrategy worldStrategy();
    WinningStrategy winningStrategy();
    AgeingStrategy ageingStrategy();
    UnitProductionStrategy unitProductionStrategy();
    MoveStrategy moveStrategy();

}

