package edu.duke.ece651.mp.common;

import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

public class MeteorShowerAction implements Action, Serializable {
  private String player_name;
  private String to;
  private int resource;

  /**
   * @param to    is the new territory one wishes to move to
   */
  public MeteorShowerAction(String to, int resource) {
    this.to = to;
    this.player_name = null;
    this.resource = resource;
  }

  /**
   * @param o is the comparison object to compare with the Move instance
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Action u = (Action) o;
      return this.to.equals(u.getTerritoryTo()) && this.resource == u.getResource();
    }
    return false;
  }

  /**
   * returns the units of move
   */
  @Override
  public HashMap<String, Integer> getUnit() {
    return null;
  }

  /**
   * returns int resource required.
   */
  @Override
  public int getResource() {
    return this.resource;
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
    return this.to;
  }

  /**
   * @param my_world is the input world that move takes action on
   */
  @Override
  public synchronized void doAction(World my_world) {
    // Update resource
    my_world.changeResources(this.player_name, "Electricity", -this.resource);
  }

  /**
   * Add action to activity log
   */
  @Override
  public void updateWorld(World my_world) {
    String message = "";
    message += this.player_name + ": ";
    message += "Cleared all units in ";
    message += this.to + " with "+ resource + " electricity, using meteor shower.";
    my_world.addLog(message);

    Territory terr_to = my_world.getTerritoryByName(this.to);
    terr_to.removeallUnits();
  }
}
