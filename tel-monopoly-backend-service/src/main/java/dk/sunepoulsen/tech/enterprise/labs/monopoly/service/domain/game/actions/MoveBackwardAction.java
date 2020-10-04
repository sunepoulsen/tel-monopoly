package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;

public class MoveBackwardAction implements MonopolyAction {
    private int moveFields;

    public MoveBackwardAction(int moveFields) {
        this.moveFields = moveFields;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
