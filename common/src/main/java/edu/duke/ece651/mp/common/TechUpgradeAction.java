package edu.duke.ece651.mp.common;

import java.io.Serializable;
import java.util.HashMap;

public class TechUpgradeAction implements Action,Serializable {
  int resource;
  int currentLevel;
  String owner_name;
  HashMap<Integer,Integer> upgradeCosts;

  /**
   * @param currentLevel is the current technology level.
   */
  public TechUpgradeAction(int currentLevel){
    this.currentLevel = currentLevel;
    this.upgradeCosts = initUpgradeCostMap();
    if(upgradeCosts.get(currentLevel)==null){
      this.resource = 0;
    }else{
    this.resource = upgradeCosts.get(currentLevel);
    }
  }

  // Initialize the upgrade cost
  private HashMap<Integer,Integer> initUpgradeCostMap(){
    HashMap<Integer,Integer> ans = new HashMap<Integer,Integer>();
    ans.put(1,50);
    ans.put(2,75);
    ans.put(3,125);
    ans.put(4,200);
    ans.put(5,300);
    return ans;
  }

  // returns the number of units being sent to attack
  @Override
  public HashMap<String, Integer> getUnit() {
    // TODO Auto-generated method stub
    return null;
  }

  // get the resource required
  @Override
  public int getResource() {
    return resource;
  }

  // returns name of player from turn
  @Override
  public String getPlayerName() {
    // TODO Auto-generated method stub
    return owner_name;
  }

  /**
   * @param player_name is the name of the player who is attacking
   */
  @Override
  public void setPlayerName(String player_name) {
    this.owner_name = player_name;
  }

  // return the attacking territory name
  @Override
  public String getTerritoryFrom() {
    // TODO Auto-generated method stub
    return null;
  }

  // return the attacked territory name
  @Override
  public String getTerritoryTo() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * @param my_world is the map which will be updated based on new ownership
   */
  @Override
  public void updateWorld(World my_world) {
    my_world.techLevelup(owner_name);
    int to_level = currentLevel+1;
    my_world.addLog(this.owner_name + ": Upgraded technology level from level" +
                    currentLevel + " to level" + to_level + " with " + this.resource + " electricity.");
  }

  /**
   * @param my_world is the work to deduct values from its territories
   */
  @Override
  public void doAction(World my_world) {
    if(resource == 0){
      return;
    }
    my_world.changeResources(owner_name, "Electricity", -resource);
  }

}
