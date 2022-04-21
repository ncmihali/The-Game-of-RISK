package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class DefaultWorldFactoryTest {
  @Test
  public void test_worldCreation() {
    DefaultWorldFactory test = new DefaultWorldFactory();
    World my_new_world = test.createWorld();

    ArrayList<Territory> iterate = my_new_world.getTerritoryByPlayer("Blue");

    Iterator<Territory> it = iterate.iterator();

    while(it.hasNext()){
      System.out.println(it.next().getName());
    }
  }

  @Test
  public void test_worldCreationEvo2() {
    DefaultWorldFactory test = new DefaultWorldFactory();
    World my_new_world = test.createWorldEvo2();

    ArrayList<Territory> iterate = my_new_world.getTerritoryByPlayer("Blue");

    Iterator<Territory> it = iterate.iterator();

    while(it.hasNext()){
      assertEquals(it.next().getPlayer(), "Blue");
    }
  }

}

