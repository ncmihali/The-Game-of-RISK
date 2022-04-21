package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;

public class CostRuleChecker extends ActionRuleChecker {
  /**
   * Constructs a CostRuleChecker
   * @param next: the next rule to check
   */
  public CostRuleChecker(ActionRuleChecker next) {
    super(next);
  }

  /**
   * Checks if there is enough resource to execute the action
   * @param action: the action to check.
   * @return null if action is valid. Otherwise return
   *         String error message.
   */
  @Override
  protected String checkMyRule(Action action, World world) {
    if (action instanceof MoveAction || action instanceof AttackAction) {
      int avaliable_resource = world.getResources(action.getPlayerName()).get("Food");
      System.out.println(avaliable_resource);
      System.out.println(action.getResource());
      if (action.getResource() > avaliable_resource) {
        return "Not enough food resource to perform action.";
      }
    } else if (action instanceof TechUpgradeAction || action instanceof UnitUpgradeAction
               || action instanceof MeteorShowerAction) {
      int avaliable_resource = world.getResources(action.getPlayerName()).get("Electricity");
      if (action.getResource() == 0) {
        return "Already reached maximum technology level.";
      } else if (action.getResource() > avaliable_resource) {
        return "Not enough electricity resource to perform action.";
      }
    }
    return null; // No error or DoneAction
  }
}
