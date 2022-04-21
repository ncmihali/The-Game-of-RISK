package edu.duke.ece651.mp.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.junit.jupiter.api.parallel.Resources;

import edu.duke.ece651.mp.common.BasicTerritory;
import edu.duke.ece651.mp.common.BasicUnit;
import edu.duke.ece651.mp.common.ServerConnection;
import edu.duke.ece651.mp.common.Territory;
import edu.duke.ece651.mp.common.Unit;
import edu.duke.ece651.mp.common.World;

public class ClientTest {

  @Test
  @ResourceLock(value = Resources.SYSTEM_OUT, mode = ResourceAccessMode.READ_WRITE)
  public void test_client2() throws ClassNotFoundException, IOException, InterruptedException {
      Runnable r2 = () -> test_server2();
      Thread th2 = new Thread(r2);
      th2.start();
      Thread.sleep(100);
      Client client = new Client("localhost","6666");
      client.updateClientWorld(test_world1_2());
      client.endGame();
      th2.interrupt();
      th2.join();
  }


  public void test_server2() {
    try {
      ServerConnection server = new ServerConnection(6666);
      server.acceptConnection();
      ObjectOutputStream out = new ObjectOutputStream(server.getClientSocket(0).getOutputStream());
      ObjectInputStream in = new ObjectInputStream(server.getClientSocket(0).getInputStream());
      out.writeObject("Green");
      out.writeObject(test_world1_0());
      server.stop();
    } catch (Exception e) {
    }
  }

  public World test_world1_0() {
    Territory oz = new BasicTerritory("Oz", unit_helper(20), "Green");
    Territory mordor = new BasicTerritory("Mordor", unit_helper(5), "Green");
    Territory roshar = new BasicTerritory("Roshar", unit_helper(1), "Blue");
    return add_neighbours(oz, mordor, roshar);
  }

  public World test_world1_2() {
    Territory oz = new BasicTerritory("Oz", unit_helper(0), "Blue");
    Territory mordor = new BasicTerritory("Mordor", unit_helper(0), "Blue");
    Territory roshar = new BasicTerritory("Roshar", unit_helper(24), "Blue");
    return add_neighbours(oz, mordor, roshar);
  }

  public World add_neighbours(Territory oz, Territory mordor, Territory roshar) {
    oz.addNeighbor(mordor, roshar);
    mordor.addNeighbor(oz, roshar);
    roshar.addNeighbor(oz, mordor);
    Territory arr[] = { oz, mordor, roshar };
    HashSet<Territory> list_of_territories = new HashSet<Territory>(Arrays.asList(arr));
    World new_world = new World(list_of_territories);
    return new_world;
  }

  public HashMap<Unit, Integer> unit_helper(int unit_count) {
    HashMap<Unit, Integer> mapping = new HashMap<Unit, Integer>();
    mapping.put(new BasicUnit(), unit_count);
    return mapping;
  }

}
