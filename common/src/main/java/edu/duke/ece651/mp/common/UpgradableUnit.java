package edu.duke.ece651.mp.common;

import java.io.Serializable;
import java.util.Random;

/**
 * This class implements Unit with the most
 * basic functionalities.
 */
public class UpgradableUnit implements Unit, Serializable{
  private String name;
  private int level;
  private int bonus;
  private Random random;

  public UpgradableUnit(int level, int bonus) {
    this.name = "level" + Integer.toString(level) + " unit"; // format: "levelx unit"
    this.level = level;
    this.bonus = bonus;
    this.random = new Random(); // For generating attack value
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
    return this.random.nextInt(20) + this.bonus;
  }

  @Override
  public int getLevel() {
    return this.level;
  }

  /**
   * @return the String representation of the unit.
   */
  @Override
  public String toString() {
    return this.getName();
  }

  /**
   * @return the hashcode of the unit.
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
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
      UpgradableUnit u = (UpgradableUnit) o;
      return this.name.equals(u.getName()) && this.level == u.getLevel();
    }
    return false;
  }

  /**
   * The algorithm to arrange units in an arraylist, by calling
   * collection.sort()
   * 
   * @param u: UpgradableUnit to compare the current UpgradableUnit to
   */
  @Override
  public int compareTo(Unit u) {
    // For Ascending order
    return this.getLevel() - u.getLevel();
  }
}
