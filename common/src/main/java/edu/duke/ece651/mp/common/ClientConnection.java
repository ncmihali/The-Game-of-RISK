package edu.duke.ece651.mp.common;

import java.io.IOException;
import java.net.Socket;

/**
 * This class is used by the client to connect with the server
 */
public class ClientConnection {
  private Socket clientSocket;

  /**
   * Start a socket connecting to the client with the specified hostname and port
   * number
   * 
   * @param ip   is the hostname of the server we want to connect to
   * @param port is the port number of the listening port of the server
   */
  public ClientConnection(String ip, int port) throws IOException {
    clientSocket = new Socket(ip, port);
  }

  /**
   * Close all connection with the server
   */
  public void stop() throws IOException {
    clientSocket.close();
  }
  /**
   * get clientsocket
   */
  public Socket getClientSocket(){
    return clientSocket;
  }
}

