package edu.duke.ece651.mp.common;

import java.util.*;
import java.io.Serializable;

public class DoneAction implements Action, Serializable {
  private String player_name;

  public DoneAction() {
    this.player_name = null;
  }

  /**
   * @param o is the comparison object to compare with the Move instance
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Action u = (Action) o;
      return this.player_name.equals(u.getPlayerName());
    }
    return false;
  }

  /**
   * returns the units of move
   */
  @Override
  public HashMap<String,Integer> getUnit() {
    return null;
  }

  /**
   * int get the resource required.
   */
  @Override
  public int getResource() {
    return 0;
  }

  // returns the player name associated with the turn
  @Override
  public String getPlayerName() {
    return this.player_name;
  }

  /**
   * @param player_name sets the player's name to player_name
   */
  @Override
  public void setPlayerName(String player_name) {
    this.player_name = player_name;
  }

  // return origin territory name
  @Override
  public String getTerritoryFrom() {
    return null;
  }

  // returns the target territory's name
  @Override
  public String getTerritoryTo() {
    return null;
  }

  /**
   * @param my_world is the input world that move takes action on
   */
  @Override
  public synchronized void doAction(World my_world) {}

  /**
   * A dummy function that does nothing
   */
  @Override
  public void updateWorld(World my_world) {}
}
