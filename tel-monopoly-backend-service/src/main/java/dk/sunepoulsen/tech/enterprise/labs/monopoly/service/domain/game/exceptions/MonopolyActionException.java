package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions;

public class MonopolyActionException extends Exception {
    public MonopolyActionException(String message) {
        super(message);
    }

    public MonopolyActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
