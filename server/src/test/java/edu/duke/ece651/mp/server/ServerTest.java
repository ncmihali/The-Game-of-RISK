package edu.duke.ece651.mp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import edu.duke.ece651.mp.common.Action;
import edu.duke.ece651.mp.common.AttackAction;
import edu.duke.ece651.mp.common.ClientConnection;
import edu.duke.ece651.mp.common.DoneAction;
import edu.duke.ece651.mp.common.MoveAction;


public class ServerTest {

  @Test
  @Timeout(2500)
  public void testServer() throws IOException, InterruptedException, ClassNotFoundException {
    Runnable r1 = () -> Server.main(new String[0]);
    Thread th1 = new Thread(r1);
    th1.start();
    TimeUnit.SECONDS.sleep(1);
    ClientConnection client1 = new ClientConnection("localhost", 6666);
    ClientConnection client2 = new ClientConnection("localhost", 6666);
    Socket clientSocket1 = client1.getClientSocket();
    ObjectOutputStream out1 = new ObjectOutputStream(clientSocket1.getOutputStream());
    ObjectInputStream in1 = new ObjectInputStream(clientSocket1.getInputStream());
    Socket clientSocket2 = client2.getClientSocket();
    ObjectOutputStream out2 = new ObjectOutputStream(clientSocket2.getOutputStream());
    ObjectInputStream in2 = new ObjectInputStream(clientSocket2.getInputStream());
    in1.readObject();
    in2.readObject();
    in1.readObject();
    in2.readObject();
    Action testAction1 = new MoveAction(unithelper(10), "Scadrial", "Roshar",100);
    System.out.println("13");
    out1.writeObject(testAction1);
    System.out.println("14");
    in1.readObject();
    System.out.println("15");
    out2.writeObject(testAction1);
    System.out.println("16");
    in2.readObject();
    System.out.println("17");
    Action testAction2 = new AttackAction(unithelper(25), "Oz", "Scadrial",100);
    System.out.println("18");
    out1.writeObject(testAction2);
    System.out.println("19");
    in1.readObject();
    System.out.println("20");
    out2.writeObject(testAction2);
    System.out.println("21");
    in2.readObject();
    System.out.println("22");
    Action done = new DoneAction();
    System.out.println("23");
    out1.writeObject(done);
    System.out.println("24");
    out2.writeObject(done);
    System.out.println("25");
    in1.readObject();
    System.out.println("26");
    in2.readObject();
    System.out.println("27");
    in1.close();
    System.out.println("28");
    out1.close();
    System.out.println("29");
    client1.stop();
    System.out.println("30");
    in2.close();
    System.out.println("31");
    out2.close();
    System.out.println("32");
    client2.stop();
    System.out.println("33");
    th1.interrupt();
    System.out.println("34");
    th1.join();
  }


  public HashMap<String,Integer> unithelper(int count){
    HashMap<String,Integer> units = new HashMap<String,Integer>();
    units.put("level0 unit",count);
    return units;
  }


  /*
  public void testClient2() {
    try {
      ClientConnection client = new ClientConnection("localhost", 6666);
      client.receive();
      Socket clientSocket = client.getClientSocket();
      ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
      in.readObject();
      Action testAction1 = new Move(18, "Scadrial", "Roshar");
      out.writeObject(testAction1);
      client.receive();
      Action testAction2 = new Attack(25, "Oz", "Scadrial");
      out.writeObject(testAction2);
      client.receive();
      Action done = new Done();
      out.writeObject(done);
      in.readObject();
      in.close();
      out.close();
      client.stop();
    } catch (Exception e) {
    }
  }
  */
}
