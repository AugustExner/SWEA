package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface WorldStrategy {
    public HashMap tileSetup(String[] map);
    public HashMap citySetup();
    public HashMap unitSetup();
}
