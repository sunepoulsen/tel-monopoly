package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.ChanceCard;
import lombok.Getter;

public class MonopolyChanceCard implements ChanceCard {
    private final String id;

    @Getter
    private final String description;
    private final MonopolyAction action;

    public MonopolyChanceCard(String id, String description, MonopolyAction action) {
        this.id = id;
        this.description = description;
        this.action = action;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public MonopolyAction getAction() {
        return this.action;
    }
}
