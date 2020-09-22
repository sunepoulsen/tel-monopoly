package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult;

public class PayHousingTaxes implements MonopolyAction {
    private Double houseTax;
    private Double hotelTax;

    public PayHousingTaxes(Double houseTax, Double hotelTax) {
        this.houseTax = houseTax;
        this.hotelTax = hotelTax;
    }

    @Override
    public void performAction(Player player, DicesResult dicesResult) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
