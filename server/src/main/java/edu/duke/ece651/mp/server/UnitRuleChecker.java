package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;

public class UnitRuleChecker extends ActionRuleChecker {
  /**
   * Constructs a UnitRuleChecker
   * @param next: the next rule to check
   */
  public UnitRuleChecker(ActionRuleChecker next) {
    super(next);
  }

  /**
   * Checks if Territory to contains enough units
   * @param action: the action to check.
   * @return null if action is valid. Otherwise return
   *         String error message of not enough unit.
   */
  @Override
  protected String checkMyRule(Action action, World world) {
    Territory from = world.getTerritoryByName(action.getTerritoryFrom());
    if (action instanceof DoneAction || action instanceof TechUpgradeAction
        || action instanceof MeteorShowerAction) {
      return null;
    } else {
      for (String u : action.getUnit().keySet()) {
        if (from.getUnits(u) < action.getUnit().get(u)) {
          return "Territory does not have enough unit.";
        }
      }
    }
    return null;
  }
}
