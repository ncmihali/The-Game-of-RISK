package edu.duke.ece651.mp.common;

import java.io.Serializable;
import java.util.*;
import java.util.stream.*;

public class UnitUpgradeAction implements Action, Serializable {
  int resources;
  Territory at;
  HashMap<String, Integer> initial;
  HashMap<String, Integer> after;
  String owner_name;

  /**
   * @param at: Territory which units are upgraded at
   * @param initial: the current units.
   * @param after: the units after upgrade.
   */
  public UnitUpgradeAction(Territory at, HashMap<String, Integer> initial, HashMap<String, Integer> after) {
    this.at = at;
    this.initial = initial;
    this.after = after;
    this.resources = calculateResources();
  }

  // Parse input into array.
  private ArrayList<Integer> parsetoArray(HashMap<String, Integer> input) {
    ArrayList<Integer> ans = new ArrayList<Integer>();
    for (int i = 0; i < 7; i++) {
      StringBuilder s = new StringBuilder();
      s.append("level");
      s.append(i);
      s.append(" unit");
      ans.add(input.get(s.toString()));
    }
    return ans;
  }

  // calculates the cost of upgrade
  private int calculateResources() {
    ArrayList<Integer> bef = parsetoArray(initial);
    ArrayList<Integer> aft = parsetoArray(after);
    return gettotalcost(aft) - gettotalcost(bef);
  }

  // calculate the total cost of upgrade
  private int gettotalcost(ArrayList<Integer> input) {
    int ans = input.get(1) * 3 + input.get(2) * 11 + input.get(3) * 30 + input.get(4) * 55 + input.get(5) * 90
        + input.get(6) * 140;
    return ans;
  }

  // get the resource required.
  public int getResource() {
    return resources;
  }

  // returns the number of units being sent to attack
  public HashMap<String, Integer> getUnit() {
    return initial;
  }

  // returns name of player from turn
  public String getPlayerName() {
    return owner_name;
  }

  /**
   * @param player_name is the name of the player who is attacking
   */
  public void setPlayerName(String player_name){
    owner_name = player_name;
  }

  // return the territory name to upgrade at.
  public String getTerritoryFrom(){
    return at.getName();
  }

  // dummy method.
  public String getTerritoryTo(){
    return null;
  }

  /**
   * @param my_world is the map which will be updated based on new ownership
   */
  public void updateWorld(World my_world){
    String message = this.owner_name + ": ";
    String upgrade_from = "";
    String upgrade_to = "";

    ArrayList<String> unit_names = this.initial.keySet().stream()
        .collect(Collectors.toCollection(ArrayList::new));
    Collections.sort(unit_names);

    for (String u: unit_names) {
      int result = this.after.get(u) - this.initial.get(u);
      if (result < 0) {
        upgrade_from += Math.abs(result) + " " + u + ", ";
      } else if (result > 0) {
        upgrade_to += result + " " + u + ", ";
      }
    }
    message += "Upgraded " + upgrade_from + "into " + upgrade_to +
      "in " + this.at.getName() + " with " + resources + " electricity.";
    my_world.addLog(message);
  }

  /**
   * @param my_world is the work to perfrom the action.
   */
  public void doAction(World my_world){
    my_world.changeResources(owner_name, "Electricity",-resources);
    Territory target = my_world.getTerritoryByName(at.getName());
    target.removeallUnits();
    for(String s:after.keySet()){
      target.addUnit(s, after.get(s));
    }
  }

  /**
   * To compare the different upgrade unit class.
   */
  public boolean equals(Object o){
    if (o.getClass().equals(getClass())) {
      Action u = (Action) o;
      return this.initial.equals(u.getUnit()) && this.resources==u.getResource()&& this.at.getName().equals(u.getTerritoryFrom());
    }
    return false;
  }
}
