package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game;

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices;

/**
 * Instance factory to create new instances of classes that is needed inside methods.
 * <p>
 *     Sometimes we need to create class instances that only lives within a single function call. This makes it
 *     very difficult to mock in tests. One example is the <code>Turn</code> class inside
 *     <code>MonopolyGame.playTurnForNextPlayer()</code>
 * </p>
 * <p>
 *     By creating instances thought this member we can pass a mocked instance of <code>MonopolyInstanceFactory</code>
 *     in the constructor and control the behaviour in tests.
 * </p>
 */
public class MonopolyInstanceFactory {
    public Turn newTurnInstance(Player player, Dices dices) {
        return new Turn(player, dices);
    }
}
