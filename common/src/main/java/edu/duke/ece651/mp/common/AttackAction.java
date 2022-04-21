package edu.duke.ece651.mp.common;

import java.io.Serializable;
import java.util.*;
import java.util.stream.*;

public class AttackAction implements Action, Serializable {
  private HashMap<String,Integer> units;
  private String player_name;
  private String from;
  private String to;
  private int resource;

  /**
   * @param units is the value to which from attacks to
   * @param from  is the name of the attacking territory
   * @param to    is the name of the attacked territory
   */
  public AttackAction(HashMap<String,Integer> units, String from, String to, int resource) {
    this.units = units;
    this.from = from;
    this.to = to;
    this.resource = resource;
  }

  /**
   * To compare the different attack class
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
   * Gets the resource required for this action.
   */
  @Override
  public int getResource() {
    return this.resource;
  }

  // returns the number of units being sent to attack
  @Override
  public HashMap<String,Integer> getUnit() {
    return this.units;
  }

  // returns name of player from turn
  @Override
  public String getPlayerName() {
    return this.player_name;
  }

  /**
   * @param player_name is the name of the player who is attacking
   */
  @Override
  public void setPlayerName(String player_name) {
    this.player_name = player_name;
  }

  // return the attacking territory name
  @Override
  public String getTerritoryFrom() {
    return this.from;
  }

  // return the attacked territory name
  @Override
  public String getTerritoryTo() {
    return this.to;
  }

  private HashMap<String,Integer> deepCopy(HashMap<String,Integer> m){
    HashMap<String,Integer> ans = new HashMap<String,Integer>();
    for(String s:m.keySet()){
      //int value = new Integer(m.get(s));
      int value = m.get(s);
      ans.put(s,value);
    }
    return ans;
  }

  /**
   * @param my_world is the map which will be updated based on new ownership
   */
  public void updateWorld(World my_world) {
    // Territory to attack on
    Territory to_territory = my_world.getTerritoryByName(to);
    HashMap<String,Integer> attacking_units = deepCopy(units);
    HashMap<String,Integer> defending_units = deepCopy(to_territory.getUnits());
    if (to_territory.getPlayer().equals(this.player_name)) { // already took over
      for (String u: this.units.keySet()) {
        to_territory.addUnit(u, this.units.get(u));
      }
    } else {

      ArrayList<String> unitnames = this.units.keySet().stream()
        .collect(Collectors.toCollection(ArrayList::new));
      Collections.sort(unitnames);

      ArrayList<String> attackers = this.units.keySet().stream()
        .filter(u -> this.units.get(u) > 0)
        .collect(Collectors.toCollection(ArrayList::new));
      Collections.sort(attackers);

      ArrayList<String> defenders = to_territory.getUnits().keySet().stream()
        .filter(u -> to_territory.getUnits(u) > 0)
        .collect(Collectors.toCollection(ArrayList::new));
      Collections.sort(defenders);

      boolean flip = false;

      while (!(attackers.isEmpty()) && !(defenders.isEmpty())) {
        int a_idx = attackers.size()-1; // Highest attacking unit
        int d_idx = 0; // Lowest defending unit
        if (flip) {
          a_idx = 0;
          d_idx = defenders.size()-1;
        }
        String a = attackers.get(a_idx);
        String d = defenders.get(d_idx);
        
        Unit a_unit = to_territory.getMappings().get(a);
        Unit d_unit = to_territory.getMappings().get(d);
        if (a_unit.getAttackValue() > d_unit.getAttackValue()) { // defender lose
          to_territory.removeUnit(d,1);
          if (to_territory.getUnits(d) == 0) {
            defenders.remove(d_idx);
          }
        } else { // attacker lose
          this.units.put(a,this.units.get(a)-1);
          if (this.units.get(a) == 0) {
            attackers.remove(a_idx);
          }
        }
        flip = !(flip);
      }
      // Resolve territory assignment and append message to log
      if (to_territory.isEmpty()) { // The territory is taken over
        for (String u: this.units.keySet()) { // Add the remaining attacking units
          to_territory.addUnit(u, this.units.get(u));
        }
        to_territory.setPlayer(this.player_name);
        String message = this.player_name + ": ";
        message += "Attack SUCCESSFUL from " + from + " to " + to + "\n";
        for(int i = 0;i<unitnames.size();i++){
          String s = unitnames.get(i);
          int unit_lost = attacking_units.get(s)-to_territory.getUnits().get(s);
          if(unit_lost>0){
            message += unit_lost + " " + s+",";
          }
        }
        message += "were lost while attacking.";
        my_world.addLog(message);
      } else {
        String message = to_territory.getPlayer() + ": ";
        message += "Defend SUCCESSFUL from " + from + "\n ";
        for(int i = 0;i<unitnames.size();i++){
          String s = unitnames.get(i);
          int unit_lost = defending_units.get(s)-to_territory.getUnits().get(s);
          if(unit_lost>0){
            message += unit_lost + " " + s+",";
          }
        }
        message += "were lost while defending.";
        my_world.addLog(message);
      }
    }
  }

  /**
   * @param my_world is the work to deduct values from its territories
   */
  @Override
  public synchronized void doAction(World my_world) {
    // deduct units from source
    Territory from_territory = my_world.getTerritoryByName(from);
    for (String u: this.units.keySet()) {
      from_territory.removeUnit(u, this.units.get(u));
    }
    // Update resource
    my_world.changeResources(this.player_name, "Food", -this.resource);
  }
}
