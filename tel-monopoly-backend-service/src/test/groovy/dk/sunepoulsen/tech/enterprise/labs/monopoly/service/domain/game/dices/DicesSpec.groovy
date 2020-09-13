package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dice
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult
import spock.lang.Specification
import spock.lang.Unroll

class DicesSpec extends Specification {
    private Dice firstDice = Mock(Dice)
    private Dice secondDice = Mock(Dice)

    @Unroll
    void "Roll two dices"() {
        given:
            Dices dices = new Dices(firstDice, secondDice)

        when:
            DicesResult dicesResult = dices.roll()

        then:
            1 * firstDice.roolDice() >> 3
            1 * secondDice.roolDice() >> 4

            dicesResult.first == 3
            dicesResult.second == 4
    }

}
