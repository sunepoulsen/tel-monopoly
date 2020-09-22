package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult;

public class PayAmountAction implements MonopolyAction {
    private Double amount;

    public PayAmountAction(Double amount) {
        this.amount = amount;
    }

    @Override
    public void performAction(Player player, DicesResult dicesResult) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
