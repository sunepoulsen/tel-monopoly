package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.MonopolyBoard
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Player
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.fields.AbstractMonopolyField
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.tests.MonopolyTestField
import spock.lang.Specification

class MoveForwardActionSpec extends Specification {
    
    void "Test move forward 3 fields on a 5 field board"() {
        given: 'A board with 5 fields'
            MonopolyBoard board = new MonopolyBoard()
            List<AbstractMonopolyField> fields = [
                new MonopolyTestField('field-1', Mock(MonopolyAction), Mock(MonopolyAction)),
                new MonopolyTestField('field-2', Mock(MonopolyAction), Mock(MonopolyAction)),
                new MonopolyTestField('field-3', Mock(MonopolyAction), Mock(MonopolyAction)),
                new MonopolyTestField('field-4', Mock(MonopolyAction), Mock(MonopolyAction)),
                new MonopolyTestField('field-5', Mock(MonopolyAction), Mock(MonopolyAction))
            ]
            fields.each {board.insertField(it) }

        and: 'a player that stands on the start field'
            Player player = new Player('name', 0.0, board.startField)
            Turn turn = new Turn(player, Mock(Dices))

        when: 'move the player 3 fields'
            new MoveForwardAction(3).performAction(turn)

        then: 'the player is positioned on the 3. field'
            player.currentField.id() == 'field-3'

        and: 'the passing action has been performed only for the 3 fields'
            fields.size().times {
                if (it < 3) {
                    1 * fields[it].passingForwardAction().performAction(turn)
                }
                else {
                    0 * fields[it].passingForwardAction().performAction(turn)
                }
            }

        and: 'the action has been performed on only the 3. field'
            fields.each {
                if (it.id() == 'field-3') {
                    1 * it.action().performAction(turn)
                }
                else {
                    0 * it.action().performAction(turn)
                }
            }
    }
}
