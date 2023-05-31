package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import java.util.List;
import java.util.ArrayList;

public class GameObserverImpl implements GameObserver {
    private ArrayList<String> observers;

    public GameObserverImpl() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void worldChangedAt(Position pos) {
        observers.add("World changed at " + pos);
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        observers.add("The next player in turn is: " + nextPlayer + " and the world age is: " + age);
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        observers.add("tile focus changes at " + position);

    }

    public String getObserverList(int index) {
        return observers.get(index);
    }

}
