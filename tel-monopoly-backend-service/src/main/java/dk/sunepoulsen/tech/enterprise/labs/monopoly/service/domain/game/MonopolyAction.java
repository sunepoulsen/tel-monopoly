package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

public interface MonopolyAction {
    /**
     * Performs the action of the player on a field.
     */
    void performAction(Player player);
}
