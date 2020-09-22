package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult;

public class MoveForwardToFieldAction implements MonopolyAction {
    private String fieldId;

    public MoveForwardToFieldAction(String fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public void performAction(Player player, DicesResult dicesResult) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
