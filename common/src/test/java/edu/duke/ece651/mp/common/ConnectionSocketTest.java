package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ConnectionSocketTest {
  @Disabled
  @Test
  public void socketReceiveandSend() throws IOException,ClassNotFoundException {
    /* ServerConnection server = new ServerConnection(0);
    ClientConnection client = new ClientConnection("localhost",server.getPort());
    server.acceptConnection();
    System.out.println("after accept");
    TestMessage testmsg = new TestMessage("Hello World!");
    server.getIOStream();
    client.getIOStream();
    System.out.println("after get");
    server.send(testmsg);
    assertEquals(testmsg,client.receive());
    client.send(testmsg);
    assertEquals(testmsg,server.receive());
    client.stop();
    server.stop(); */
  }
}
