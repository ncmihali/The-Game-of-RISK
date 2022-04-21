package edu.duke.ece651.mp.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

public class ClientConnectionTest {

  @Test
  public void test_get_clientSocket() throws IOException{
    ServerConnection server = new ServerConnection(6666);
    ClientConnection client = new ClientConnection("localhost", 6666);
    server.acceptConnection();
    BufferedReader client_in = new BufferedReader(new InputStreamReader(client.getClientSocket().getInputStream()));
    PrintWriter server_out =new PrintWriter(server.getClientSocket(0).getOutputStream(), true);
    server_out.println("Hello Client!");
    assertEquals("Hello Client!",client_in.readLine());
    client.stop();
    server.stop();
  }

}

