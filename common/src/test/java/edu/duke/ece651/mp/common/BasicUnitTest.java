package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BasicUnitTest {
  @Test
  public void test_getName() {
    Unit u = new BasicUnit();
    assertEquals("level0 unit", u.getName());
  }

  @Test
  public void test_toString() {
    Unit u = new BasicUnit();
    assertEquals("level0 unit", u.toString());
  }

  @Test
  public void test_getAttackValue() {
    Unit u = new BasicUnit();
    assertEquals(1, u.getAttackValue());
  }

  @Test
  public void test_hashCode() {
    Unit u1 = new BasicUnit();
    Unit u2 = new BasicUnit(); 
    assertEquals(u1.hashCode(), u1.hashCode()); // Same object, same type of unit
    assertEquals(u1.hashCode(), u2.hashCode()); // Different object but same type of unit
  }

  @Test
  public void test_equals() {
    Unit u1 = new BasicUnit();
    Unit u2 = new BasicUnit();
    assertEquals(u1, u1);   //equals should be reflective
    assertEquals(u1, u2);   //different objects but same contents
    assertNotEquals(u1, "unit"); //different types  
  }

  @Test
  public void test_getLevel() {
    Unit u = new BasicUnit();
    assertEquals(0, u.getLevel());
  }

  @Test
  public void test_compareTo() {
    Unit u = new BasicUnit();
    assertEquals(true, u.compareTo(u) < 0);
  }
}

