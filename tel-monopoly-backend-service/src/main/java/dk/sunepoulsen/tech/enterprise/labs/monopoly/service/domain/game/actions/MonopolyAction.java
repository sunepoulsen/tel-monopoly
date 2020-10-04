package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;

public interface MonopolyAction {
    /**
     * Performs the action of the player on a field.
     */
    void performAction(Turn turn) throws MonopolyActionException;
}
