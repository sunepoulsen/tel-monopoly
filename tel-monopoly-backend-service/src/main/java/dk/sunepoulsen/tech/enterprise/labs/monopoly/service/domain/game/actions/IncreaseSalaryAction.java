package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;

public class IncreaseSalaryAction implements MonopolyAction {
    private final double amount;

    public IncreaseSalaryAction(double amount) {
        this.amount = amount;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        turn.getPlayer().increaseCash(this.amount);
    }

}
