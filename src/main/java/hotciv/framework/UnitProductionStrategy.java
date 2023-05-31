package hotciv.framework;

public interface UnitProductionStrategy {

    int getUnitCost(String type);

    void newUnit(Position position, Game game);
}
