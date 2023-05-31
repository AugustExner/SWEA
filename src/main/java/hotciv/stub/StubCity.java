package hotciv.stub;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import java.util.UUID;

public class StubCity implements City {
    int treasury = 20;
    public String id;

    public StubCity() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public Player getOwner() {
        return Player.BLUE;
    }

    @Override
    public int getSize() {
        return 10;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return GameConstants.ARCHER;
    }

    @Override
    public String getWorkforceFocus() {
        return GameConstants.foodFocus;
    }

    @Override
    public void minusTreasury(int amount) {
    treasury = treasury - amount;
    }

    @Override
    public void addTreasury(int amount) {
        treasury = treasury + amount;
    }

    @Override
    public String getId() {
        return id;
    }
}
