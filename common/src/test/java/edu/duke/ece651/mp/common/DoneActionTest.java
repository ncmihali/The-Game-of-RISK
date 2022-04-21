package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DoneActionTest {
  @Test
  public void test_equals() {
    Action done = new DoneAction();
    done.setPlayerName("player");
    Action done2 = new DoneAction();
    done2.setPlayerName("player");

    assertEquals(true, done.equals(done2));
    assertEquals(false, done.equals("done"));
  }

  @Test
  public void test_others() {
    Action done = new DoneAction();
    done.setPlayerName("player");
    assertEquals(null, done.getTerritoryTo());
    assertEquals(null, done.getTerritoryFrom());
    assertEquals(null, done.getUnit());
    assertEquals(0, done.getResource());
  }

}
