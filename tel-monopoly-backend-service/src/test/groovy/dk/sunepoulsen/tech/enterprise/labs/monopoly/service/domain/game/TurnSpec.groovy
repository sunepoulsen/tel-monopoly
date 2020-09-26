package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MoveForwardAction
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.MonopolyField
import spock.lang.Specification

class TurnSpec extends Specification {

    private Dices dices = Mock(Dices)

    void "Play a single turn for a player"() {
        given: 'Given a player'
            Player player = new Player('Jennifer', 1000.0, Mock(MonopolyField))

        when: 'execute turn for the player'
            MonopolyAction action = new Turn(player, dices).playTurn()

        then: 'verity action and mock dices'
            action.getClass() == MoveForwardAction
            ((MoveForwardAction)action).moveFields == 7
            1 * dices.roll() >> new DicesResult(3, 4)
    }

    void "Play a single turn for a player that rolls equal dices"() {
        given: 'Given a player'
            Player player = new Player('Jennifer', 1000.0, Mock(MonopolyField))
            Turn turn = new Turn(player, dices)

        when: 'execute turn for the player'
            MonopolyAction action = turn.playTurn()

        then: 'verity action and mock dices'
            action.getClass() == MoveForwardAction
            ((MoveForwardAction)action).moveFields == 8
            1 * dices.roll() >> new DicesResult(4, 4)
            turn.playAgain()
    }
}
