package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import lombok.Getter;

/**
 * A player at a Monopoly game.
 */
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

    public Player() {
        this(null, 0.0);
    }

    public Player(String name, Double cash) {
        this.name = name;
        this.cash = cash;
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
    Double increaseCash(Double amount) {
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
    Double decreaseCash(Double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Amount may not be negative");
        }

        this.cash -= amount;
        return this.cash;
    }
}
