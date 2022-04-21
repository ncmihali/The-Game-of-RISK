package edu.duke.ece651.mp.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * This class implements Territory with the most basic functionality.
 */
public class BasicTerritory implements Territory, Serializable, Comparable<Territory> {
  private String name;
  private int size; // Cost to travel through this territory
  private HashMap<Unit, Integer> units;
  private HashMap<String, Unit> mappings; // Maps unit name to unit object.
  private HashSet<Territory> neighbors;
  private String owner;
  
  public BasicTerritory(String name, int size, String owner) {
    this(name, initializeUnits(), owner);
    this.size = size;
  }
  
  /**
   * Contructs a BasicTerritory given a name, the type of units and count in a
   * hashmap, and a hashmap of adjacent Territories.
   * 
   * @param name:      name of the Territory in String.
   * @param units:     all unit types and quantity of them in a hashmap.
   * @param neighbors: a set of Territories that is adjacent to this Territory.
   */
  public BasicTerritory(String name, HashMap<Unit, Integer> units, String owner) {
    this.name = name;
    this.size = 0;
    if (units.isEmpty()) {
      Unit u = new BasicUnit();
      units.put(u, 0);
    }
    this.units = units;
    this.owner = owner;
    this.neighbors = new HashSet<Territory>();
    this.mappings = new HashMap<String, Unit>();
    for (Unit u : this.units.keySet()) {
      this.mappings.put(u.getName(), u);
    }
  }

  /**
   * Constructs a BasicTerritory with no unit and neighbors given a name.
   * 
   * @param name: name of the Territory in String.
   */
  public BasicTerritory(String name, String owner) {
    this(name, new HashMap<Unit, Integer>(), owner);
  }

  /**
   * @return name of the Territory in String.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * @return int, size of the territory.
   */
  @Override
  public int getSize() {
    return this.size;
  }

  /**
   * @return Player, the owner of this Territory.
   */
  @Override
  public String getPlayer() {
    return this.owner;
  }

  /**
   * Change the ownership of this Territory.
   * 
   * @param p: Player. the new owner of the Territory.
   */
  @Override
  public void setPlayer(String player_name) {
    this.owner = player_name;
  }

  /**
   * @return all unit names and quantity of them in a hashmap.
   */
  @Override
  public HashMap<String, Integer> getUnits() {
    HashMap<String, Integer> result = new HashMap<String, Integer>();
    for (String unit_name : this.mappings.keySet()) {
      Unit u = this.mappings.get(unit_name);
      result.put(unit_name, this.units.get(u));
    }
    return result;
  }

  /**
   * @param unit_name: unit name to check.
   * @return the quantity of a unit given the String name.
   */
  @Override
  public int getUnits(String unit_name) {
    checkUnitName(unit_name);
    Unit u = this.mappings.get(unit_name);
    return this.units.get(u);
  }

  /**
   * @return a map of the name of the unit to the unit object.
   */
  @Override
  public HashMap<String,Unit> getMappings() {
    return this.mappings;
  }

  @Override
  public boolean isEmpty() {
    for (Unit u: this.units.keySet()) {
      if (this.units.get(u) != 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * @param the neighbor to add.
   */
  /*
   * @Override public void addNeighbor(Territory neighbor) {
   * this.neighbors.add(neighbor); }
   */
  @Override
  public void addNeighbor(Territory... neighbor) {
    for (Territory t : neighbor) {
      this.neighbors.add(t);
    }
  }

  /**
   * @return a set of Territories that is adjacent to this Territory.
   */
  @Override
  public HashSet<Territory> getNeighbors() {
    return this.neighbors;
  }

  /**
   * Checks if another object is equal to this Territory.
   * 
   * @param o: Any object to check.
   * @return True if they are the same Territory. False otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Territory t = (Territory) o;
      HashSet<Territory> their_neigh = t.getNeighbors();
      for (Territory n : neighbors) {
        boolean found = false;
        for (Territory s : their_neigh) {
          if (n.getName().equals(s.getName())) {
            found = true;
          }
        }
        if (!found) {
          return false;
        }
      }
      return this.name.equals(t.getName()) && this.owner.equals(t.getPlayer());
    }
    return false;
  }

  /**
   * Add unit to this territory
   * 
   * @param unit_name is the name of unit to be added
   * @param how_many  is the number of said unit we want to add
   */
  @Override
  public void addUnit(String unit_name, int how_many) {
    checkUnitName(unit_name);
    Unit u = this.mappings.get(unit_name);
    int total = this.units.get(u) + how_many;
    this.units.put(u, total);
  }

  /**
   * Removes units from the territory
   * 
   * @param unit_name is the name of the unit we want to remove
   * @param how_many  is the number of said unit we want to remove
   */
  @Override
  public void removeUnit(String unit_name, int how_many) {
    checkUnitName(unit_name);
    Unit u = this.mappings.get(unit_name);
    int total = this.units.get(u) - how_many;
    this.units.put(u, total);
  }

  /**
   * Make sure the unit type with specified name exists
   */
  private void checkUnitName(String unit_name) {
    if (!this.mappings.containsKey(unit_name)) {
      throw new IllegalArgumentException("Territory does not contain this unit");
    }
  }

  /**
   * The algorithm to arrange territory in an arraylist, by calling
   * collection.sort()
   * 
   * @param t is the territory to compare the current territory to
   */
  @Override
  public int compareTo(Territory t) {
    int toCompare = 100 * t.getUnits("level0 unit") + (t.getNeighbors().size() * t.getName().length());
    int myValue = 100 * this.getUnits("level0 unit") + (this.getNeighbors().size() * this.getName().length());
    /* For Ascending order */
    return myValue - toCompare;
  }

  /**
   * Create the unit mapping to integer for evolution 2.
   */
  private static HashMap<Unit,Integer> initializeUnits() {
    Unit level0 = new UpgradableUnit(0, 0); // level 0 with bonus 0.
    Unit level1 = new UpgradableUnit(1, 1); // level 1 with bonus 1.
    Unit level2 = new UpgradableUnit(2, 3); // level 2 with bonus 3.
    Unit level3= new UpgradableUnit(3, 5); // level 3 with bonus 5.
    Unit level4 = new UpgradableUnit(4, 8); // level 4 with bonus 8
    Unit level5 = new UpgradableUnit(5, 11); // level 5 with bonus 11.
    Unit level6 = new UpgradableUnit(6, 15); // level 6 with bonus 15.
    HashMap<Unit,Integer> units = new HashMap<Unit,Integer>();
    units.put(level0,0);
    units.put(level1,0);
    units.put(level2,0);
    units.put(level3,0);
    units.put(level4,0);
    units.put(level5,0);
    units.put(level6,0);
    return units;
  }

  /**
   * Removes all the unit in the territory by setting to 0.
   */
  public void removeallUnits(){
    this.units = initializeUnits();
  }
}
