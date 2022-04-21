package edu.duke.ece651.mp.client;

import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.HashMap;

@ExtendWith(ApplicationExtension.class)
public class ControllerTest {

  @Test
  public void test_one() {
    Controller controller = new Controller();
    controller.midkemiasize = new Label("3");
    assertEquals(controller.midkemiasize.getText(), "3");
  }

  @Test
  public void test_unitsMovedAttacked(){
    Controller controller = new Controller();
    controller.units = new HashMap<String, Integer>();
    controller.units.put("level0 unit", 1);
    controller.units.put("level1 unit", 1);
    controller.units.put("level2 unit", 1);
    controller.units.put("level3 unit", 1);
    controller.units.put("level4 unit", 1);
    controller.units.put("level5 unit", 1);
    controller.units.put("level6 unit", 1);
    controller.activitylog = new TextArea();

    controller.printUnitsMovedAttacked();
    //assertEquals(controller.midkemiasize.getText(), "3");
  }
  @Test
  public void test_printupgraded(){
    Controller controller = new Controller();
    controller.after = new HashMap<String, Integer>();
    controller.after.put("level0 unit", 1);
    controller.after.put("level1 unit", 1);
    controller.after.put("level2 unit", 1);
    controller.after.put("level3 unit", 1);
    controller.after.put("level4 unit", 1);
    controller.after.put("level5 unit", 1);
    controller.after.put("level6 unit", 1);
    controller.activitylog = new TextArea();
    //assertEquals(controller.midkemiasize.getText(), "3");
  }

  @Test
  public void test_upgradeshowhide(){
    Controller controller = new Controller();

    controller.templvl0 = 1;
    controller.templvl1 = 1;
    controller.templvl2 = 1;
    controller.templvl3 = 1;
    controller.templvl4 = 1;
    controller.templvl5 = 1;
    controller.templvl6 = 1;

    controller.level0button = new Button();
    controller.level1button = new Button();
    controller.level2button = new Button();
    controller.level3button = new Button();
    controller.level4button = new Button();
    controller.level5button = new Button();
    controller.level6button = new Button();
    controller.cancelbutton = new Button();
    controller.finishbutton = new Button();
    controller.costlabel2 = new Label("test");
controller.dragonstrikebutton = new Button();
    controller.updateUpgradeButtons();
  }

  @Test
  public void test_showhidebuttons(){
    Controller controller = new Controller();
    controller.attackbutton = new Button();
    controller.movebutton = new Button();
    controller.donebutton = new Button();
    controller.upgradebutton = new Button();
    controller.upgradetlbutton = new Button();
    controller.upgradecostlabel = new Label("test");
controller.dragonstrikebutton = new Button();
controller.dragonstrikecost = new Label();
  }

  @Test
  public void test_updatecosts(){
      Controller controller = new Controller();
      controller.costlabel2 = new Label();
      controller.activitylog = new TextArea();
      controller.setActivityLog("testing");
      controller.totalcost = 10;
  }

  @Test
  public void test_enabledisable(){
    Controller controller = new Controller();
    controller.roshar = new Rectangle();
    controller.hogwarts = new Rectangle();
    controller.elantris = new Rectangle();
    controller.oz = new Rectangle();
    controller.midkemia = new Rectangle();
    controller.narnia = new Rectangle();
    controller.scadrial = new Rectangle();
    controller.gondor = new Rectangle();

    controller.disableRectangles();
    assertEquals(true, controller.gondor.isDisable());
    controller.enableRectangles();
  }

  @Test
  public void test_datalabels(){
    Controller controller = new Controller();
    controller.ozlabel = new Label("test");
    controller.rosharlabel = new Label("test");
    controller.midkemialabel = new Label("test");
    controller.hogwartslabel = new Label("test");
    controller.scadriallabel = new Label("test");
    controller.gondorlabel = new Label("test");
    controller.narnialabel = new Label("test");
    controller.elantrislabel = new Label("test");

    controller.ozsize = new Label("test");
    controller.rosharsize = new Label("test");
    controller.midkemiasize = new Label("test");
    controller.hogwartssize = new Label("test");
    controller.scadrialsize = new Label("test");
    controller.gondorsize = new Label("test");
    controller.narniasize = new Label("test");
    controller.elantrissize = new Label("test");

    controller.roshardatalabel = new Label("test");
    controller.hogwartsdatalabel = new Label("test");
    controller.elantrisdatalabel = new Label("test");
    controller.ozdatalabel = new Label("test");
    controller.midkemiadatalabel = new Label("test");
    controller.narniadatalabel = new Label("test");
    controller.scadrialdatalabel = new Label("test");
    controller.gondordatalabel = new Label("test");
    controller.dragonstrikebutton = new Button();
    controller.dragonstrikecost = new Label();
  }

  @Test
  public void test_updatecosts2(){
    Controller controller = new Controller();
    controller.costlabel = new Label();
    controller.totalfoodcost = 10;
    controller.pathcost = 1;

    controller.level0slider = new Spinner<Integer>();
    controller.level1slider = new Spinner<Integer>();
    controller.level2slider = new Spinner<Integer>();
    controller.level3slider = new Spinner<Integer>();
    controller.level4slider = new Spinner<Integer>();
    controller.level5slider = new Spinner<Integer>();
    controller.level6slider = new Spinner<Integer>();

    controller.level0label = new Label("test");
    controller.level1label = new Label("test");
    controller.level2label = new Label("test");
    controller.level3label = new Label("test");
    controller.level4label = new Label("test");
    controller.level5label = new Label("test");
    controller.level6label = new Label("test");
    controller.submitbutton = new Button();
    controller.dragonstrikebutton = new Button();
    controller.dragonstrikecost = new Label();
  }
}
