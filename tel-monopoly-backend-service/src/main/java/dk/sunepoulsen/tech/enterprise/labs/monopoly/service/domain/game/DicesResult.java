package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import lombok.Data;

/**
 * Holds the result of a throw of two dices.
 */
@Data
public class DicesResult {
    private final Integer first;
    private final Integer second;

    public DicesResult(Integer first, Integer second) {
        this.first = first;
        this.second = second;
    }

    Integer sum() {
        return this.first + this.second;
    }

    boolean isEqual() {
        return this.first.equals(this.second);
    }
}
