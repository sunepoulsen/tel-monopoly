package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;

/**
 * Defines a change card from in the game.
 */
public interface ChanceCard {
    /**
     * Each change card must have the ML can use to identify this chance card in its analysis of a player.
     */
    String id();

    /**
     * Returns the action that needs to be performed when a player picks up this change card.
     */
    MonopolyAction getAction();
}
