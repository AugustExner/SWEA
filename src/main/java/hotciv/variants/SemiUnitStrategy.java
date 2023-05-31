package hotciv.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.UnitStrategy;
import hotciv.framework.WinningStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class SemiUnitStrategy implements UnitStrategy {
    UnitStrategy alphaState = new AlphaUnitStrategy();
    UnitStrategy gammaState = new GammaUnitStrategy();

    @Override
    public void performUnitAction(Position p, HashMap<Position, CityImpl> cityHashMap, HashMap<Position, UnitImpl> unitHashMap) {
        if (unitHashMap.get(p).getTypeString().equals(GameConstants.ARCHER)) {
            alphaState.performUnitAction(p, cityHashMap, unitHashMap);
        } else if (unitHashMap.get(p).getTypeString().equals(GameConstants.SETTLER)) {
            gammaState.performUnitAction(p, cityHashMap, unitHashMap);
        }
    }


}
