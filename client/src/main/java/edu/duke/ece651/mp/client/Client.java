package edu.duke.ece651.mp.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.duke.ece651.mp.common.ClientConnection;
import edu.duke.ece651.mp.common.ClientPlayer;
import edu.duke.ece651.mp.common.World;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class deal with all the client side functionalities by calling
 * appropriate classes under shared directory
 */
public class Client extends Application {
  public ClientConnection connection;
  public ClientPlayer player;
  public World world;
  static Controller controller;

  @Override
  public void start(Stage primaryStage) throws Exception{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("riskdesign.fxml"));
    Parent root = loader.load();
    controller = (Controller)loader.getController();
    primaryStage.setTitle("RISK");
    primaryStage.setScene(new Scene(root, 932, 792));
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  /**
   * Constructor for the Client which establish connection with the server and
   * obtain the objectInput and output stream from the socket
   * 
   * @param hostname   is the hostname of the server we want to connect
   * @param portNumber is the portnumber of the server we want to connect
   */
  public Client(String hostname, String portNumber) throws IOException, ClassNotFoundException {
    int port = Integer.parseInt(portNumber);
    this.connection = new ClientConnection(hostname, port);
    Socket clientSocket = connection.getClientSocket();
    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
    String name = (String) in.readObject();
    this.player = new ClientPlayer(name, in, out);
    this.world = player.receiveFromServer();
  }
  
  /**
   * Constructor for the client app for testing purposes
   */
  public Client(){
  }

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    launch(args);
  }



  /**
   * Update the world attribute of the client object to the input world object
   * 
   * @param world is the world object we want the client to have
   */
  public void updateClientWorld(World world) {
    this.world = world;
  }


  /**
   * clean up the connections and IOStreams
   */
  public void endGame() throws IOException {
    this.player.stop();
    this.connection.stop();
  }

}


