package edu.duke.ece651.mp.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class in charge of creating a listening socket and accepting client
 * connection
 */
public class ServerConnection {
  private ArrayList<PrintWriter> out;
  protected ServerSocket serverSocket;
  protected ArrayList<Socket> clientSockets;

  /**
   * Start a listening socket at the port specified by user
   * 
   * @param port is the port the user want to listening on
   */
  public ServerConnection(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    clientSockets = new ArrayList<Socket>();
    out = new ArrayList<PrintWriter>();
  }

  /**
   * Accept connection from the client, get its outputstream
   */
  public void acceptConnection() throws IOException {
    Socket clientSocket = serverSocket.accept();
    clientSockets.add(clientSocket);
    PrintWriter newout = new PrintWriter(clientSocket.getOutputStream(), true);
    out.add(newout);
  }

  /**
   * Send text message to the client, then close the outputstream
   * 
   * @param msg is the String the server want to send
   */
  public void send(int i,String msg) throws IOException {
    out.get(i).println(msg);
    //out.close();
  }

  /**
   * Close all connection with the client
   */
  public void stop() throws IOException {
    for(PrintWriter p:out){
      p.close();
    }
    for (Socket s : clientSockets) {
      s.close();
    }
    serverSocket.close();
  }
  /**
   * get clientsocket
   */
  public Socket getClientSocket(int i){
    return clientSockets.get(i);
  }
}
