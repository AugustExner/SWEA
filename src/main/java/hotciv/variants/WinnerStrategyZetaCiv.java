package hotciv.variants;

import hotciv.framework.*;
import hotciv.framework.Position;
import hotciv.framework.WinningStrategy;
import hotciv.standard.CityImpl;


import java.util.HashMap;

public class WinnerStrategyZetaCiv implements WinningStrategy {
    private int rounds = 0;
    private int redAttacks = 0;
    private int blueAttacks = 0;
    WinningStrategy betaState = new WinnerStrategyBetaCiv();
    WinningStrategy epsilonState = new WinnerStrategyEpsilonCiv();
    private HashMap<Position, CityImpl> cityHashMap = new HashMap<>();


    @Override
    public Player winnerStrategy(Game game) {
        if(rounds < 20){
            return betaState.winnerStrategy(game);
        } else return epsilonState.winnerStrategy(game);

    }

    @Override
    public void incrementBattlesWonBy(Player p) {
        if(rounds < 20){
            betaState.incrementBattlesWonBy(p);
        } else epsilonState.incrementBattlesWonBy(p);

    }

    @Override
    public void incrementRoundsPlayed() {
        rounds++;
    }
}
