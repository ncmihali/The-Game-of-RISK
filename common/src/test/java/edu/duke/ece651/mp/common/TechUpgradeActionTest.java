package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TechUpgradeActionTest {
  @Test
  public void test_upgrade_Tech() {
    Action newAction = new TechUpgradeAction(1);
    newAction.setPlayerName("Red");
    World testWorld = create_testWorld();
    newAction.doAction(testWorld);
    assertEquals(450, testWorld.getResources(newAction.getPlayerName()).get("Electricity"));
    assertEquals(50,newAction.getResource());
    assertEquals(null, newAction.getTerritoryFrom());
    assertEquals(null, newAction.getTerritoryTo());
    assertEquals(null,newAction.getUnit());
    assertEquals(500, testWorld.getResources("Blue").get("Electricity"));
    assertEquals(1,testWorld.getTechLevel("Red"));
    assertEquals(1,testWorld.getTechLevel("Blue"));

    newAction.updateWorld(testWorld);
    ArrayList<String> log_ans = new ArrayList<String>();
    log_ans.add("----------TURN 1----------\n"+"Red: Upgraded technology level from level1 to level2 with 50 electricity.\n");
    assertEquals(log_ans, testWorld.getActivityLog());
  }


  @Test
  public void test_illegal_action(){
    Action newAction = new TechUpgradeAction(0);
    newAction.setPlayerName("Red");
    World testWorld = create_testWorld();
    newAction.doAction(testWorld);
    assertEquals(500, testWorld.getResources(newAction.getPlayerName()).get("Electricity"));
    Action newAction1 = new TechUpgradeAction(7);
    newAction1.setPlayerName("Red");
    newAction1.doAction(testWorld);
    assertEquals(500, testWorld.getResources(newAction.getPlayerName()).get("Electricity"));
  }

  public World create_testWorld(){
    Territory t = new BasicTerritory("Land",3, "Red");
    HashSet<Territory> temp = new HashSet<Territory>();
    temp.add(t);
    return new World(temp);
  }
}
