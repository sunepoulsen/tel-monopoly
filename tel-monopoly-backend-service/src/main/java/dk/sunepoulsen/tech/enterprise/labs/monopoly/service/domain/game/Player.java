package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.MonopolyField;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A player at a Monopoly game.
 */
@ToString
@EqualsAndHashCode
public class Player {
    /**
     * Name of the player.
     */
    @Getter
    private final String name;

    /**
     * The total amount of cash.
     */
    @Getter
    private Double cash;

    /**
     * Current field that this player is standing on.
     */
    @Getter
    @Setter
    private MonopolyField currentField;

    public Player() {
        this(null, 0.0, null);
    }

    public Player(String name, Double cash, MonopolyField currentField) {
        this.name = name;
        this.cash = cash;
        this.currentField = currentField;
    }

    /**
     * Increases the player cash amount with an amount.
     *
     * @param amount The amount.
     *
     * @throws IllegalArgumentException If the amount is negative.
     *
     * @return The new amount of this player
     */
    public Double increaseCash(Double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Amount may not be negative");
        }

        this.cash += amount;
        return this.cash;
    }

    /**
     * Decreases the player cash amount with an amount.
     *
     * @param amount The amount.
     *
     * @throws IllegalArgumentException If the amount is negative.
     *
     * @return The new amount of this player
     */
    public Double decreaseCash(Double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Amount may not be negative");
        }

        this.cash -= amount;
        return this.cash;
    }
}
