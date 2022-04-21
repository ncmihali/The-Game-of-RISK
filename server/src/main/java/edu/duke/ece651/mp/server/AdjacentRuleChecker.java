package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class AdjacentRuleChecker extends ActionRuleChecker {
  /**
   * Constructs a IsOwnRuleChecker
   * @param next: the next rule to check
   */
  public AdjacentRuleChecker(ActionRuleChecker next) {
    super(next);
  }

  /**
   * Checks if Territory to is own territory.
   * @param action: the action to check.
   * @return null if action is valid. Otherwise return
   *         String error message of not own territory.
   */
  @Override
  protected String checkMyRule(Action action, World world) {
    if (action instanceof MoveAction) {
      Territory from = world.getTerritoryByName(action.getTerritoryFrom());
      Territory to = world.getTerritoryByName(action.getTerritoryTo());
      if (!existPath(from, to)) {
        return "There is no path between the two territories.";
      }
    } else if (action instanceof AttackAction) {
      Territory from = world.getTerritoryByName(action.getTerritoryFrom());
      Territory to = world.getTerritoryByName(action.getTerritoryTo());
      if (!from.getNeighbors().contains(to)) {
        return "Target territory is not adjacent to your selected territory.";
      }
    }
    return null;
  }

  /**
   * A valid path consist of all own territory..
   * @param from: Territory to check from.
   * @param to: Territory to check to.
   * @return True if there exist a path between from and to.
   *         Returns false otherwise.
   */
  private boolean existPath(Territory from, Territory to) {
    Queue<Territory> toVisit = new LinkedList<Territory>();
    toVisit.add(from);
    HashSet<Territory> visited = new HashSet<Territory>();
    while (!toVisit.isEmpty()) {
      Territory t = toVisit.poll();
      if (t.equals(to)) {
        return true;
      }
      for (Territory neigh: t.getNeighbors()) {
        if (!visited.contains(neigh) && neigh.getPlayer().equals(from.getPlayer())) {
          toVisit.add(neigh);
        }
      }
      visited.add(t);
    }
    return false;
  }
}
