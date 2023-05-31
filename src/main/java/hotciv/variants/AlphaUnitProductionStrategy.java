package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;

import java.util.HashMap;
import java.util.Map;

public class AlphaUnitProductionStrategy implements UnitProductionStrategy {
    @Override
    public int getUnitCost(String type) {
        int archerCost = 10;
        int legionCost = 15;
        int settlerCost = 30;
        if (type.equals(GameConstants.ARCHER)){
            return archerCost;
        } else  if (type.equals(GameConstants.LEGION)) {
            return legionCost;
        } else if (type.equals(GameConstants.SETTLER)) {
            return settlerCost;
        } else return 0;
    }


    @Override
    public void newUnit(Position position, Game game) {
        City city = game.getCityAt(position);
        HashMap<Position, UnitImpl> unitMap = game.getUnitHashMap();
        String type = city.getProduction();
        int cost = getUnitCost(type);
        if (city.getTreasury() >= cost){
            for (Position p : Utility.get8neighborhoodOf(position)) {
                boolean notAnotherUnit = game.getUnitAt(p) == null;
                boolean notAnotherUnitOrOcean = (notAnotherUnit && (!game.getTileAt(p).getTypeString().equals(GameConstants.OCEANS)));
                boolean notAnotherUnitOrMountain = (notAnotherUnit && (!game.getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS)));
                if(notAnotherUnitOrOcean || notAnotherUnitOrMountain){
                    unitMap.put((p), new UnitImpl(type, city.getOwner()));
                    city.minusTreasury(cost);
                    break;
                    }
            }
        }
    }
}
