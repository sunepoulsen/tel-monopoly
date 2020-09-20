package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult
import spock.lang.Specification

class NoActionSpec extends Specification {

    private Player player = Mock(Player)
    private DicesResult dicesResult = Mock(DicesResult)

    void "Tests that the action does nothing"() {
        given: 'An NoAction'
            NoAction action = new NoAction()

        when: 'Execute action'
            action.performAction(player, dicesResult)

        then:
            0 * player._
            0 * dicesResult._
    }
}
