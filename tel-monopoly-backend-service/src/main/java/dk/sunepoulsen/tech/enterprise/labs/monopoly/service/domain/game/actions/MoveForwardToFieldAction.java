package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;

public class MoveForwardToFieldAction implements MonopolyAction {
    private String fieldId;

    public MoveForwardToFieldAction(String fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public void performAction(Turn turn) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
