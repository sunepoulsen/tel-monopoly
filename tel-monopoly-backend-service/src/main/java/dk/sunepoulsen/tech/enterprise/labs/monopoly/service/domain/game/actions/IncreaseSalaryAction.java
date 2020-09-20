package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult;

public class IncreaseSalaryAction implements MonopolyAction {
    private final double amount;

    public IncreaseSalaryAction(double amount) {
        this.amount = amount;
    }

    @Override
    public void performAction(Player player, DicesResult dicesResult) {
        player.increaseCash(this.amount);
    }

}
