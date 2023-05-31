package hotciv.variants;

import hotciv.framework.*;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;

public class ThetaUnitProductionStrategy implements UnitProductionStrategy {
    UnitProductionStrategy alphaState = new AlphaUnitProductionStrategy();
    @Override
    public int getUnitCost(String type) {
        if(type == GameConstants.SANDWORM){
            return 30;
        } else return alphaState.getUnitCost(type);
    }

    @Override
    public void newUnit(Position position, Game game) {
        City city = game.getCityAt(position);
        String type = city.getProduction();
        if (type.equals(GameConstants.SANDWORM)) {
            int cost = getUnitCost(type);
            if (city.getTreasury() >= cost) {
                for (Position p : Utility.get8neighborhoodOf(position)) {
                    if (game.getTileAt(p).getTypeString().equals(GameConstants.DESSERT)) {
                        boolean notAnotherUnit = game.getUnitAt(p) == null;
                        boolean notAnotherUnitOrOcean = (notAnotherUnit && (!game.getTileAt(p).getTypeString().equals(GameConstants.OCEANS)));
                        boolean notAnotherUnitOrMountain = (notAnotherUnit && (!game.getTileAt(p).getTypeString().equals(GameConstants.MOUNTAINS)));
                        if (notAnotherUnitOrOcean || notAnotherUnitOrMountain) {
                            game.getUnitHashMap().put((p), new UnitImpl(type, city.getOwner()));
                            city.minusTreasury(cost);
                            break;
                        }
                    }
                }

            } else alphaState.newUnit(position, game);
        }
    }
}
