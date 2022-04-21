package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;

public class TerritoryToRuleChecker extends ActionRuleChecker {
  /**
   * Constructs a TerritoryToRuleChecker
   * @param next: the next rule to check
   */
  public TerritoryToRuleChecker(ActionRuleChecker next) {
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
      Territory to = world.getTerritoryByName(action.getTerritoryTo());
      if (!action.getPlayerName().equals(to.getPlayer())) {
        return "Must move units to own territory.";
      }
    } else if (action instanceof AttackAction || action instanceof MeteorShowerAction) {
      Territory to = world.getTerritoryByName(action.getTerritoryTo());
      if (action.getPlayerName().equals(to.getPlayer())) {
        return "Cannot attack own territory.";
      }
    }
    return null;
  }
}
