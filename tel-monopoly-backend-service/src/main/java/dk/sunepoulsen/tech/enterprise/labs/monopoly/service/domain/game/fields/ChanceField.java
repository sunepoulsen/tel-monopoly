package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.ChanceCardPile;

public class ChanceField extends AbstractMonopolyField {
    private final ChanceCardPile chanceCardPile;

    public ChanceField(String id, ChanceCardPile chanceCardPile) {
        super(id);
        this.chanceCardPile = chanceCardPile;
    }

    @Override
    public MonopolyAction action() {
        return this.chanceCardPile.drawCard().getAction();
    }
}
