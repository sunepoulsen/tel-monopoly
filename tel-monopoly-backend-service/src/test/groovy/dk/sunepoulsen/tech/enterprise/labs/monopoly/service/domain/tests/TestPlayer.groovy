package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.tests

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.ChooseActionReason
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.MonopolyField
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.players.ActionChooser
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.players.Player

class TestPlayer extends Player {
    ActionChooser actionChooser

    TestPlayer() {
    }

    TestPlayer(String name, Double cash, MonopolyField currentField) {
        super(name, cash, currentField)
    }

    @Override
    ActionChooser createActionChooser(ChooseActionReason reason) {
        return this.actionChooser
    }
}
