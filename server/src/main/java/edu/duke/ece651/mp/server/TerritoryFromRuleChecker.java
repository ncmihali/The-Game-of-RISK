package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;

public class TerritoryFromRuleChecker extends ActionRuleChecker {
  /**
   * Constructs a IsOwnRuleChecker
   * @param next: the next rule to check
   */
  public TerritoryFromRuleChecker(ActionRuleChecker next) {
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
    Territory from = world.getTerritoryByName(action.getTerritoryFrom());
    if (action instanceof DoneAction || action instanceof TechUpgradeAction
        || action instanceof MeteorShowerAction) { // No need to check DoneAction
      return null;
    }
    if (!action.getPlayerName().equals(from.getPlayer())) {
      return "Cannot use units from other player's territory.";
    }
    return null;
  }
}
