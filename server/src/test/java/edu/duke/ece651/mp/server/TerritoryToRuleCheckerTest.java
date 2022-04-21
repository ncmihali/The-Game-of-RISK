package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;
import edu.duke.ece651.mp.common.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TerritoryToRuleCheckerTest {
  @Test
  public void test_move() {
    int units = 10;
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    a.setPlayerName("A");
    ActionRuleChecker rule = new TerritoryToRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
    a.setPlayerName("B");
    assertEquals("Must move units to own territory.", rule.checkAction(a, world));
  }

  @Test
  public void test_attack() {
    int units = 10;
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new AttackAction(null, "land", "sea", 0);
    a.setPlayerName("B");
    ActionRuleChecker rule = new TerritoryToRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
    a.setPlayerName("A");
    assertEquals("Cannot attack own territory.", rule.checkAction(a, world));
  }

  @Test
  public void test_others() {
    int units = 10;
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new DoneAction();
    a.setPlayerName("B");
    ActionRuleChecker rule = new TerritoryToRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }
}
