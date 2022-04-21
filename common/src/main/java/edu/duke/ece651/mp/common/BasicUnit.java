package edu.duke.ece651.mp.common;

import java.io.Serializable;

/**
 * This class implements Unit with the most
 * basic functionalities.
 */
public class BasicUnit implements Unit, Serializable{
  private String name;
  private int attack_value;

  public BasicUnit() {
    this.name = "level0 unit";
    this.attack_value = 1;
  }

  /**
   * @return the name of the Unit in String.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * @return the damage stats of the unit.
   */
  @Override
  public int getAttackValue() {
    return this.attack_value;
  }

  /**
   * @return the String representation of the unit.
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * @return the hashcode of the unit.
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public int getLevel() {
    return 0;
  }

  /**
   * Checks if the given object is the same as
   * this unit.
   * @param o: Any object to check.
   * @return True if the two units are the same
   *         False otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      BasicUnit u = (BasicUnit) o;
      return this.name.equals(u.getName()) && this.attack_value==(u.getAttackValue());
    }
    return false;
  }

  /**
   * @param u is the unit to compare the current unit to
   */
  @Override
  public int compareTo(Unit u) {
    return -1; // Always smaller
  }
}
