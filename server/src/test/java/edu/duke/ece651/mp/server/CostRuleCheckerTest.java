package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
import edu.duke.ece651.mp.common.*;

public class CostRuleCheckerTest {
  @Test
  public void test_Move() {
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "Red");
    Territory to = new BasicTerritory("sea", "Red");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    a.setPlayerName("Red");
    ActionRuleChecker rule = new CostRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
    Action a2 = new MoveAction(null, "land", "sea", 10000000);
    a2.setPlayerName("Red");
    assertEquals("Not enough food resource to perform action.", rule.checkAction(a2, world));
  }

  @Test
  public void test_Attack() {
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "Red");
    Territory to = new BasicTerritory("sea", "Red");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new MoveAction(null, "land", "sea", 0);
    a.setPlayerName("Red");
    ActionRuleChecker rule = new CostRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
    Action a2 = new AttackAction(null, "land", "sea", 10000000);
    a2.setPlayerName("Red");
    assertEquals("Not enough food resource to perform action.", rule.checkAction(a2, world));
  }

  @Test
  public void test_Done() {
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "Red");
    Territory to = new BasicTerritory("sea", "Red");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new DoneAction();
    a.setPlayerName("Red");
    ActionRuleChecker rule = new CostRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
  }

  @Test
  public void test_techUpgrade() {
    HashSet<Territory> territories = new HashSet<Territory>();
    Territory from = new BasicTerritory("land", "Red");
    Territory to = new BasicTerritory("sea", "Red");
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    Action a = new TechUpgradeAction(1);
    a.setPlayerName("Red");
    ActionRuleChecker rule = new CostRuleChecker(null);
    assertEquals(null, rule.checkAction(a, world));
    a.doAction(world);
    
    Action a2 = new TechUpgradeAction(4);
    a2.setPlayerName("Red");
    assertEquals(null, rule.checkAction(a2, world));
    a2.doAction(world);
    
    Action a3 = new TechUpgradeAction(5);
    a3.setPlayerName("Red");
    assertEquals("Not enough electricity resource to perform action.", rule.checkAction(a3, world));

    Action a4 = new TechUpgradeAction(10);
    a4.setPlayerName("Red");
    assertEquals("Already reached maximum technology level.", rule.checkAction(a4, world));
  }

}
