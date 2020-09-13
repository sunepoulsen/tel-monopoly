package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.NoAction;

import java.util.Collections;
import java.util.List;

public class AbstractMonopolyField implements MonopolyField {
    private final String id;
    private MonopolyField nextField;
    private MonopolyField previousField;

    public AbstractMonopolyField(String id) {
        this.id = id;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public MonopolyField nextField() {
        return this.nextField;
    }

    public void setNextField(MonopolyField nextField) {
        this.nextField = nextField;
    }

    @Override
    public MonopolyField previousField() {
        return this.previousField;
    }

    public void setPreviousField(MonopolyField previousField) {
        this.previousField = previousField;
    }

    @Override
    public List<MonopolyAction> action() {
        return Collections.emptyList();
    }

    @Override
    public MonopolyAction passingForwardAction() {
        return new NoAction();
    }
}
