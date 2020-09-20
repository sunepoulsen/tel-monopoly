package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game

import spock.lang.Specification

class MonopolyGameSpec extends Specification {

    private MonopolyGame game

    void setup() {
        this.game = new MonopolyGame()
        this.game.configureBoard()
    }

    void "Test game configuration"() {
        expect: 'Players'
            this.game.players != null

        and: 'Chance pile'
            this.game.chanceCardPile != null

        and: 'Board fields'
            this.game.board.startField.id() == "start-field"

        and: 'Dices'
            this.game.dices != null
    }
}
