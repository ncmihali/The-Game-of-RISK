package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.Socket;

import org.junit.jupiter.api.Test;

public class ServerConnectionTest {
  @Test
  public void test_connection() throws IOException{
    ServerConnection server = new ServerConnection(6666);
    Socket ableToConnect = new Socket("localhost", 6666);
    server.acceptConnection();
      assert (ableToConnect.isConnected());
      // close the `clientSocket`
      ableToConnect.close();
    
    // close the 'serverSocket'
    server.stop();
  try {
      // now that `serverSocket` is closed
      // try to connect another `clientSocket` to the same `serverSocket`
      new Socket("localhost", 6666);
    } catch (Exception e) {
      // assert that the exception is thrown and is the right exception
      assertEquals("Connection refused", e.getMessage().trim());
    }
  }

  @Test
  public void test_sendto_multiple_clients() throws IOException{
    ServerConnection server = new ServerConnection(6666);
    ClientConnection client1 = new ClientConnection("localhost", 6666);
    ClientConnection client2 = new ClientConnection("localhost", 6666);
    server.acceptConnection();
    server.send(0,"Hello Client1!");
    server.acceptConnection();
    server.send(1,"Hello Client2!");
    client1.stop();
    client2.stop();
    server.stop();
  }
}
