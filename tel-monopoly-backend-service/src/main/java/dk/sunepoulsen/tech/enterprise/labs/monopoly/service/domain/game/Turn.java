package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveForwardAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.players.Player;
import lombok.Getter;

/**
 * Class to execute one turn for a player
 */
public class Turn {
    /**
     * The player to play this turn
     */
    @Getter
    private final Player player;

    /**
     * Dices to play the turn with.
     */
    private final Dices dices;

    /**
     * Holds the result of the last roll of dices.
     */
    @Getter
    private DicesResult dicesResult;

    public Turn(Player player, Dices dices) {
        this.player = player;
        this.dices = dices;
        this.dicesResult = null;
    }

    MonopolyAction playTurn() {
        this.dicesResult = this.dices.roll();
        return new MoveForwardAction(this.dicesResult.sum());
    }

    boolean playAgain() {
        return this.dicesResult.isEqual();
    }
}
