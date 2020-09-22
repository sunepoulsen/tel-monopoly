package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import lombok.Getter;

public class MonopolyPrisonCard implements ChanceCard {
    private final String id;

    @Getter
    private final String description;

    public MonopolyPrisonCard(String id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public MonopolyAction getAction() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
