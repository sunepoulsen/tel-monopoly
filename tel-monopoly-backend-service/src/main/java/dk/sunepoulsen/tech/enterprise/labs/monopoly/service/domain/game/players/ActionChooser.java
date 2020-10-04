package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.players;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;

public interface ActionChooser {
    MonopolyAction makeChoice();
}
