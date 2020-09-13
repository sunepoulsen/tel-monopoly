package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices;

/**
 * A set of two dices.
 */
public class Dices {
    private final Dice first;
    private final Dice second;

    public Dices() {
        this(new Dice(), new Dice());
    }

    public Dices(Dice first, Dice second) {
        this.first = first;
        this.second = second;
    }

    public DicesResult roll() {
        return new DicesResult(first.roolDice(), second.roolDice());
    }

}
