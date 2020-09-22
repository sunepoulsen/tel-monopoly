package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult;

public class MoveForwardAction implements MonopolyAction {
    private int moveFields;

    public MoveForwardAction(int moveFields) {
        this.moveFields = moveFields;
    }

    @Override
    public void performAction(Player player, DicesResult dicesResult) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
