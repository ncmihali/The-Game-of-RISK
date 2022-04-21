package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.*;

import org.junit.jupiter.api.Test;

public class UnitUpgradeActionTest {
  @Test
  public void test_calculate_cost() {
    UnitUpgradeAction upgrade = formAction();
    assertEquals(329,upgrade.getResource());
  }

  @Test
  public void test_doAction(){
    World testWorld = create_testWorld();
    UnitUpgradeAction upgrade = formAction();
    upgrade.setPlayerName("Red");
    assertEquals("Red",upgrade.getPlayerName());
    upgrade.doAction(testWorld);
    assertEquals(171,testWorld.getResources("Red").get("Electricity"));
    assertEquals(500, testWorld.getResources("Blue").get("Electricity"));

    upgrade.updateWorld(testWorld);
    ArrayList<String> log_ans = new ArrayList<String>();
    log_ans.add("----------TURN 1----------\n"+
                "Red: Upgraded into 1 level0 unit, 1 level1 unit, " +
                "1 level2 unit, 1 level3 unit, 1 level4 unit, 1 level5 unit, 1 level6 unit, " +  
                "in Land with 329 electricity.\n");
    assertEquals(log_ans, testWorld.getActivityLog());
  }

  @Test
  public void test_meaningless_method(){
    UnitUpgradeAction newaction = formAction();
    HashMap<String,Integer> before = new HashMap<String,Integer>();
    HashMap<String,Integer> after = new HashMap<String,Integer>();
    for (int i = 0;i<7;i++){
      String s= "level"+i+" unit";
      before.put(s,0);
      after.put(s,1);
    }
    assertEquals(before, newaction.getUnit());
    assertFalse(newaction.equals(before));
    assertEquals("Land",newaction.getTerritoryFrom());
    assertEquals(null,newaction.getTerritoryTo());
    assert(newaction.equals(formAction()));
  }

  public UnitUpgradeAction formAction(){
    HashMap<String,Integer> before = new HashMap<String,Integer>();
    HashMap<String,Integer> after = new HashMap<String,Integer>();
    for (int i = 0;i<7;i++){
      String s= "level"+i+" unit";
      before.put(s,0);
      after.put(s,1);
    }
      Territory t = new BasicTerritory("Land",3, "Red");
      return new UnitUpgradeAction(t, before, after);
  }

  public World create_testWorld(){
    Territory t = new BasicTerritory("Land",3, "Red");
    HashSet<Territory> temp = new HashSet<Territory>();
    temp.add(t);
    return new World(temp);
  }
}
