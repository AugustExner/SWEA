package hotciv.variants;

import hotciv.framework.*;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.WinningStrategy;
import hotciv.standard.CityImpl;

import java.util.HashMap;

public class WinnerStrategyEpsilonCiv implements WinningStrategy {
    private int redAttacks = 0;
    private int blueAttacks = 0;

    @Override
    public Player winnerStrategy(Game game) {
        if(redAttacks >= 3){
            return Player.RED;
        } else if (blueAttacks >= 3 ){
            return Player.BLUE;
        }

        return null;
    }

    @Override
    public void incrementBattlesWonBy(Player p) {

        if(p.equals(Player.RED)){
            redAttacks++;
        } else if(p.equals(Player.BLUE)){
            blueAttacks++;
        }
    }


    @Override
    public void incrementRoundsPlayed() {

    }


}
