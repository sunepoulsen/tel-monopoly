package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.IncreaseSalaryAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;

public class StartField extends AbstractMonopolyField {

    public StartField() {
        super("start-field");
    }

    @Override
    public MonopolyAction action() {
        return passingForwardAction();
    }

    @Override
    public MonopolyAction passingForwardAction() {
        return new IncreaseSalaryAction(4000.0);
    }

}
