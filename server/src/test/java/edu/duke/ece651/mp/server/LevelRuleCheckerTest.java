package edu.duke.ece651.mp.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
import edu.duke.ece651.mp.common.*;
import java.util.HashMap;

public class LevelRuleCheckerTest {
  @Test
  public void test() {
    ActionRuleChecker rule = new LevelRuleChecker(null);

    Territory from = new BasicTerritory("land", 3,"Red");
    Territory to = new BasicTerritory("sea", 3, "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);
    
    Action a = new MeteorShowerAction("sea", 250);
    Action a2 = new MoveAction(null, "land", "sea", 0);
    a.setPlayerName("Red");

    assertEquals("Meteor shower not avaliable until technology level 6.", rule.checkAction(a, world));
    assertEquals(null, rule.checkAction(a2, world));

    world.techLevelup("Red");
    world.techLevelup("Red");
    world.techLevelup("Red");
    world.techLevelup("Red");
    world.techLevelup("Red");
    assertEquals(null, rule.checkAction(a, world));
  }

}
