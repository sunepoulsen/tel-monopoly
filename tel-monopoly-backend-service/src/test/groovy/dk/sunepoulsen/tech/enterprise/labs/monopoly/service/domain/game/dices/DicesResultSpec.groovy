package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.DicesResult
import spock.lang.Specification
import spock.lang.Unroll

class DicesResultSpec extends Specification {
    @Unroll
    void "Dices '#_first' and '#_second' has the sum: '#_sum'"() {
        when:
            DicesResult dicesResult = new DicesResult(_first, _second)

        then:
            dicesResult.sum() == _sum

        where:
            _first | _second | _sum
            1      | 5       | 6
    }

    @Unroll
    void "Dices '#_first' and '#_second' is equal: '#_equal'"() {
        when:
            DicesResult dicesResult = new DicesResult(_first, _second)

        then:
            dicesResult.equal == _equal

        where:
            _first | _second | _equal
            1      | 5       | false
            3      | 3       | true
    }

}
