package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import java.util.Random;

/**
 * A 6 sided dice.
 */
public class Dice {
    private final Random randomNumbers;

    public Dice() {
        this.randomNumbers = new Random();
    }

    /**
     * Rool a dice a return a value between 1 and 6 both inclusive.
     */
    Integer roolDice() {
        return randomNumbers.nextInt(6) + 1;
    }

}
