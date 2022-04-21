package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;
import edu.duke.ece651.mp.common.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class TerritoryFromRuleCheckerTest {
  @Test
  public void test_checkRule_valid() {
    int units = 10;
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    a.setPlayerName("A");
    ActionRuleChecker rule = new TerritoryFromRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_checkRule_invalid() {
    int units = 10;
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    a.setPlayerName("B");
    ActionRuleChecker rule = new TerritoryFromRuleChecker(null);
    assertEquals("Cannot use units from other player's territory.",
                 rule.checkAction(a, world));
  }

  @Test
  public void test_checkRule_doneaction() {
    int units = 10;
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "A");
    Territory to = new BasicTerritory("sea", "A");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new DoneAction();
    a.setPlayerName("B");
    ActionRuleChecker rule = new TerritoryFromRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

}
