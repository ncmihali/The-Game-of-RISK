package edu.duke.ece651.mp.common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class represents a single player on the server side, it deals with
 * receiving and sending objects to the client this represents
 */
public class ServerPlayer extends Player {
  /**
   * Constructor for this class, calls the parents constructor directly
   * 
   * @param name is the name of the player
   * @param in   is the ObjectinputStream we want to read from
   * @param out  is the ObjectOutputStream we want to write to
   */
  public ServerPlayer(String name, ObjectInputStream in, ObjectOutputStream out) {
    super(name, in, out);
  }

  /**
   * Receive an action class from the client
   * 
   * @return received is the action class we receive from client
   */
  public Action receiveFromClient() throws IOException, ClassNotFoundException {
    Action received = (Action) in.readObject();
    return received;
  }

  public void sendString(String s) throws IOException{
    out.reset();
    out.writeObject(s);
  }

  /**
   * Send an world class to the client
   * 
   * @param worldmap is the world object the server want to send to client
   */
  public void sendToClient(World worldmap) throws IOException {
    out.reset();
    out.writeObject(worldmap);
  }

}
