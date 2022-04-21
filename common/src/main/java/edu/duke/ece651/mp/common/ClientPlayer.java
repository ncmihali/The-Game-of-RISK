package edu.duke.ece651.mp.common;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;

public class ClientPlayer extends Player {
  /**
   * Simple Constructor for this class, calls the parents constructor directly
   * 
   * @param name is the name of the player
   * @param in   is the ObjectinputStream we want to read from
   * @param out  is the ObjectOutputStream we want to write to
   */
  public ClientPlayer(String name, ObjectInputStream in, ObjectOutputStream out) {
    super(name, in, out);
  }
    /**
   * Full Constructor for this class, calls the parents constructor directly
   * 
   * @param name is the name of the player
   * @param in   is the ObjectinputStream we want to read from
   * @param out  is the ObjectOutputStream we want to write to
   */
  public ClientPlayer(String name, ObjectInputStream in, ObjectOutputStream out,Reader inputSource,PrintStream outputSource) {
    super(name, in, out,inputSource,outputSource);
  }
    /**
   * Receive an World class from the client
   * 
   * @return received is the World class we receive from client
   */
  public World receiveFromServer() throws IOException, ClassNotFoundException {
    World received = (World) in.readObject();
    return received;
  }

  public String receiveString() throws IOException, ClassNotFoundException{
    String received = (String)in.readObject();
    return received;
  };

  /**
   * Send an action class to the client
   * 
   * @param action is the action object the server want to send to client
   */
  public void sendToServer(Action action) throws IOException {
    out.reset();
    out.writeObject(action);
  }
  /**
   * Format the prompt to be displayed to the player
   */
  public String formatPrompt(){
    StringBuilder prompt = new StringBuilder();
    prompt.append("You are the ");
    prompt.append(name);
    prompt.append(" player, what would you like to do?\n");
    prompt.append("(M)ove\n(A)ttack\n(D)one\n");
    return prompt.toString();
  }

  /**
   *Read the user's Input and parse into String
   */
  public String readInput(String prompt)throws IOException{
    outputWriter.print(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException("No more input to read!");
    }
    return s;
  }
}
