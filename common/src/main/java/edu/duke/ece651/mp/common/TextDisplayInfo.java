package edu.duke.ece651.mp.common;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import edu.duke.ece651.mp.common.Territory;
import edu.duke.ece651.mp.common.World;

/**
 * This class takes an World class and display the territory info by player in text form
 */
public class TextDisplayInfo implements DisplayInfo {
  private World gamemap;
  final PrintStream out;
  private ArrayList<String> player_names;
  private ArrayList<ArrayList<Territory>> territories_by_player;

  /**
   * Simplified Consturctor that takes no World Class
   * 
   * @out is where we want to display the info
   */
  public TextDisplayInfo(PrintStream out) {
    this.gamemap = null;
    this.player_names = null;
    this.out = out;
    this.territories_by_player = null;
  }

  /**
   * Print the input World map
   */
  @Override
  public void displayMap(World inputWorld) {
    this.gamemap = inputWorld;
    this.player_names = gamemap.getPlayerName();
    this.territories_by_player = new ArrayList<ArrayList<Territory>>();
    parseTerritoriesByPlayer();
    display();
  }

  /**
   * Group territories of each player accordingly
   */
  private void parseTerritoriesByPlayer() {
    for (String name : player_names) {
      territories_by_player.add(gamemap.getTerritoryByPlayer(name));
    }
  }

  /**
   * print the name of the player follows by a divider
   */
  private void printPlayername(String name) {
    out.println(name + " player:");
    out.println("-------------");
  }

  /**
   * Get the current territory's neighbour's name, and parse them into an
   * ArrayList of String
   * 
   * @param t is the territory we parse from
   * @return neighbour is an ArrayList containing all the neighbour's name
   */
  private ArrayList<Territory> parseNeighbour(Territory t) {
    ArrayList<Territory> neighbour = new ArrayList<Territory>();
    for (Territory territory : t.getNeighbors()) {
      neighbour.add(territory);
    }
    Collections.sort(neighbour);
    return neighbour;
  }

  /**
   * Output the unit and neighbour info of the current territory
   */
  private void printTerritory(Territory t) {
    String unit_count = String.valueOf(t.getUnits("level0 unit"));
    ArrayList<Territory> neighbour = parseNeighbour(t);
    out.print(unit_count + " units in " + t.getName() + " (next to: ");
    for (int i = 0; i < neighbour.size() - 1; i++) {
      out.print(" " + neighbour.get(i).getName() + ",");
    }
    out.println(" " + neighbour.get(neighbour.size() - 1).getName() + ")");
  }

  /**
   * Display the desired info in textform to the specified printStream
   */
  private void display() {
    for (int i = 0; i < player_names.size(); i++) {
      printPlayername(player_names.get(i));
      for (Territory t : territories_by_player.get(i)) {
        printTerritory(t);
      }
      out.println();
    }
  }
}
