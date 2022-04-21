package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UpgradableUnitTest {
  @Test
  public void test_getName() {
    Unit u = new UpgradableUnit(0, 0);
    assertEquals("level0 unit", u.getName());
  }

  @Test
  public void test_toString() {
    Unit u = new UpgradableUnit(1,1);
    assertEquals("level1 unit", u.toString());
  }

  @Test
  public void test_getAttackValue() {
    Unit u = new UpgradableUnit(0,0);
    for (int i=0; i < 20; i++) { // Test for 20 times
      assertEquals(true, 19 >= u.getAttackValue());
      assertEquals(true,  0  <= u.getAttackValue());
    }
    u = new UpgradableUnit(6,15);
    for (int i=0; i < 20; i++) { // Test for 20 times
      assertEquals(true, 19+15 >= u.getAttackValue());
      assertEquals(true,  15  <= u.getAttackValue());
    }
  }

  @Test
  public void test_hashCode() {
    Unit u1 = new UpgradableUnit(0,0);
    Unit u2 = new UpgradableUnit(0,0);
    Unit u3 = new UpgradableUnit(1,1);
    assertEquals(u1.hashCode(), u1.hashCode()); // Same object, same type of unit
    assertEquals(u1.hashCode(), u2.hashCode()); // Different object but same type of unit
    assertNotEquals(u1.hashCode(), u3.hashCode());
  }

  @Test
  public void test_equals() {
    Unit u1 = new UpgradableUnit(0,0);
    Unit u2 = new UpgradableUnit(0,0);
    Unit u3 = new UpgradableUnit(1,1);
    assertEquals(u1, u1);   //equals should be reflective
    assertEquals(u1, u2);   //different objects but same contents
    assertNotEquals(u1, "level0 unit"); //different types
    assertNotEquals(u1, u3);
  }

  @Test
  public void test_getLevel() {
    Unit u = new UpgradableUnit(2,3);
    assertEquals(2, u.getLevel());
  }

  @Test
  public void test_compareTo() {
    Unit u = new BasicUnit();
    Unit u1 = new UpgradableUnit(0,0);
    Unit u2 = new UpgradableUnit(2,3);
    assertEquals(true, u2.compareTo(u1) > 0);
    assertEquals(true, u.compareTo(u1) < 0);
    assertEquals(true, u.compareTo(u2) < 0);
    assertEquals(true, u1.compareTo(u2) < 0);
    assertEquals(true, u1.compareTo(u1) == 0);
  }
}
