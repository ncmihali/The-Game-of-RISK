package edu.duke.ece651.mp.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import edu.duke.ece651.mp.common.*;

/**
 * This class handles all the functionality of a game server, it will send
 * clients world map at the start of each turn, validate the action that the
 * client wants to perform and update the local world respectively
 */
public class Server {
  public ServerConnection connection;
  public ServerPlayer player1;
  public ServerPlayer player2;
  public World world;
  public ActionRuleChecker rule;
  public ArrayList<Action> pending_actions;

  /**
   * Constructor that create a listensocket and accept 2 client players, it will
   * then extract out the respective IOStream for later use
   */
  public Server() throws IOException, ClassNotFoundException {
    this.connection = new ServerConnection(6666);
    connection.acceptConnection();
    connection.acceptConnection();
    Socket clientSocket1 = connection.getClientSocket(0);
    Socket clientSocket2 = connection.getClientSocket(1);
    ObjectOutputStream out = new ObjectOutputStream(clientSocket1.getOutputStream());
    ObjectInputStream in = new ObjectInputStream(clientSocket1.getInputStream());
    this.player1 = new ServerPlayer("Red", in, out);
    this.player1.sendString("Red");
    ObjectOutputStream out1 = new ObjectOutputStream(clientSocket2.getOutputStream());
    ObjectInputStream in1 = new ObjectInputStream(clientSocket2.getInputStream());
    this.player2 = new ServerPlayer("Blue", in1, out1);
    this.player2.sendString("Blue");
    DefaultWorldFactory f = new DefaultWorldFactory();
    this.world = f.createWorldEvo2();
    this.rule = ActionRuleChecker.makeActionRuleChecker();
    this.pending_actions = new ArrayList<Action>();
  }

  public static void main(String[] args) {
    try {
      Server server = new Server();
      server.player1.sendToClient(server.world);
      server.player2.sendToClient(server.world);
      while (server.world.hasEnd() == null) {
        Runnable r1 = () -> server.playOneTurn(server.player1, 0);
        Runnable r2 = () -> server.playOneTurn(server.player2, 1);
        Thread th1 = new Thread(r1);
        Thread th2 = new Thread(r2);
        th1.start();
        th2.start();
        th1.join(); // Wait for all players to finish commiting actions
        th2.join();
        for (Action a : server.pending_actions) {
          if (a instanceof MeteorShowerAction) {
            a.updateWorld(server.world);
          }
        }
        for (Action a : server.pending_actions) {
            a.updateWorld(server.world);
        }
        server.growUnit();
        server.growResources();
        server.world.turnnum++;
        server.player1.sendToClient(server.world);
        server.player2.sendToClient(server.world);
        server.pending_actions = new ArrayList<Action>();
      }
      server.endGame();
    } catch (Exception e) {
      System.out.println("Client disconnected, game ended");
      //server.endGame();
    }

  }

  /**
   * Increase units of every territory by 1, typically performed at the end of
   * turn.
   */
  public void growUnit() {
    int green_count = 0;
    int blue_count = 0;
    for (Territory t : this.world.getTerritories()) {
      t.addUnit("level0 unit", 1);
      if (t.getPlayer().equals("Red")) {
        green_count++;
      } else { //Blue
        blue_count++;
      }
    }
    String green_message = "Red: Gained 1 level0 unit for each territory owned, in total: " + green_count + ".";
    String blue_message = "Blue: Gained 1 level0 unit for each territory owned, in total: " + blue_count + ".";
    this.world.addLog(green_message);
    this.world.addLog(blue_message);
  }

  /*
   * Reward players with food and electricity based on the territories they own.
   */
  public void growResources() {
    int green_count = 0;
    int blue_count = 0;
    for (Territory t : this.world.getTerritories()) {
      String owner = t.getPlayer();
      this.world.changeResources(owner, "Food", t.getSize());
      this.world.changeResources(owner, "Electricity", t.getSize());
      if (owner.equals("Red")) {
        green_count += t.getSize();
      } else { //Blue
        blue_count += t.getSize();
      }
    }
    String green_message = "Red: Gained " + green_count + " food and electricity resource based on total territory size.";
    String blue_message = "Blue: Gained " + blue_count + " food and electricity resource based on total territory size.";
    this.world.addLog(green_message);
    this.world.addLog(blue_message);
  }

  /**
   * Server receive responses from clients until a done object is received, the
   * server will check for validity of these objects and then update local world
   * accrodingly. The attack action needs to be performed at the end of the turn
   * 
   * @param player is the serverplayer class incharge of writing and reading from
   *               IOstream
   * @param index  represents which client to interact with 0 for green player, 1
   *               for blue player
   */
  public void playOneTurn(ServerPlayer player, int index) {
    try {
      this.playOneTurnHelper(player, index);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Helper function for playonturn, has exact input as playoneturn
   */
  public void playOneTurnHelper(ServerPlayer player, int index) throws IOException, ClassNotFoundException {
    //TextDisplayInfo printer = new TextDisplayInfo(System.out);
    Action a = player.receiveFromClient();
    a.setPlayerName(player.getName());
    while (!(a instanceof DoneAction)) {
      String error_msg = this.rule.checkAction(a, this.world);
      if (error_msg != null) {
        player.sendString(error_msg);
      } else {
        this.pending_actions.add(a);
        a.doAction(this.world);
        player.sendString("Action successful!");
        //printer.displayMap(world);
      }
      a = player.receiveFromClient();
      a.setPlayerName(player.getName());
    }
  }

  /**
   * perform cleanup on server sockets and IOstreams
   */
  public void endGame() throws IOException, ClassNotFoundException {
    this.player1.stop();
    this.player2.stop();
    this.connection.stop();
  }
}
