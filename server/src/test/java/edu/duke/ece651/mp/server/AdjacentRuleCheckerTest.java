package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;
import edu.duke.ece651.mp.common.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class AdjacentRuleCheckerTest {
  @Test
  public void test_valid_move() {
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    from.addNeighbor(to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_invalid_move() {
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals("There is no path between the two territories.", rule.checkAction(a, world));
  }

  @Test
  public void test_valid_attack() {
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "B");
    from.addNeighbor(to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new AttackAction(null, "land", "sea", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_valid_move2() {
    Territory from = new BasicTerritory("land", "A");
    Territory middle = new BasicTerritory("sea", "A");
    Territory to = new BasicTerritory("mountain", "A");
    from.addNeighbor(middle);
    middle.addNeighbor(from, to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(middle);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "mountain", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_invalid_move2() {
    Territory from = new BasicTerritory("land", "A");
    Territory middle = new BasicTerritory("sea", "B");
    Territory to = new BasicTerritory("mountain", "A");
    from.addNeighbor(middle);
    middle.addNeighbor(from, to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(middle);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "mountain", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals("There is no path between the two territories.", rule.checkAction(a, world));
  }

  @Test
  public void test_invalid_attack() {
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "B");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new AttackAction(null, "land", "sea", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals("Target territory is not adjacent to your selected territory.",
                 rule.checkAction(a, world));
  }

  @Test
  public void test_move_default_world() {
    WorldFactory f = new DefaultWorldFactory();
    World world = f.createWorld();
    Action a = new MoveAction(null, "Oz", "Narnia", 0);
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_otheractions() {
    WorldFactory f = new DefaultWorldFactory();
    World world = f.createWorld();
    ActionRuleChecker rule = new AdjacentRuleChecker(null);
    Action a = new TechUpgradeAction(1);
    assertEquals(null, rule.checkAction(a, world));
  }
}
