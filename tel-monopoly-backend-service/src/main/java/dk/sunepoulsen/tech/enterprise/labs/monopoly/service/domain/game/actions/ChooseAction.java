package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.BadActionException;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.players.ActionChooser;

public class ChooseAction implements MonopolyAction {
    private ChooseActionReason reason;

    public ChooseAction(ChooseActionReason reason) {
        this.reason = reason;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        try {
            ActionChooser actionChooser = turn.getPlayer().createActionChooser(this.reason);
            MonopolyAction action = actionChooser.makeChoice();

            if (this.reason.possibleActions().contains(action)) {
                action.performAction(turn);
            }

            throw new BadActionException(String.format("Bad or no action was chosen by the player for the reason. Reason: %s, Action: %s", this.reason, action));
        }
        catch (BadActionException ex) {
            throw new MonopolyActionException("Unable to execute chosen action", ex);
        }
    }
}
