package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;

import java.util.List;

/**
 * Represents a field on the Monopoly board.
 */
public interface MonopolyField {
    /**
     * Each field must have an unique id that is used to find and identify the field.
     */
    String id();

    /**
     * Returns the field next to this field on the board.
     */
    MonopolyField nextField();

    /**
     * Returns the field previous to this field on the board.
     */
    MonopolyField previousField();

    /**
     * Actions to be performed when a player ends its turn on this field.
     * <p>
     *     If the returned list contains more than one item then the player needs to choose which action that will
     *     be performed.
     * </p>
     */
    List<MonopolyAction> action();

    /**
     * Action to be performed when a player passes this field during a move.
     * <p>
     *     The main purpose of this action is to implement the collection of salery when a player passes the start
     *     field.
     * </p>
     */
    MonopolyAction passingForwardAction();
}
