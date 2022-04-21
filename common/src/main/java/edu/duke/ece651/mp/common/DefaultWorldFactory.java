package edu.duke.ece651.mp.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * This class creates a default world map to be used in the game
 */
public class DefaultWorldFactory implements WorldFactory {
  @Override
  public World createWorld() {
    /* this will create a new world with territories and players */

    /* below are Green territories */
    String color1 = "Red";
    Territory narnia = new BasicTerritory("Narnia", unit_helper(1), color1);
    Territory midkemia = new BasicTerritory("Midkemia", unit_helper(6), color1);
    Territory oz = new BasicTerritory("Oz", unit_helper(4), color1);
    Territory gondor = new BasicTerritory("Gondor", unit_helper(5), color1);
    Territory mordor = new BasicTerritory("Mordor", unit_helper(8), color1);
    Territory hogwarts = new BasicTerritory("Hogwarts", unit_helper(3), color1);

    /* below are Blue territories */
    Territory elantris = new BasicTerritory("Elantris", unit_helper(15), "Blue");
    Territory scadrial = new BasicTerritory("Scadrial", unit_helper(22), "Blue");
    Territory roshar = new BasicTerritory("Roshar", unit_helper(18), "Blue");

    /* now we will add all the neighbors to each territory */
    narnia.addNeighbor(elantris, midkemia);
    midkemia.addNeighbor(oz, elantris, scadrial, narnia);
    oz.addNeighbor(midkemia, gondor, mordor, scadrial);
    gondor.addNeighbor(oz, mordor);
    mordor.addNeighbor(gondor, oz, hogwarts, scadrial);
    hogwarts.addNeighbor(mordor, scadrial, roshar);
    scadrial.addNeighbor(hogwarts, roshar, elantris, midkemia, oz, mordor);
    elantris.addNeighbor(narnia, midkemia, scadrial, roshar);
    roshar.addNeighbor(hogwarts, scadrial, elantris);

    // group the territories into a hashset
    Territory arr[] = { narnia, midkemia, oz, gondor, mordor, hogwarts, scadrial, elantris, roshar };
    HashSet<Territory> list_of_territories = new HashSet<Territory>(Arrays.asList(arr));

    /* now to create the world */
    World new_world = new World(list_of_territories);
    return new_world;
  }

  /**
   * This class creates a default world map to be used in the game for evolution 2
   */
  
  @Override
  public World createWorldEvo2() {
    /* this will create a new world with territories and players */

    /* below are Green territories */
    String color1 = "Red";
    Territory narnia = new BasicTerritory("Narnia", 3, color1);
    Territory midkemia = new BasicTerritory("Midkemia", 4, color1);
    Territory oz = new BasicTerritory("Oz", 5, color1);
    Territory gondor = new BasicTerritory("Gondor", 6, color1);

    /* below are Blue territories */
    Territory hogwarts = new BasicTerritory("Hogwarts", 3, "Blue");
    Territory elantris = new BasicTerritory("Elantris", 4, "Blue");
    Territory scadrial = new BasicTerritory("Scadrial", 5, "Blue");
    Territory roshar = new BasicTerritory("Roshar", 6, "Blue");

    // adding units to each territory
    addLevelUnits(narnia);
    addLevelUnits(midkemia);
    addLevelUnits(oz);
    addLevelUnits(gondor);
    addLevelUnits(hogwarts);
    addLevelUnits(elantris);
    addLevelUnits(scadrial);
    addLevelUnits(roshar);

    /* now we will add all the neighbors to each territory */
    narnia.addNeighbor(elantris, midkemia);
    midkemia.addNeighbor(oz, scadrial, narnia, gondor);
    oz.addNeighbor(midkemia, gondor, hogwarts);
    gondor.addNeighbor(oz, midkemia);
    hogwarts.addNeighbor(scadrial, oz);
    scadrial.addNeighbor(hogwarts, roshar, elantris, midkemia);
    elantris.addNeighbor(narnia, scadrial, roshar);
    roshar.addNeighbor(scadrial, elantris);

    // group the territories into a hashset
    Territory arr[] = { narnia, midkemia, oz, gondor, hogwarts, scadrial, elantris, roshar};
    HashSet<Territory> list_of_territories = new HashSet<Territory>(Arrays.asList(arr));

    /* now to create the world */
    World new_world = new World(list_of_territories);
    return new_world;
  }

  /**
   * Randomly adds units to each level in a territory.
   */
  public void addLevelUnits(Territory t){
    Random r = new Random();
    for(int i = 0; i < 7; i++){
      int temp = r.nextInt(20);
      t.addUnit("level" + i + " unit", temp);
    }
  }

  /**
   * Method that adds the number of Basic unit to the territory
   * 
   * @param unit_count the number of unit to be added to the territory
   */
  private HashMap<Unit, Integer> unit_helper(int unit_count) {
    HashMap<Unit, Integer> mapping = new HashMap<Unit, Integer>();
    mapping.put(new BasicUnit(), unit_count);
    return mapping;
  }

}
