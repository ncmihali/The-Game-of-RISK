package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

public class MoveActionTest {
  @Test
  public void test_Move() {
    Territory one = new BasicTerritory("One", 3, "Red");
    one.addUnit("level0 unit", 5);
    one.addUnit("level1 unit", 5);
    Territory two = new BasicTerritory("Two", 3, "Red");

    HashSet<Territory> newTerritories = new HashSet<Territory>();
    newTerritories.add(one);
    newTerritories.add(two);
    World newWorld = new World(newTerritories);

    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units
    units_to_move.put("level1 unit", 2); // Move 2 level1 units
    units_to_move.put("level2 unit", 0);
    
    Action move = new MoveAction(units_to_move, "One", "Two", 6);
    move.setPlayerName("Red");
    int resource_before = newWorld.getResources("Red").get("Food");
    move.doAction(newWorld);
    assertEquals(resource_before-6, newWorld.getResources("Red").get("Food"));
    assertEquals(4, newWorld.getTerritoryByName("Two").getUnits("level0 unit"));
    assertEquals(1, newWorld.getTerritoryByName("One").getUnits("level0 unit"));
    assertEquals(2, newWorld.getTerritoryByName("Two").getUnits("level1 unit"));
    assertEquals(3, newWorld.getTerritoryByName("One").getUnits("level1 unit"));

    move.updateWorld(newWorld);
    ArrayList<String> log_ans = new ArrayList<String>();
    log_ans.add("----------TURN 1----------\n"+"Red: Moved 4 level0 unit, 2 level1 unit, from One to Two with 6 food.\n");
    assertEquals(log_ans, newWorld.getActivityLog());
  }

  @Test
  public void test_equals() {
    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units                                                                      
    units_to_move.put("level1 unit", 2); // Move 2 level1 units

    HashMap<String,Integer> units_to_move2 = new HashMap<String,Integer>();
    units_to_move2.put("level0 unit", 4); // Move 4 level0 units                                                                        
    units_to_move2.put("level1 unit", 2); // Move 2 level1 units

    Action move = new MoveAction(units_to_move, "One", "Two", 10);
    Action move2 = new MoveAction(units_to_move2, "One", "Two", 10);

    assertEquals(true, move.equals(move2));
    assertEquals(false, move.equals("move"));
  }

  @Test
  public void test_others() {
    HashMap<String,Integer> units_to_move = new HashMap<String,Integer>();
    units_to_move.put("level0 unit", 4); // Move 4 level0 units   
    units_to_move.put("level1 unit", 2); // Move 2 level1 units

    Action move = new MoveAction(units_to_move, "One", "Two", 10);

    assertEquals("One", move.getTerritoryFrom());
    assertEquals("Two", move.getTerritoryTo());

    assertEquals(null, move.getPlayerName());
    move.setPlayerName("Green");
    assertEquals("Green", move.getPlayerName());

    World new_world = new World(null);
    move.updateWorld(new_world);
  }

}
