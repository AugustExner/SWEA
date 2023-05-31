package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;


public class GameDecorator implements Game{
    private Game game;
    private boolean transcripting = true;

    public GameDecorator(Game gameDeco){
        game = gameDeco;
    }

    public void setTranscripting(boolean transcripting){
        this.transcripting = transcripting;
    }
    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        if(game.getWinner()!= null){
            if(transcripting) {
                System.out.println(game.getWinner() + " has won the game!!");
            }
            return game.getWinner();
        }
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }

    @Override
    public HashMap getCityHashMap() {
        return game.getCityHashMap();
    }

    @Override
    public void removeUnit(Position p) {
        game.removeUnit(p);
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Unit attackingUnit = game.getUnitAt(from);
        Unit defendingUnit = game.getUnitAt(from);
        if (game.getUnitAt(to) == null) {
            if(transcripting) {
                System.out.println(game.getUnitAt(from).getOwner() + " moves " + game.getUnitAt(from).getTypeString() + " to " + to);
            }
            return game.moveUnit(from, to);
        } else if (game.getUnitAt(to) != game.getUnitAt(from)) {
            boolean winningUnit = game.moveUnit(from, to);
            if (game.getUnitAt(to).getOwner() == attackingUnit.getOwner()) {
                if(transcripting) {
                    System.out.println(attackingUnit.getOwner() + attackingUnit.getTypeString() + " has eliminated " + defendingUnit.getOwner() + defendingUnit.getTypeString() + " and moved to opponents tile");
                }
                return winningUnit;
            } else {
                if(transcripting) {
                    System.out.println(defendingUnit.getOwner() + defendingUnit.getTypeString() + " defends its tile and eliminates " + attackingUnit.getOwner() + attackingUnit.getTypeString());
                }
                return winningUnit;
            }
        } else {
            return game.moveUnit(from, to);
        }
    }

    @Override
    public void makeActualMove(Position from, Position to) {
        game.makeActualMove(from, to);

    }

    @Override
    public void endOfTurn() {
    Player player = game.getPlayerInTurn();
        if(transcripting) {
            System.out.println(player + " has just ended their turn ");
        }
        game.endOfTurn();

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    Player player = game.getPlayerInTurn();
        if(transcripting) {
            System.out.println(player + " has just changed workforce in city at " + p + " to " + balance);
        }
        game.changeWorkForceFocusInCityAt(p,balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        Player player = game.getPlayerInTurn();
        if(transcripting) {
            System.out.println(player + " has just changed production in city at " + p + " to " + unitType);
        }
        game.changeProductionInCityAt(p,unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        if(game.getUnitAt(p).equals(GameConstants.ARCHER)) {
            if(transcripting) {
                System.out.println(getUnitAt(p).getOwner() + " has just fortified their archer at " + p);
            }
            game.performUnitActionAt(p);
        } else if (game.getUnitAt(p).equals(GameConstants.SETTLER)) {
            if(transcripting) {
                System.out.println(getUnitAt(p).getOwner() + " has just used their settler to build a city at " + p);
            }
            game.performUnitActionAt(p);
        }else if (game.getUnitAt(p).equals(GameConstants.LEGION)) {
            if(transcripting) {
                System.out.println(getUnitAt(p).getOwner() + " has just made their legion perform it's unit action at " + p);
            }
            game.performUnitActionAt(p);
        } else if(game.getUnitAt(p).equals(GameConstants.SANDWORM)) {
            if(transcripting) {
                System.out.println(getUnitAt(p).getOwner() + " has just used their sandworm to attack all opponent units near  " + p);
            }
            game.performUnitActionAt(p);
        }
        game.performUnitActionAt(p);
    }

    @Override
    public HashMap getUnitHashMap() {
        return game.getUnitHashMap();
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
