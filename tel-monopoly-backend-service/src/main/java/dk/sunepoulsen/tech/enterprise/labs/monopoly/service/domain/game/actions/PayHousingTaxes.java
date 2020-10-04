package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException;

public class PayHousingTaxes implements MonopolyAction {
    private Double houseTax;
    private Double hotelTax;

    public PayHousingTaxes(Double houseTax, Double hotelTax) {
        this.houseTax = houseTax;
        this.hotelTax = hotelTax;
    }

    @Override
    public void performAction(Turn turn) throws MonopolyActionException {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
