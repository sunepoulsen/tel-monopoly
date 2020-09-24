package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions


import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn
import spock.lang.Specification

class NoActionSpec extends Specification {

    private Turn turn = Mock(Turn)

    void "Tests that the action does nothing"() {
        given: 'An NoAction'
            NoAction action = new NoAction()

        when: 'Execute action'
            action.performAction(turn)

        then:
            0 * turn._
    }
}
