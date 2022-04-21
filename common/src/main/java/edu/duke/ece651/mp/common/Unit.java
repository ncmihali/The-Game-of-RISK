package edu.duke.ece651.mp.common;

/**
 * This interface represents an Unit for the
 * RISC game.
 */
public interface Unit extends Comparable<Unit> {

  /**
   * @return the name of the Unit in String.
   */
  public String getName();

  /**
   * @return the damage stats of the unit.
   */
  public int getAttackValue();

  /**
   * @return the level of this unit.
   */
  public int getLevel();

  /**
   * @return the String representation of the unit.
   */
  public String toString();

  /**
   * @return the hashcode of the unit.
   */
  public int hashCode();

  /**
   * Checks if the given object is the same as
   * this unit.
   * @param o: Any object to check.
   * @return True if the two units are the same.
   *         False otherwise.
   */
  public boolean equals(Object o);
}

