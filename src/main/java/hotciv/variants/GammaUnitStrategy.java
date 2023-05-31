package hotciv.variants;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.UnitStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class GammaUnitStrategy implements UnitStrategy {
    private String productionType;
    private String workForceFocus;
    @Override
    public void performUnitAction(Position p, HashMap<Position, CityImpl> cityHashMap, HashMap<Position, UnitImpl> unitHashMap) {
        Unit unit = unitHashMap.get(p);
        if(unit.getTypeString().equals(GameConstants.SETTLER)){
            for(CityImpl c : cityHashMap.values()){
                if(c.getOwner().equals(unit.getOwner())){
                    productionType = c.getProduction();
                    workForceFocus = c.getWorkforceFocus();
                }
            }
            cityHashMap.put((p),new CityImpl(unit.getOwner(),productionType, workForceFocus));
            unitHashMap.remove(p);
        } else if(unit.getTypeString().equals(GameConstants.ARCHER)){
            Unit unit2 = unitHashMap.get(p);
            switch(unit2.getDefensiveStrength()){
                case 3:
                    unit2.changeDefensiveStrength(6);
                    unit2.setMoveable(false);
                    break;

                case 6:
                    unit2.changeDefensiveStrength(3);
                    unit2.setMoveable(true);
                    break;
            }
        }
    }
}
