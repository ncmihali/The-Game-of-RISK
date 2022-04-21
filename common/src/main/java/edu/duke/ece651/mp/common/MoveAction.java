package edu.duke.ece651.mp.common;

import java.util.*;
import java.util.stream.*;
import java.io.Serializable;

public class MoveAction implements Action, Serializable {
  private HashMap<String, Integer> units;
  private String player_name;
  private String from;
  private String to;
  private int resource;

  /**
   * @param units is the total units one wishes to move
   * @param from  is the origin teritory to move units out of
   * @param to    is the new territory one wishes to move to
   */
  public MoveAction(HashMap<String, Integer> units, String from, String to, int resource) {
    this.units = units;
    this.from = from;
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
      return this.units.equals(u.getUnit()) && this.to.equals(u.getTerritoryTo())
          && this.from.equals(u.getTerritoryFrom()) && this.resource == u.getResource();
    }
    return false;
  }

  /**
   * returns the units of move
   */
  @Override
  public HashMap<String, Integer> getUnit() {
    return this.units;
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
    return this.from;
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
    /* remove units from source and add them to destination territory */
    Territory terr_to = my_world.getTerritoryByName(this.to);
    Territory terr_from = my_world.getTerritoryByName(this.from);
    for (String u : this.units.keySet()) {
      int how_many = this.units.get(u);
      terr_from.removeUnit(u, how_many);
      terr_to.addUnit(u, how_many);
    }
    // Update resource
    my_world.changeResources(this.player_name, "Food", -this.resource);
  }

  /**
   * Add action to activity log
   */
  @Override
  public void updateWorld(World my_world) {
    String message = "";
    message += this.player_name + ": ";
    message += "Moved ";

    ArrayList<String> unit_names = this.units.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
    Collections.sort(unit_names);
    for (String u : unit_names) {
      if (this.units.get(u) > 0) {
        message += this.units.get(u) + " " + u + ", ";
      }
    }
    message += "from " + this.from + " to " + this.to + " with "+ resource + " food.";
    my_world.addLog(message);
  }
}
