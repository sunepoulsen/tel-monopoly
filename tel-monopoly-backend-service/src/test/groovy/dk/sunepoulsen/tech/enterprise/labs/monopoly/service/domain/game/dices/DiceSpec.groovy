package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dice
import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Unroll

@Slf4j
class DiceSpec extends Specification {
    @Unroll
    void "Roll dice #_rollCount times between 1 and 6"() {
        given:
            Dice dice = new Dice()

        when:
            List<Integer> results = []
            _rollCount.times {
                results << dice.roolDice()
            }
            6.times {no ->
                def list = results.findAll {it == no + 1}
                log.info("Results ${no + 1}'s: ${list.size()} -> ${list.size() / _rollCount * 100.0} %")
            }

        then:
            results.size() == _rollCount
            results.unique().sort() == [1, 2, 3, 4, 5, 6]

        where:
            _rollCount << [100000]
    }
}
