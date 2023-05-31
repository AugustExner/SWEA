package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

import java.util.UUID;

public class CityImpl implements City {
    private Player owner;
    private String productionType;
    private String workForce;
    private int treasure = 0;
    private String id;

    //Constructor
    public CityImpl(Player owner, String productionType, String workForce){
        id = UUID.randomUUID().toString();
        this.owner = owner;
        this.productionType = productionType;
        this.workForce = workForce;}

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public int getTreasury() {
        return treasure;
    }

    @Override
    public String getProduction() {
        return productionType;
    }

    @Override
    public String getWorkforceFocus() {
        return workForce;
    }

    @Override
    public void addTreasury(int amount){
        treasure += amount;}

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void minusTreasury(int amount){
        treasure -= amount;}
}
