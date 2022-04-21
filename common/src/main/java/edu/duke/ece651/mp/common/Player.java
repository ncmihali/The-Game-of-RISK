package edu.duke.ece651.mp.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;

public class Player {
  protected String name;
  final BufferedReader inputReader;
  final PrintStream outputWriter;
  protected ObjectInputStream in;
  protected ObjectOutputStream out;

  /**
   * Simple Constructor for the Player class, set inputreader to be system.in
   * 
   * @param name is the player_name
   * @param in   is the ObjectInputStream to read from
   * @param out  is the ObjectOutputStream to send
   */
  public Player(String name, ObjectInputStream in, ObjectOutputStream out) {
    this.name = name;
    this.inputReader= new BufferedReader(new InputStreamReader(System.in));
    this.outputWriter = System.out;
    this.out = out;
    this.in = in;
  }
  /**
   * Full Constructor for the Player class
   * 
   * @param name is the player_name
   * @param in   is the ObjectInputStream to read from
   * @param out  is the ObjectOutputStream to send
   */
  public Player(String name, ObjectInputStream in, ObjectOutputStream out, Reader inputSource,PrintStream outputSource){
    this.name = name;
    this.inputReader= (BufferedReader)inputSource;
    this.outputWriter = outputSource;
    this.out = out;
    this.in = in;
  }
  /**
   * Get the name of the Player
   */
  public String getName() {
    return name;
  }
  /**
   * Close the Input and output stream
   */
  public void stop()throws IOException{
    in.close();
    out.close();
  }
}
