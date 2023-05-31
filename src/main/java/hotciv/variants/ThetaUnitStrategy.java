package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;

import java.util.HashMap;

public class ThetaUnitStrategy implements UnitStrategy {
    UnitStrategy gammaState = new GammaUnitStrategy();

    @Override
    public void performUnitAction(Position p, HashMap<Position, CityImpl> cityHashMap, HashMap<Position, UnitImpl> unitHashMap) {
        Unit unit = unitHashMap.get(p);
        Player unitOwner = unit.getOwner();
        if (unit.getTypeString().equals(GameConstants.SANDWORM)) {
            for (Position position : Utility.get8neighborhoodOf(p)) {
                if (unitHashMap.get(position) != null) {
                    if (!unitHashMap.get(position).getOwner().equals(unitOwner)) {
                        unitHashMap.remove(position);
                    }
                }
            }
        } else gammaState.performUnitAction(p, cityHashMap, unitHashMap);
    }
}
