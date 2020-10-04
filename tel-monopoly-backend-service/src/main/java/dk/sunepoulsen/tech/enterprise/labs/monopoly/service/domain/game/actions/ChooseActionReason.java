package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import java.util.List;

/**
 * Defines the contract of the reason of the player that need to make a decision between multiple possible actions
 */
public interface ChooseActionReason {
    /**
     * List of possible actions to choose to execute for this reason
     */
    List<MonopolyAction> possibleActions();
}
