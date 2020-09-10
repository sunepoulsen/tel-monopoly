package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import lombok.Data;
import java.util.Queue;

/**
 * Representation of a single instance of a Monopoly game.
 */
@Data
public class MonopolyGame {
    /**
     * Contains a list of players in the game.
     * <p>
     *     The list is sorted by the order that the players are playing at each turn.
     * </p>
     */
    private Queue<Player> players;

    /**
     * Queue of change cards to pick from when a player ends its turn on a change field.
     */
    private Queue<ChanceCard> changeCards;

    /**
     * Instance of the game board that holds the state of the entire board.
     */
    private MonopolyBoard board;

    /**
     * Dices for the game.
     */
    private Dices dices;
}
