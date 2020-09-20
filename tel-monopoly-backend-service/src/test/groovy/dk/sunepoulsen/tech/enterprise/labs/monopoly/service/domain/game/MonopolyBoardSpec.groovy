package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.NoAction
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.ActionField
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.MonopolyField
import spock.lang.Specification

class MonopolyBoardSpec extends Specification {

    private MonopolyBoard board

    void setup() {
        this.board = new MonopolyBoard()

        10.times {
            this.board.insertField(new ActionField("field-${it}", new NoAction()))
        }
    }

    void "Test iteration though the fields"() {
        when:
            MonopolyField field = this.board.startField

        then:
            field.id() == "start-field"
            10.times {
                field = field.nextField()
                field.id() == "field-${it}"
            }
    }
}
