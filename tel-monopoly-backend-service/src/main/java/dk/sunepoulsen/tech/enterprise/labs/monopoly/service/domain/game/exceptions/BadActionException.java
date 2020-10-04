package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions;

public class BadActionException extends Exception {
    public BadActionException(String message) {
        super(message);
    }
}
