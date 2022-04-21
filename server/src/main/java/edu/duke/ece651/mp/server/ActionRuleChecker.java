package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;

/**
 * This class is an abstract class to hold checking rules for action.
 */
public abstract class ActionRuleChecker {
  private final ActionRuleChecker next;

  /**
   * Constructs a ActionRuleChecker
   * 
   * @param next: the rule to check
   */
  public ActionRuleChecker(ActionRuleChecker next) {
    this.next = next;
  }

  /**
   * Checks a single rule for placement
   * 
   * @param action: the action to check.
   * @return null if action is valid. Otherwise return String error message.
   */
  protected abstract String checkMyRule(Action action, World world);

  /**
   * Checks if the coordinate of a ship is valid.
   * 
   * @param action: the action to check.
   * @return null if action is valid. Otherwise return String error message.
   */
  public String checkAction(Action action, World world) {
    // if we fail our own rule: stop. the action is not legal
    if (checkMyRule(action, world) != null) {
      return checkMyRule(action, world);
    }
    // other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkAction(action, world);
    }
    // if there are no more rules, then the action is legal
    return null;
  }

  /**
   * put the various rule checkers together via chain of responsibility model
   * order: levelchecker -> fromChecker -> toChecker -> unitChecker -> adjacentChecker -> CostChecker
   */
  public static ActionRuleChecker makeActionRuleChecker() {
    ActionRuleChecker costChecker = new CostRuleChecker(null);
    ActionRuleChecker adjacentChecker = new AdjacentRuleChecker(costChecker);
    ActionRuleChecker unitChecker = new UnitRuleChecker(adjacentChecker);
    ActionRuleChecker toChecker = new TerritoryToRuleChecker(unitChecker);
    ActionRuleChecker fromChecker = new TerritoryFromRuleChecker(toChecker);
    ActionRuleChecker levelChecker = new LevelRuleChecker(fromChecker);
    return fromChecker;
  }
}
