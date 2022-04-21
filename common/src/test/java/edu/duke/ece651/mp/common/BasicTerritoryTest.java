package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class BasicTerritoryTest {
  @Test
  public void test_constructor() {
    Unit u = new BasicUnit();
    HashMap<Unit, Integer> units = new HashMap<Unit, Integer>();
    units.put(u, 5);
    Territory t = new BasicTerritory("land", units, "A");
  }

  @Test
  public void test_constructor_evo2() { 
    Territory t = new BasicTerritory("land", 3, "A");
    assertEquals(3, t.getSize());
  }
  
  @Test
  public void test_getName() {
    Territory t = new BasicTerritory("land", "A");
    assertEquals("land", t.getName());
  }

  @Test
  public void test_getUnits() {
    Territory t = new BasicTerritory("land", "A");
    HashMap<Unit,Integer> units = new HashMap<Unit,Integer>();
    Unit u = new BasicUnit();
    units.put(u, 0);
    assertEquals(units.isEmpty(), t.getUnits().isEmpty());
  }

  @Test
  public void test_getUnits_evo2() {
    Territory t = new BasicTerritory("land", 3, "A");
    assertEquals(0, t.getUnits("level0 unit"));
    assertEquals(0, t.getUnits("level1 unit"));
  }

  @Test
  public void test_getPlayer() {
    Territory t = new BasicTerritory("land", "A");
    assertEquals("A", t.getPlayer());
  }

  @Test
  public void test_setPlayer() {
    Territory t = new BasicTerritory("land", "A");
    t.setPlayer("B");
    assertEquals("B", t.getPlayer());
  }

  @Test
  public void test_getNeighbors() {
    Territory t = new BasicTerritory("land", "A");
    assertEquals(new HashSet<Territory>(), t.getNeighbors());
  }

  @Test
  public void test_equals() {
    Territory t1 = new BasicTerritory("land", "A");
    Territory t2 = new BasicTerritory("land", "A");
    Territory t3 = new BasicTerritory("sea", "A");
    assertEquals(t1, t1); // Equals should be reflective
    assertEquals(t1, t2); // Different objects but same contents
    assertNotEquals(t1, t3); // Different content
    assertNotEquals(t3, "sea"); // Different type
  }

  /*
  @Test
  public void test_toString() {
    Territory t1 = new BasicTerritory("land", "A");
    assertEquals("land", t1.toString());
    assertNotEquals("sea", t1.toString());
  }
  */

  /*
  @Test
  public void test_hashCode() {
    Territory t1 = new BasicTerritory("land", "A");
    Territory t2 = new BasicTerritory("land", "A");
    Territory t3 = new BasicTerritory("sea", "A");
    assertEquals(t1.hashCode(), t1.hashCode());
    assertEquals(t1.hashCode(), t2.hashCode());
    assertNotEquals(t1.hashCode(), t3.hashCode());
  }
  */

  @Test
  public void test_isEmpty() {
    Territory t = new BasicTerritory("land", 3, "A");
    assertEquals(true, t.isEmpty());
    t.addUnit("level0 unit", 1);
    assertEquals(false, t.isEmpty());
  }

  @Test
  public void test_addUnit() {
    Territory t1 = new BasicTerritory("land", "A");
    assertThrows(IllegalArgumentException.class, () -> t1.addUnit("land", 1));
    assertThrows(IllegalArgumentException.class, () -> t1.addUnit("land", -1));
    assertThrows(IllegalArgumentException.class, () -> t1.addUnit("land", 0));
  }

  @Test
  public void test_removeUnit() {
    Territory t1 = new BasicTerritory("land", "A");
    t1.addUnit("level0 unit", 1);
    assertEquals(1, t1.getUnits("level0 unit"));
    t1.removeUnit("level0 unit", 1);
    assertEquals(0, t1.getUnits("level0 unit"));
    assertThrows(IllegalArgumentException.class, () -> t1.removeUnit("test", -1));
    assertThrows(IllegalArgumentException.class, () -> t1.removeUnit("test", 0));
  }
}

