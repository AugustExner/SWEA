package hotciv.variants;

import hotciv.framework.*;

import java.util.HashMap;
import hotciv.standard.CityImpl;

public class WinnerStrategyAlphaCiv implements WinningStrategy {


    @Override
    public Player winnerStrategy(Game game) {
        // Red wins at 3000
        if(game.getAge() == -3000) {
            return Player.RED;
        }
        return null;
    }

    @Override
    public void incrementBattlesWonBy(Player p) {

    }

    @Override
    public void incrementRoundsPlayed() {

    }
}
