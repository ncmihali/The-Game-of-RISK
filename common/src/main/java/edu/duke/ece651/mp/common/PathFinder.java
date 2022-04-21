package edu.duke.ece651.mp.common;

import java.util.*;
import java.lang.*;
import java.io.*;

public class PathFinder {
  private HashMap<Territory, Integer> visited;
  private World world;

  /*
   * Constructs a pather finder class
   * @param world: the World containing all the territories.
   * @param player_name: String, the player name
   */
  public PathFinder(World world, String player_name) {
    this.world = world;
    this.visited = new HashMap<Territory, Integer>();
    ArrayList<Territory> myTerritory = world.getTerritoryByPlayer(player_name);
    for (Territory t : myTerritory) {
      this.visited.put(t, -1);
    }
  }

  // Get the territory object by its name.
  private Territory getTerritoryByName(String territory_name) {
    return world.getTerritoryByName(territory_name);
  }

  // Get the Territory of minimum distance.
  private Territory minDistance(Territory currentTerr) {
    int min_size = 99999;
    Territory closest = null;
    for (Territory t : currentTerr.getNeighbors()) {
      if (visited.containsKey(t)) {
        if ((t.getSize() < min_size) && (visited.get(t) == -1)) {
          closest = t;
          min_size = t.getSize();
        }
      }
    }
    return closest;
  }

  /**
   * Find the shortest cost from one territory to another
   * 
   * @param from is the name of territory from
   * @param to   is the name of territory to
   * @return the cost to move to target territory, returns -1 if unreachable
   */

  public int getshorttestCost(String from_name, String to_name) {
    for (Territory t : visited.keySet()) {
      this.visited.put(t, -1);
    }
    Territory from = getTerritoryByName(from_name);
    Territory to = getTerritoryByName(to_name);
    visited.put(from, from.getSize());
    while (visited.get(to) == -1) {
      int min_dist = 99999;
      Territory next = null;
      for (Territory t : visited.keySet()) {
        if (visited.get(t) != -1) {
          Territory temp = minDistance(t);
          if (temp != null) {
            if ((visited.get(t) + temp.getSize()) < min_dist) {
              min_dist = visited.get(t) + temp.getSize();
              next = temp;
            }
          }
        }
      }
      if (next == null) {
        break;
      } else {
        visited.put(next, min_dist);
      }
    }
    return visited.get(to);
  }
}
