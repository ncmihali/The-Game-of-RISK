package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

public class TextDisplayInfoTest {
  @Test
  public void test_TextDisplay() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes, true);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("testTextDisplayInfo.txt");
    assertNotNull(expectedStream);
    DefaultWorldFactory f = new DefaultWorldFactory();
    World testWorld = f.createWorld();
    TextDisplayInfo printer = new TextDisplayInfo( out);
    printer.displayMap(testWorld);
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
    bytes.reset(); // clear out bytes for next time around
  }

}
