package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player;
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn;

import java.util.List;

public class ReceiveFromAllPlayersAction implements MonopolyAction {
    private List<Player> players;
    private Double amount;

    public ReceiveFromAllPlayersAction(List<Player> players, Double amount) {
        this.players = players;
        this.amount = amount;
    }

    @Override
    public void performAction(Turn turn) {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
