package edu.duke.ece651.mp.common;


public interface WorldFactory {
  /** 
      used to create a new world
  **/
  public World createWorld();
  /**
     used to create a new world for Evo2
  */
  public World createWorldEvo2();
}

