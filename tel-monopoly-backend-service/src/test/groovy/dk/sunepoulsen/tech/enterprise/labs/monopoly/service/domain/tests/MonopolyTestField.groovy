package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.tests

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.AbstractMonopolyField

class MonopolyTestField extends AbstractMonopolyField {
    private MonopolyAction passingForwardAction
    private MonopolyAction action

    MonopolyTestField(String id, MonopolyAction passingForwardAction, MonopolyAction action) {
        super(id)
        this.passingForwardAction = passingForwardAction
        this.action = action
    }

    @Override
    MonopolyAction action() {
        return this.action
    }

    @Override
    MonopolyAction passingForwardAction() {
        return this.passingForwardAction
    }
}
