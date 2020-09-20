package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;

public class ActionField extends AbstractMonopolyField {
    private final MonopolyAction action;

    public ActionField(String id, MonopolyAction action) {
        super(id);
        this.action = action;
    }

    @Override
    public MonopolyAction action() {
        return this.action;
    }
}
