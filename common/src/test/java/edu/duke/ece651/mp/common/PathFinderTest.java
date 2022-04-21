package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class PathFinderTest {
  @Test
  public void test_findcost1() {
    World testWorld = createTestWorld1();
    PathFinder finder = new PathFinder(testWorld, "Green");
    assertEquals(16, finder.getshorttestCost("Narnia", "Gondor"));
    assertEquals(15, finder.getshorttestCost("Narnia", "Oz"));
    assertEquals(12, finder.getshorttestCost("Midkemia", "Gondor"));
    assertEquals(finder.getshorttestCost("Gondor", "Oz"), finder.getshorttestCost("Oz", "Gondor"));
  }

  @Test
  public void test_findcost2() {
    World testWorld = createTestWorld2();
    PathFinder finder = new PathFinder(testWorld, "Blue");
    assertEquals(21, finder.getshorttestCost("Roshar", "Midkemia"));
    assertEquals(-1, finder.getshorttestCost("Roshar", "Hogwarts"));
  }

  public World createTestWorld1() {
    Territory narnia = new BasicTerritory("Narnia", 4, "Green");
    Territory midkemia = new BasicTerritory("Midkemia", 5, "Green");
    Territory oz = new BasicTerritory("Oz", 6, "Green");
    Territory gondor = new BasicTerritory("Gondor", 7, "Green");

    /* below are Blue territories */
    Territory hogwarts = new BasicTerritory("Hogwarts", 3, "Blue");
    Territory elantris = new BasicTerritory("Elantris", 9, "Blue");
    Territory scadrial = new BasicTerritory("Scadrial", 2, "Blue");
    Territory roshar = new BasicTerritory("Roshar", 7, "Blue");

    narnia.addNeighbor(elantris, midkemia);
    midkemia.addNeighbor(oz, elantris, scadrial, narnia, gondor);
    oz.addNeighbor(midkemia, gondor, hogwarts, scadrial);
    gondor.addNeighbor(oz, midkemia);
    hogwarts.addNeighbor(scadrial, oz);
    scadrial.addNeighbor(hogwarts, roshar, elantris, midkemia, oz);
    elantris.addNeighbor(narnia, midkemia, scadrial, roshar);
    roshar.addNeighbor(scadrial, elantris);

    Territory arr[] = { narnia, midkemia, oz, gondor, hogwarts, scadrial, elantris, roshar };
    HashSet<Territory> list_of_territories = new HashSet<Territory>(Arrays.asList(arr));

    /* now to create the world */
    World new_world = new World(list_of_territories);
    return new_world;
  }

  public World createTestWorld2() {
    Territory narnia = new BasicTerritory("Narnia", 4, "Green");
    Territory midkemia = new BasicTerritory("Midkemia", 5, "Blue");
    Territory oz = new BasicTerritory("Oz", 6, "Green");
    Territory gondor = new BasicTerritory("Gondor", 7, "Green");

    /* below are Blue territories */
    Territory hogwarts = new BasicTerritory("Hogwarts", 3, "Blue");
    Territory elantris = new BasicTerritory("Elantris", 9, "Blue");
    Territory scadrial = new BasicTerritory("Scadrial", 2, "Green");
    Territory roshar = new BasicTerritory("Roshar", 7, "Blue");

    narnia.addNeighbor(elantris, midkemia);
    midkemia.addNeighbor(oz, elantris, scadrial, narnia, gondor);
    oz.addNeighbor(midkemia, gondor, hogwarts, scadrial);
    gondor.addNeighbor(oz, midkemia);
    hogwarts.addNeighbor(scadrial, oz);
    scadrial.addNeighbor(hogwarts, roshar, elantris, midkemia, oz);
    elantris.addNeighbor(narnia, midkemia, scadrial, roshar);
    roshar.addNeighbor(scadrial, elantris);

    Territory arr[] = { narnia, midkemia, oz, gondor, hogwarts, scadrial, elantris, roshar };
    HashSet<Territory> list_of_territories = new HashSet<Territory>(Arrays.asList(arr));

    /* now to create the world */
    World new_world = new World(list_of_territories);
    return new_world;
  }

}
