package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;

public class ReceiveAmountAction implements MonopolyAction {
    private Double amount;

    public ReceiveAmountAction(Double amount) {
        this.amount = amount;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
