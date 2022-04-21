package edu.duke.ece651.mp.common;

import java.util.*;
import java.io.Serializable;

/**
 * This class represent the map to be used in the game
 */
public class World implements Serializable {
  private HashSet<Territory> territories;
  private ArrayList<String> player_names;
  public int turnnum;
  private HashMap<String, HashMap<String, Integer>> resources;
  private HashMap<String,Integer> techLevel;
  private ArrayList<String> activity_log;

  /**
   * Constructor that constructs the world from a hashset of territories
   * 
   * @param territories is the set of territories we want to construct the world
   *                    with
   */
  public World(HashSet<Territory> territories) {
    this.player_names = new ArrayList<String>();
    this.territories = territories;
    this.activity_log = new ArrayList<String>();
    player_names.add("Red");
    player_names.add("Blue");
    resources = initializeResources();
    techLevel = initTechLevel();
    turnnum = 1;
  }

  // Initialize technology level for both players.
  private HashMap<String,Integer> initTechLevel(){
    HashMap<String,Integer> ans = new HashMap<String,Integer>();
    ans.put("Red",1);
    ans.put("Blue",1);
    return ans;
  }

  // level up the technology level
  public void techLevelup(String name){
    int level = techLevel.get(name);
    techLevel.put(name,level+1);
  }

  // get the technology level based on a player name.
  public int getTechLevel(String name){
    return techLevel.get(name);
  }

  /**
   * @return the activity log.
   */
  public ArrayList<String> getActivityLog() {
    return this.activity_log;
  }

  /**
   * @param String of the log message to add.
   */
  public void addLog(String log_message) {
    if(activity_log.size()<turnnum){
      activity_log.add("----------TURN " + turnnum + "----------\n");
    }
    String newmsg = activity_log.get(turnnum-1).concat(log_message+"\n");
    activity_log.set(turnnum-1, newmsg);
  }

  // Initialize resource for both players.
  private HashMap<String, HashMap<String, Integer>> initializeResources() {
    HashMap<String, HashMap<String, Integer>> res = new HashMap<String, HashMap<String, Integer>>();
    HashMap<String, Integer> temp = new HashMap<String, Integer>();
    temp.put("Food", 500);
    temp.put("Electricity", 500);
    HashMap<String, Integer> temp2 = new HashMap<String, Integer>();
    temp2.put("Food", 500);
    temp2.put("Electricity", 500);

    res.put("Red", temp);
    res.put("Blue", temp2);
    return res;
  }

  // get the resource by player name
  public HashMap<String,Integer> getResources(String name){
    return resources.get(name);
  }

  // update a resource given the player's name, resouce type, and amount
  public void changeResources(String player_name,String type, Integer amount){
    int result = resources.get(player_name).get(type)+amount;
    resources.get(player_name).put(type,result);
  }

  /**
   * Get the arraylist of playername of the game
   * 
   * @return the arraylist consists of two player names
   */
  public ArrayList<String> getPlayerName() {
    return player_names;
  }

  /**
   * To determine whether a winner is declared
   * 
   * @return the name of the winner
   */
  public String hasEnd() {
    if (!this.territories.isEmpty()) {
      Iterator<Territory> iter = this.territories.iterator();
      String winner = iter.next().getPlayer();
      while (iter.hasNext()) {
        Territory t = iter.next();
        if (!winner.equals(t.getPlayer())) {
          return null; // Game has not end yet
        }
      }
      return winner;
    }
    return null;
  }

  /**
   * Get the name of territory that each player currently poccesses
   * 
   * @param player_name is the name of the player whose territories we want to
   *                    print
   * @return an arraylist containing the list of territory names that the player
   *         has
   */
  public ArrayList<Territory> getTerritoryByPlayer(String player_name) {
    HashSet<Territory> player_terr = new HashSet<Territory>();

    Iterator<Territory> it = territories.iterator();

    while (it.hasNext()) {
      Territory temp_territory = it.next();
      if (temp_territory.getPlayer().equals(player_name)) {
        player_terr.add(temp_territory);
      }
    }
    ArrayList<Territory> ans = new ArrayList<Territory>(player_terr);
    Collections.sort(ans);
    return ans;
  }

  /**
   * Returns the set of territories
   */
  public HashSet<Territory> getTerritories() {
    return territories;
  }

  /**
   * Returns a reference to the territory class with the name as input
   * 
   * @param name of the territory we want to obtain
   */
  public Territory getTerritoryByName(String name) {
    for (Territory t : territories) {
      if (t.getName().equals(name)) {
        return t;
      }
    }
    return null;
  }

  /**
   * The method to compare two world objects
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      World u = (World) o;
      HashSet<Territory> theirs = u.getTerritories();
      for (Territory t : territories) {
        boolean found = false;
        for (Territory s : theirs) {
          if (t.equals(s)) {
            found = true;
          }
        }
        if (!found) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  /**
   * Calculates the resource consumption for 1 unit to travel through the two
   * territories. Note that a path must contain all the same player's territory.
   * 
   * @param from: Territory to travel from
   * @param to:   Territory to travel to
   * @return a int, representing the cost of moving one unit. returns infinty if
   *         there is no path between.
   */
  public int getPathCost(Territory from, Territory to) {
    HashMap<Territory, Territory> back_link = new HashMap<Territory, Territory>();
    back_link.put(from, null);
    // BFS to find the neighbor
    Queue<Territory> toVisit = new LinkedList<Territory>();
    toVisit.add(from);
    HashSet<Territory> visited = new HashSet<Territory>();
    while (!toVisit.isEmpty()) {
      Territory t = toVisit.poll();
      if (t.equals(to)) {
        break;
      }
      for (Territory neigh : t.getNeighbors()) {
        if (!visited.contains(neigh) && neigh.getPlayer().equals(from.getPlayer())) {
          toVisit.add(neigh);
          back_link.put(neigh, t);
        }
      }
      visited.add(t);
    }
    if (!(back_link.containsKey(to))) { // No path
      return Integer.MAX_VALUE;
    }
    Territory trace = to;
    int cost = 0;
    while (!(trace.equals(from))) {
      cost += trace.getSize();
      trace = back_link.get(trace);
    }
    return cost + trace.getSize();
  }
}
