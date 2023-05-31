package hotciv.framework;

import java.util.HashMap;
import hotciv.standard.CityImpl;

public interface WinningStrategy {
    Player winnerStrategy(Game game);
    void incrementBattlesWonBy(Player p);
    void incrementRoundsPlayed();
}
