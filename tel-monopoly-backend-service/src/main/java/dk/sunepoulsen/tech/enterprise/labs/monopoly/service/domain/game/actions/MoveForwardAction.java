package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.MonopolyField;

public class MoveForwardAction implements MonopolyAction {
    private int moveFields;

    public MoveForwardAction(int moveFields) {
        this.moveFields = moveFields;
    }

    @Override
    public void performAction(Turn turn) {
        Player player = turn.getPlayer();
        MonopolyField field = player.getCurrentField();

        MonopolyAction action;
        for (int i = 0; i < moveFields; i++) {
            field = field.nextField();

            action = field.passingForwardAction();
            if (action != null) {
                action.performAction(turn);
            }
        }

        player.setCurrentField(field);
        action = field.action();
        if (action != null) {
            action.performAction(turn);
        }
    }
}
