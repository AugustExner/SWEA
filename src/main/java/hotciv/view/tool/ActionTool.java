package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ActionTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;

    public ActionTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        boolean isUnit = game.getUnitAt(GfxConstants.getPositionFromXY(x,y)) != null;
        if (e.isShiftDown() && isUnit) {
            boolean isPlayerInTurn = game.getPlayerInTurn().equals(game.getUnitAt(GfxConstants.getPositionFromXY(x,y)).getOwner());
            if (isPlayerInTurn) {
                super.mouseDown(e, x, y);
                game.performUnitActionAt(GfxConstants.getPositionFromXY(x,y));
            }
        }
    }


    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseMove(e, x, y);
    }
}
