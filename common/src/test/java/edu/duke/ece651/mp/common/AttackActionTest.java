package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

public class AttackActionTest {
  @Test
  public void test_Attack() {
    HashMap<String,Integer> units_to_atk = new HashMap<String,Integer>();
    units_to_atk.put("level0 unit", 4); // Move 4 level0 units                                                                      
    units_to_atk.put("level1 unit", 2); // Move 2 level1 units

    HashMap<String,Integer> units_to_atk2 = new HashMap<String,Integer>();
    units_to_atk2.put("level0 unit", 4); // Move 4 level0 units                                                                        
    units_to_atk2.put("level1 unit", 2); // Move 2 level1 units

    Action attack = new AttackAction(units_to_atk, "One", "Two", 10);
    Action attack2 = new AttackAction(units_to_atk2, "One", "Two", 10);

    assertEquals(true, attack.equals(attack2));
    assertEquals(false, attack.equals("attack"));
  }

  @Test
  public void test_others() {
    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units   
    units_to_move.put("level1 unit", 2); // Move 2 level1 units

    Action attack = new AttackAction(units_to_move, "One", "Two", 10);

    assertEquals("One", attack.getTerritoryFrom());
    assertEquals("Two", attack.getTerritoryTo());

    assertEquals(null, attack.getPlayerName());
    attack.setPlayerName("Green");
    assertEquals("Green", attack.getPlayerName());
  }

  @Test
  public void test_doAction() {
    Territory one = new BasicTerritory("One", 3, "Green");
    one.addUnit("level0 unit", 5);
    one.addUnit("level1 unit", 5);
    Territory two = new BasicTerritory("Two", 3, "Blue");

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units
    units_to_move.put("level1 unit", 2); // Move 2 level1 units

    Action attack = new AttackAction(units_to_move, "One", "Two", 6);
    attack.setPlayerName("Red");
    int resource_before = newWorld.getResources("Red").get("Food");
    attack.doAction(newWorld);
    assertEquals(resource_before-6, newWorld.getResources("Red").get("Food"));
    assertEquals(1, newWorld.getTerritoryByName("One").getUnits("level0 unit"));
    assertEquals(3, newWorld.getTerritoryByName("One").getUnits("level1 unit"));
    assertEquals(0, newWorld.getTerritoryByName("One").getUnits("level2 unit"));

    attack.updateWorld(newWorld);
    String s ="----------TURN 1----------\n"+"Red: Attack SUCCESSFUL from One to Two\nwere lost while attacking.\n";
    assertEquals(s, newWorld.getActivityLog().get(0));
  }

  @Test
  public void test_updateWorld_basic() {
    Territory one = new BasicTerritory("One", 3, "Red");
    one.addUnit("level0 unit", 5);
    one.addUnit("level1 unit", 5);
    Territory two = new BasicTerritory("Two", 3, "Red");
    two.addUnit("level0 unit", 2);

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units
    units_to_move.put("level1 unit", 2); // Move 2 level1 units  

    Action attack = new AttackAction(units_to_move, "One", "Two", 10);
    attack.setPlayerName("Red");
    
    assertEquals("Red", two.getPlayer());
    attack.updateWorld(newWorld);
    assertEquals("Red", two.getPlayer());

    assertEquals(6, two.getUnits("level0 unit"));
    assertEquals(2, two.getUnits("level1 unit"));
    assertEquals(0, two.getUnits("level2 unit"));
  }

  @Test
  public void test_updateWorld_basic2() {
    Territory one = new BasicTerritory("One", 3, "Red");
    one.addUnit("level0 unit", 5);
    one.addUnit("level1 unit", 5);
    Territory two = new BasicTerritory("Two", 3, "Blue");

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units                                                                                    
    units_to_move.put("level1 unit", 2); // Move 2 level1 units                                                                                   

    Action attack = new AttackAction(units_to_move, "One", "Two", 10);
    attack.setPlayerName("Red");

    assertEquals("Blue", two.getPlayer());
    attack.updateWorld(newWorld);
    assertEquals("Red", two.getPlayer());

    assertEquals(4, two.getUnits("level0 unit"));
    assertEquals(2, two.getUnits("level1 unit"));
    assertEquals(0, two.getUnits("level2 unit"));
  }

  @Test
  public void test_updateWorld_advance() {
    Territory one = new BasicTerritory("One", 3, "Red");
    one.addUnit("level6 unit", 5);
    one.addUnit("level2 unit", 1);
    Territory two = new BasicTerritory("Two", 3, "Blue");
    two.addUnit("level0 unit", 5);
    two.addUnit("level6 unit", 5);

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level6 unit", 5); // Move 5 level6 units
    units_to_move.put("level2 unit", 1);
    
    Action attack = new AttackAction(units_to_move, "One", "Two", 10);
    attack.setPlayerName("Red");

    
    assertEquals("Blue", two.getPlayer());
    attack.updateWorld(newWorld);
    /*
    assertEquals("Green", two.getPlayer());

    assertEquals(0, two.getUnits("level0 unit"));
    assertEquals(false, two.getUnits("level0 unit") > 0 && two.getUnits("level0 unit") <= 5);
    assertEquals(0, two.getUnits("level3 unit")); //sanity check
    */
  }

  @Test
  public void test_updateWorld_advance2() {
    Territory one = new BasicTerritory("One", 3, "Red");
    one.addUnit("level6 unit", 5);
    one.addUnit("level5 unit", 5);
    Territory two = new BasicTerritory("Two", 3, "Blue");
    two.addUnit("level0 unit", 1);

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level6 unit", 5); // Attack with 5 level6 units
    units_to_move.put("level5 unit", 3); // Attack with 3 level5 units

    Action attack = new AttackAction(units_to_move, "One", "Two", 10);
    attack.setPlayerName("Red");

    assertEquals("Blue", two.getPlayer());
    attack.updateWorld(newWorld);
    assertEquals("Red", two.getPlayer());

    assertEquals(0, two.getUnits("level0 unit"));
    assertEquals(true, two.getUnits("level6 unit") > 0 && two.getUnits("level6 unit") <= 5);
    assertEquals(3, two.getUnits("level5 unit"));
    assertEquals(0, two.getUnits("level3 unit")); //sanity check                                                                                    
  }
}
