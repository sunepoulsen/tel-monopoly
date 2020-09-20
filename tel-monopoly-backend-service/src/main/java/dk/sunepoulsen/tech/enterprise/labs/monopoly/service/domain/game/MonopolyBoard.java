package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.AbstractMonopolyField;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.StartField;
import lombok.Getter;

public class MonopolyBoard {
    @Getter
    private StartField startField;

    public MonopolyBoard() {
        this.startField = createStartField();
    }

    public void insertField(AbstractMonopolyField field) {
        AbstractMonopolyField lastField = (AbstractMonopolyField)startField.previousField();

        field.setPreviousField(lastField);
        field.setNextField(startField);

        lastField.setNextField(field);
        startField.setPreviousField(field);
    }

    private static StartField createStartField() {
        StartField startField = new StartField();

        startField.setPreviousField(startField);
        startField.setNextField(startField);

        return startField;
    }

}
