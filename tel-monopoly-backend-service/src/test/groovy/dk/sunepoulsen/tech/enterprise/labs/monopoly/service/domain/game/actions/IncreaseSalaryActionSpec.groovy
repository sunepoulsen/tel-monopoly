package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult
import spock.lang.Specification

class IncreaseSalaryActionSpec extends Specification {

    private DicesResult mockDicesResult = Mock(DicesResult)

    void "Tests increase of salary"() {
        given: 'A play with 1.000 in cash'
            Player player = new Player('Jennifer', 1000.0)

        when: 'Increase the player salary with 200'
            new IncreaseSalaryAction(200.0).performAction(player, mockDicesResult)

        then:
            player.cash == 1200.0
            0 * mockDicesResult._
    }
}
