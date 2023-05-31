package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UnitMoveTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;
    private Position originalPosition;
    private Position finalPosition;

    public UnitMoveTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        originalPosition = GfxConstants.getPositionFromXY(x,y);
        boolean isUnitAtPosition = game.getUnitAt(originalPosition) != null;
        boolean isItThePlayersTurn = game.getPlayerInTurn().equals(game.getUnitAt(originalPosition).getOwner());

        if(isUnitAtPosition && isItThePlayersTurn){
            System.out.println("unit is being moved");
            super.mouseDown(e,x,y);
        }

    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y){
        super.mouseDrag(e,x,y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y){
        super.mouseMove(e,x,y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y){
        finalPosition = GfxConstants.getPositionFromXY(x,y);
        if(finalPosition.getColumn() < GameConstants.WORLDSIZE && finalPosition.getRow() < GameConstants.WORLDSIZE &&
        finalPosition.getColumn() > -1 && finalPosition.getRow() > -1){
            boolean isUnitAtPosition = game.getUnitAt(originalPosition) != null;
            boolean moveIsValid = game.moveUnit(originalPosition, finalPosition);
            boolean insideMap = finalPosition != null;
            if(isUnitAtPosition && moveIsValid && insideMap){
                game.moveUnit(originalPosition, finalPosition);
            }
        }

    }
}
