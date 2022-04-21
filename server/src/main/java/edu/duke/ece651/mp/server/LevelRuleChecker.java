package edu.duke.ece651.mp.server;

import edu.duke.ece651.mp.common.*;

public class LevelRuleChecker extends ActionRuleChecker {
  /**
   * Constructs a LevelRuleChecker
   * @param next: the next rule to check
   */
  public LevelRuleChecker(ActionRuleChecker next) {
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
    if (action instanceof MeteorShowerAction) {
      if (world.getTechLevel(action.getPlayerName()) < 6) {
        return "Meteor shower not avaliable until technology level 6.";
      }
    }
    return null;
  }
}
