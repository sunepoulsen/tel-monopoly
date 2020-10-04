package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;

public class PayAmountAction implements MonopolyAction {
    private Double amount;

    public PayAmountAction(Double amount) {
        this.amount = amount;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
