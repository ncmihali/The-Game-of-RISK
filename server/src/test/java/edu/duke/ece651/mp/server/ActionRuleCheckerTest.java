package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;
import edu.duke.ece651.mp.common.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

public class ActionRuleCheckerTest {
  @Test
  public void test_all_valid() {
    ActionRuleChecker rule = ActionRuleChecker.makeActionRuleChecker();
    
    Territory from = new BasicTerritory("land", 0, "Red");
    from.addUnit("level0 unit", 10);
    Territory to = new BasicTerritory("sea", 0, "Red");
    from.addNeighbor(to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level0 unit", 10);
    
    Action a = new MoveAction(units, "land", "sea", 100);
    a.setPlayerName("Red");
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_all_invalid_from() {
    ActionRuleChecker rule = ActionRuleChecker.makeActionRuleChecker();

    Territory from = new BasicTerritory("land", 0,  "Blue");
    from.addUnit("level0 unit", 10);
    Territory to = new BasicTerritory("sea", 0, "Red");
    from.addNeighbor(to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level0 unit", 10);
    
    Action a = new MoveAction(units, "land", "sea", 100);
    a.setPlayerName("Red");
    assertEquals("Cannot use units from other player's territory.", rule.checkAction(a, world));
  }

  @Test
  public void test_all_invalid_to() {
    ActionRuleChecker rule = ActionRuleChecker.makeActionRuleChecker();
    
    Territory from = new BasicTerritory("land", 0, "Red");
    from.addUnit("level0 unit", 10);
    Territory to = new BasicTerritory("sea", 0, "Blue");
    from.addNeighbor(to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level0 unit", 10);
    
    // Test Move to
    Action a = new MoveAction(units, "land", "sea", 100);
    a.setPlayerName("Red");
    assertEquals("Must move units to own territory.", rule.checkAction(a, world));
    // Test Attack to
    Action b = new AttackAction(units, "land", "sea", 100);
    b.setPlayerName("Red");
    assertEquals(null, rule.checkAction(b, world));
  }
  
}
