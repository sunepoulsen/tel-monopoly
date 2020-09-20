package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.NoAction;

public class PrisonField extends AbstractMonopolyField {
    public PrisonField() {
        super("prison");
    }

    @Override
    public MonopolyAction action() {
        return new NoAction();
    }
}
