package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.ByeGroundReason;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.ChooseAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.NoAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.Ground;

public class GroundField extends AbstractMonopolyField {
    private final Ground ground;

    public GroundField(Ground ground) {
        super(ground.getId());
        this.ground = ground;
    }

    @Override
    public MonopolyAction action() {
        if (this.ground.getOwner() == null ) {
            return new ChooseAction(new ByeGroundReason(this.ground));
        }

        throw new UnsupportedOperationException("Collect rent is not implemented yet.");
    }
}
