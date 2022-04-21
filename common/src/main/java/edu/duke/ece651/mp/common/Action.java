package edu.duke.ece651.mp.common;

import java.util.*;

/**
 * Interface for the actions to be performed by the players, parent of Attack
 * and Move
 */
public interface Action {
  /**
   * Obtain the unit to be used by action
   */
  public HashMap<String,Integer> getUnit();

  public int getResource();

  /**
   * Get the name of the player that initiated the action
   */
  public String getPlayerName();

  /**
   * Set the name of the player that initiated the action
   */
  public void setPlayerName(String player_name);

  /**
   * Get the Territory name the action is performed from
   */
  public String getTerritoryFrom();

  /**
   * Get the Territory name the action will be performed on
   */
  public String getTerritoryTo();

  /**
   * The final step for the attack action
   */
  public void updateWorld(World my_world);

  /**
   * this will perform the move either Move or Attack
   */
  public void doAction(World my_world);

  /**
   * To compare the actions
   */
  public boolean equals(Object o);
}
