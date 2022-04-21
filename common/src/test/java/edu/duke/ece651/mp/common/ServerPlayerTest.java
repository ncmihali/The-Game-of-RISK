package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class ServerPlayerTest {

  @Test
  public void test_receive_from_client() throws IOException, FileNotFoundException, ClassNotFoundException {
    FileOutputStream file = new FileOutputStream("file.txt");
    ObjectOutputStream out = new ObjectOutputStream(file);
    FileInputStream fileStream = new FileInputStream("file.txt");
    // Creating an object input stream
    ObjectInputStream in = new ObjectInputStream(fileStream);
    ServerPlayer serPlayer = new ServerPlayer("Green", in, out);
    Action testAction = createTestAction();
    out.writeObject(testAction);
    assert (testAction.equals(serPlayer.receiveFromClient()));
    serPlayer.stop();
  }

  public Action createTestAction() {
    Territory one = new BasicTerritory("One", "Green");
    Territory two = new BasicTerritory("Two", "Blue");
    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level1 unit",10);
    Action new_attack = new AttackAction(units, "One", "Two",1);
    return new_attack;
  }

  @Test
  public void test_send_to_client() throws IOException, FileNotFoundException, ClassNotFoundException {
    World testWorld = createTestWorld();
    FileOutputStream file = new FileOutputStream("file.txt");
    ObjectOutputStream out = new ObjectOutputStream(file);
    FileInputStream fileStream = new FileInputStream("file.txt");
    // Creating an object input stream
    ObjectInputStream in = new ObjectInputStream(fileStream);
    ServerPlayer serPlayer = new ServerPlayer("Green", in, out);
    serPlayer.sendToClient(testWorld);
    World received = (World) in.readObject();
    /*
     * TextDisplayInfo printer = new TextDisplayInfo(testWorld, System.out);
     * printer.displayMap(); TextDisplayInfo printer1 = new
     * TextDisplayInfo(received, System.out); printer1.displayMap(); while(true){ }
     */
    assert (testWorld.equals(received));
    serPlayer.stop();
  }

  public World createTestWorld() {
    DefaultWorldFactory f = new DefaultWorldFactory();
    World testWorld = f.createWorld();
    return testWorld;
  }

  @Test
  public void test_getName() {
    ServerPlayer serPlayer = new ServerPlayer("Green", null, null);
    assertEquals("Green", serPlayer.getName());
  }

  @Test
  public void test_stop() throws IOException,FileNotFoundException{
    World testWorld = createTestWorld();
    FileOutputStream file = new FileOutputStream("file.txt");
    ObjectOutputStream out = new ObjectOutputStream(file);
    FileInputStream fileStream = new FileInputStream("file.txt");
    // Creating an object input stream
    ObjectInputStream in = new ObjectInputStream(fileStream);
    ServerPlayer serPlayer = new ServerPlayer("Green", in, out);
    serPlayer.stop();
    assertThrows(IOException.class, ()->serPlayer.sendToClient(testWorld));
    assertThrows(IOException.class, ()->serPlayer.receiveFromClient());
  }
}
