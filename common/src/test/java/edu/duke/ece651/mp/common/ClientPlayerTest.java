package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class ClientPlayerTest {
  @Test
  public void test_receive_from_server() throws IOException, FileNotFoundException, ClassNotFoundException {
    FileOutputStream file = new FileOutputStream("file.txt");
    ObjectOutputStream out = new ObjectOutputStream(file);
    FileInputStream fileStream = new FileInputStream("file.txt");
    // Creating an object input stream
    ObjectInputStream in = new ObjectInputStream(fileStream);
    ClientPlayer cliPlayer = new ClientPlayer("Green", in, out);
    Action testAction = createTestAction();
    cliPlayer.sendToServer(testAction);
    Action received = (Action) in.readObject();
    assert (testAction.equals(received));
    cliPlayer.stop();
  }

  @Test
  public void test_send_to_server() throws IOException, FileNotFoundException, ClassNotFoundException {
    FileOutputStream file = new FileOutputStream("file.txt");
    ObjectOutputStream out = new ObjectOutputStream(file);
    FileInputStream fileStream = new FileInputStream("file.txt");
    // Creating an object input stream
    ObjectInputStream in = new ObjectInputStream(fileStream);
    ClientPlayer cliPlayer = new ClientPlayer("Green", in, out);
    World testWorld = createTestWorld();
    out.writeObject(testWorld);
    assert (testWorld.equals(cliPlayer.receiveFromServer()));
    cliPlayer.stop();
  }

  @Test
  public void test_format_prompt() throws IOException{
    ClientPlayer cliPlayer = new ClientPlayer("Green", null, null);
    String prompt = cliPlayer.formatPrompt();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes, true);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("testPrompt.txt");
    assertNotNull(expectedStream);
    out.print(prompt);
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
    bytes.reset(); // clear out bytes for next time around
  }

  @Test
  public void test_read_input() throws IOException,EOFException{
    ClientPlayer cliPlayer = new ClientPlayer("Green", null, null,new BufferedReader(new StringReader("Testing Input\n")),System.out);
    String s = cliPlayer.readInput(null);
    assertEquals("Testing Input",s);
    assertThrows(EOFException.class, ()->cliPlayer.readInput(null));
  }

  public Action createTestAction() {
    Territory one = new BasicTerritory("One", "Green");
    Territory two = new BasicTerritory("Two", "Blue");
    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level1 unit",10);
    Action new_attack = new AttackAction(units, "One", "Two",1);
    return new_attack;
  }

  public World createTestWorld() {
    DefaultWorldFactory f = new DefaultWorldFactory();
    World testWorld = f.createWorld();
    return testWorld;
  }
}
