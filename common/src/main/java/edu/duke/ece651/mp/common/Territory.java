package edu.duke.ece651.mp.common;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This interface represents a Territory interface for the RISC game.
 */
public interface Territory extends Comparable<Territory>{

  /**
   * @return the name of the Territory.
   */
  public String getName();

  public int getSize();

  public HashMap<String,Unit> getMappings();
  
  public boolean isEmpty();

  /**
   * @return String, the owner name of this Territory.
   */
  public String getPlayer();

  /**
   * Change the ownership of this Territory.
   * 
   * @param player name: String. the new owner of the Territory.
   */
  public void setPlayer(String player_name);

  /**
   * @return all unit types and quantity of them in a hashmap.
   */
  public HashMap<String, Integer> getUnits();

  public int getUnits(String unit_name);

  /**
   * @param neighbor to add.
   */
  public void addNeighbor(Territory... neighbor);

  /**
   * @return a set of Territories that is adjacent to this Territory.
   */
  public HashSet<Territory> getNeighbors();

  /**
   * Checks if another object is equal to this Territory.
   * 
   * @param o: Any object to check.
   * @return True if they are the same Territory. False otherwise.
   */
  public boolean equals(Object o);

  /**
   * Add a given number to unit to a given unit type.
   * 
   * @param unit_name: name of the unit in String.
   * @param how_many:  the number of unit to add in int.
   * @throws IllegalArgumentException if how_many is less than zero.
   * @throws IllegalArgumentException if unit_name not in Territory.
   */
  public void addUnit(String unit_name, int how_many);

  /**
   * Remove a given number to unit to a given unit type.
   * 
   * @param unit_name: name of the unit in String.
   * @param how_many:  the number of unit to add in int.
   * @throws IllegalArgumentException if how_many is less than zero.
   * @throws IllegalArgumentException if unit_name not in Territory.
   */
  public void removeUnit(String unit_name, int how_many);

  public void removeallUnits();

}
