package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.LackOfFoundsException;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.Ground;
import lombok.Getter;

public class ByeGroundAction implements MonopolyAction {
    @Getter
    private final Ground ground;

    public ByeGroundAction(Ground ground) {
        this.ground = ground;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        try {
            if (turn.getPlayer().getCash() < this.ground.getPrice()) {
                throw new LackOfFoundsException(String.format("The player '%s' does not have enough founds to buy the ground '%s'. Players found: %s, Ground price: %s",
                    turn.getPlayer().getName(), this.ground.getId(), this.ground.getPrice(), turn.getPlayer().getCash()));
            }

            turn.getPlayer().decreaseCash(this.ground.getPrice());
            this.ground.setOwner(turn.getPlayer());
        }
        catch( LackOfFoundsException ex ) {
            throw new MonopolyActionException("Unable to perform action", ex);
        }
    }
}
