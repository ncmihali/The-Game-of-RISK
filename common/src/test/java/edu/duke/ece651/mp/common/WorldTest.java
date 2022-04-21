package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class WorldTest {
  @Test
  public void test_World() {
    HashSet<Territory> to_put = new HashSet<Territory>();
    Territory to_test = new BasicTerritory("Narnia", "Blue");
    to_put.add(to_test);
    to_put.add(new BasicTerritory("Grizwald", "Red"));
    World new_world = new World(to_put);
    assertEquals(new_world.getPlayerName().contains("Red"), true);
    assertEquals(new_world.getPlayerName().contains("Blue"), true);

    HashSet<Territory> to_put2 = new HashSet<Territory>();
    Territory to_test2 = new BasicTerritory("Narnia", "Red");
    to_put2.add(to_test2);
    to_put2.add(new BasicTerritory("Grizwald", "Blue"));
    World new_world2 = new World(to_put2);
    assertFalse(new_world.equals(new_world2));
    assertFalse(new_world2.getTerritories().contains(to_test));
    HashSet<Territory> to_put3 = new HashSet<Territory>();
    Territory to_test3 = new BasicTerritory("Narnia", "Blue");
    to_put3.add(to_test3);
    to_put3.add(new BasicTerritory("Grizzzwald", "Red"));
    World new_world3 = new World(to_put3);
    assertFalse(new_world.equals(new_world3));
    assertFalse(new_world.equals(new BasicTerritory("test1", "test1")));
  }

  @Test
  public void test_equals(){
    DefaultWorldFactory f = new DefaultWorldFactory();
    World testWorld = f.createWorld();
    World testWorld2 = f.createWorld();
    assert(testWorld.equals(testWorld2));
  }

  @Test
  public void test_get_territory_by_name(){
    DefaultWorldFactory f =new DefaultWorldFactory();
    World testWorld = f.createWorld();
    String name1="Oz";
    String name2="Happy";
    Territory t = testWorld.getTerritoryByName(name1);
    assertEquals("Oz",t.getName());
    assertEquals(null,testWorld.getTerritoryByName(name2));
  }

  @Test
  public void test_hasEnd() {
    HashSet<Territory> toPut = new HashSet<Territory>();
    World emptyWorld = new World(toPut);
    assertEquals(null, emptyWorld.hasEnd());

    DefaultWorldFactory f =new DefaultWorldFactory();
    World testWorld = f.createWorld();
    assertEquals(null, testWorld.hasEnd());

    testWorld.getTerritoryByName("Elantris").setPlayer("Red");
    testWorld.getTerritoryByName("Scadrial").setPlayer("Red");
    testWorld.getTerritoryByName("Roshar").setPlayer("Red");
    assertEquals("Red", testWorld.hasEnd());
  }

  @Test
  public void test_getPathCost_simple_path() {
    Territory from = new BasicTerritory("land", 10, "A");
    Territory to = new BasicTerritory("sea", 10, "A");
    from.addNeighbor(to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    assertEquals(20, world.getPathCost(from, to));
  }

  @Test
  public void test_getPathCost_no_path() {
    Territory from = new BasicTerritory("land", 10, "A");
    Territory to = new BasicTerritory("sea", 10, "A");
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(to);
    World world = new World(territories);

    assertEquals(Integer.MAX_VALUE, world.getPathCost(from, to));
  }

  @Test void test_getPathCost_complex_path() {
    Territory from = new BasicTerritory("land", 10, "A");
    Territory middle = new BasicTerritory("sea", 10, "B");
    Territory to = new BasicTerritory("mountain", 10, "A");
    from.addNeighbor(middle);
    middle.addNeighbor(from, to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(middle);
    territories.add(to);
    World world = new World(territories);
    assertEquals(Integer.MAX_VALUE, world.getPathCost(from, to));
  }

  @Test void test_getPathCost_complex_path2() {
    Territory from = new BasicTerritory("land", 10, "A");
    Territory middle = new BasicTerritory("sea", 10, "B");
    Territory middle2 = new BasicTerritory("sea2", 10, "A");
    Territory to = new BasicTerritory("mountain", 10, "A");
    from.addNeighbor(middle, middle2);
    middle.addNeighbor(from, to);
    middle2.addNeighbor(from, to);
    HashSet<Territory> territories = new HashSet<Territory>();
    territories.add(from);
    territories.add(middle);
    territories.add(to);
    territories.add(middle2);
    World world = new World(territories);
    assertEquals(30, world.getPathCost(from, to));
  }

  @Test
  public void test_getActivityLog() {
    World world = new World(null);
    assertEquals(true, world.getActivityLog().equals(new ArrayList<String>()));
  }

  @Test
  public void test_addLog() {
    World world = new World(null);
    assertEquals(true, world.getActivityLog().isEmpty());
    world.addLog("Hello World");
    ArrayList<String> ans = new ArrayList<String>();
    ans.add("----------TURN 1----------\n"+"Hello World\n");
    assertEquals(ans, world.getActivityLog());
    world.addLog("Hello World Again");
    ans.set(0,ans.get(0)+"Hello World Again\n");
    assertEquals(ans, world.getActivityLog());
  }
}

