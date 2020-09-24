package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;

public class MoveForwardAction implements MonopolyAction {
    private int moveFields;

    public MoveForwardAction(int moveFields) {
        this.moveFields = moveFields;
    }

    @Override
    public void performAction(Turn turn) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
