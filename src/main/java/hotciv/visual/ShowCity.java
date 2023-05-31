package hotciv.visual;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.stub.StubGame1;
import hotciv.view.GfxConstants;
import hotciv.view.MapView;
import hotciv.view.figure.CityFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.CompositionalDrawing;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/** Test the CityFigure.
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowCity {
  
  public static void main(String[] args) {
    Game game = new StubGame1();
    DrawingEditor editor =
      new MiniDrawApplication( "Click to see city graphics update...",  
                               new HotCivFactory3(game) );
    editor.open();
    CityStub city = new CityStub();

    CityFigure cf = new CityFigure( city,
                                    new Point( GfxConstants.getXFromColumn(4),
                                               GfxConstants.getYFromRow(7) ) );
    editor.drawing().add(cf);
    editor.setTool( new ChangeCityTool(city, cf) );
  }
}

class ChangeCityTool extends NullTool {
  private CityStub city;
  private CityFigure cityFigure;
  public ChangeCityTool(CityStub c, CityFigure cf) {
    city = c;
    cityFigure = cf;
  }
  public void mouseUp(MouseEvent e, int x, int y) {
    city.makeAChange();
    // Note: No observer chain has been defined
    // between Game's city and MiniDraw's cityFigure,
    // (this is not a full HotCiv game)
    // so we have to force the graphics observer
    // update manually.
    cityFigure.changed();
  }
}

class HotCivFactory3 implements Factory {
  private Game game;
  public HotCivFactory3(Game g) { game = g; }

  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new MapView(editor, game);
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new CompositionalDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    return null;
  }
}

// A test stub implementation just to show graphical renderings.
class CityStub implements City {
  boolean redOwns = true;
  // a testing method just to make some
  // state changes
  public void  makeAChange() {
    redOwns = ! redOwns;
  }
  public Player getOwner() {
    return (redOwns ? Player.RED : Player.BLUE);
  }
  public int getSize() {
    return (redOwns ? 4 : 9);
  }
  public int getTreasury() {
    return 0;
  }
  public String getProduction() {
    return null;
  }
  public String getWorkforceFocus() {
    return null;
  }

  @Override
  public void minusTreasury(int amount) {

  }

  @Override
  public void addTreasury(int amount) {

  }

  @Override
  public String getId() {
    return null;
  }
}