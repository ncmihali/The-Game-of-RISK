package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;
import edu.duke.ece651.mp.common.*;
import java.util.HashMap;
import org.junit.jupiter.api.*;
import java.util.*;

public class UnitRuleCheckerTest {
  @Test
  public void test_validcase() {
    Territory from = new BasicTerritory("land", 3,"A");
    from.addUnit("level0 unit", 10);
    Territory to = new BasicTerritory("sea", 3, "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    HashMap<String,Integer> units_to_put = new HashMap<String,Integer>();
    units_to_put.put("level0 unit", 10);
    Action a = new MoveAction(units_to_put, "land", "sea", 0);

    ActionRuleChecker rule = new UnitRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  /*
  @Test
  public void test_invalidcase() {
    Territory from = new BasicTerritory("land", 3, "A");
    Territory to = new BasicTerritory("sea", 3, "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level0 unit", 10);
    Action a = new MoveAction(units, "land", "sea", 0);
    
    ActionRuleChecker rule = new UnitRuleChecker(null);
    assertEquals("Must select at least one unit.", rule.checkAction(a, world));
    Action b = new MoveAction(units, "sea", "land");
    assertEquals("Must select at least one unit.", rule.checkAction(b, world));
  }
  */

  @Test
  public void test_notenough() {
    Territory from = new BasicTerritory("land", 3, "A");
    from.addUnit("level0 unit", 5);
    Territory to = new BasicTerritory("sea", 3, "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    HashMap<String,Integer> units_to_put = new HashMap<String,Integer>();
    units_to_put.put("level0 unit", 10);
    Action a = new MoveAction(units_to_put, "land", "sea", 0);
    
    ActionRuleChecker rule = new UnitRuleChecker(null);
    assertEquals("Territory does not have enough unit.", rule.checkAction(a, world));
  }

  @Test
  public void test_upgrades() {
    Territory from = new BasicTerritory("land", 3, "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    World world = new World(territories);
    Action a = new TechUpgradeAction(1);
    ActionRuleChecker rule = new UnitRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }
}
