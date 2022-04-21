package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;

public class MeteorShowerActionTest {
  @Test
  public void test_function() {
    Territory one = new BasicTerritory("One", 3, "Red");
    Territory two = new BasicTerritory("Two", 3, "Blue");
    two.addUnit("level0 unit", 5);
    two.addUnit("level1 unit", 5);

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    Action attack = new MeteorShowerAction("Two", 250);
    attack.setPlayerName("Red");
    int resource_before = newWorld.getResources("Red").get("Electricity");
    attack.doAction(newWorld);
    assertEquals(resource_before-250, newWorld.getResources("Red").get("Electricity"));

    attack.updateWorld(newWorld);
    String s ="----------TURN 1----------\n"+"Red: Cleared all units in Two with 250 electricity, using meteor shower.\n";
    
    assertEquals(s, newWorld.getActivityLog().get(0));
    assertEquals(0, two.getUnits("level0 unit"));
    assertEquals(0, two.getUnits("level1 unit"));
  }

  @Test
  public void test_equals() {
    Action attack1 = new MeteorShowerAction("Two", 250);
    Action attack2 = new MeteorShowerAction("Two", 250);
    Action attack3 = new MeteorShowerAction("One", 50);
    assertEquals(true, attack1.equals(attack2));
    assertEquals(false, attack1.equals(attack3));
    assertEquals(false, attack1.equals("attack1"));
  }

  @Test
  public void test_others() {
    Action attack = new MeteorShowerAction("Two", 250);

    assertEquals(null, attack.getTerritoryFrom());
    assertEquals("Two", attack.getTerritoryTo());

    assertEquals(null, attack.getPlayerName());
    attack.setPlayerName("Red");
    assertEquals("Red", attack.getPlayerName());
    assertEquals(null, attack.getUnit());
  }

}
