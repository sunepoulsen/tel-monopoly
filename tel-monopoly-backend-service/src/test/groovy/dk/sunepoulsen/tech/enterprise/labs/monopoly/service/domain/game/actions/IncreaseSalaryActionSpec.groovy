package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult
import spock.lang.Specification

class IncreaseSalaryActionSpec extends Specification {

    private Turn turn
    private DicesResult dicesResult = Mock(DicesResult)

    void "Tests increase of salary"() {
        given: 'A play with 1.000 in cash'
            turn = new Turn(new Player('Jennifer', 1000.0), new Dices())
            turn.dicesResult = dicesResult

        when: 'Increase the player salary with 200'
            new IncreaseSalaryAction(200.0).performAction(turn)

        then:
            turn.player.cash == 1200.0
            0 * turn.dicesResult._
    }
}
