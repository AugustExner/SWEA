package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.CityImpl;

import java.util.HashMap;

public class WinnerStrategyBetaCiv implements WinningStrategy {
    private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();

    @Override
    public Player winnerStrategy(Game game) {
        cityHashMap = game.getCityHashMap();
        int count = cityHashMap.size();
        int red = 0;
        int blue = 0;
        for (City c : cityHashMap.values()) {
            if (c.getOwner().equals(Player.RED)) {
                red++;
            } else if (c.getOwner().equals(Player.BLUE)) {
                blue++;
            }
        }
        if (red == count) {
            return Player.RED;
        } else if (blue == count) {
            return Player.BLUE;
        } else return null;

    }

    @Override
    public void incrementBattlesWonBy(Player p) {

    }

    @Override
    public void incrementRoundsPlayed() {

    }
}
