package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions.MonopolyAction
import spock.lang.Specification

class MonopolyGameSpec extends Specification {

    private MonopolyGame game
    private MonopolyInstanceFactory instanceFactory = Mock(MonopolyInstanceFactory)

    void setup() {
        this.game = new MonopolyGame(instanceFactory)
        this.game.configureGame()
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

    void "Test that a player is back in the queue after an executed turn"() {
        given: 'Game with two players'
            Player p1 = new Player('player-1', 40000.0, this.game.board.startField)
            Player p2 = new Player('player-2', 40000.0, this.game.board.startField)

            this.game.players.add(p1)
            this.game.players.add(p2)

            Turn turn = Mock(Turn)
            MonopolyAction action = Mock(MonopolyAction)

        when: 'p1 plays a turn'
            this.game.playTurnForNextPlayer()

        then: 'The game performs the action to move p1 forward to a new field'
            1 * instanceFactory.newTurnInstance(p1, this.game.dices) >> turn
            1 * turn.playTurn() >> action
            1 * turn.playAgain() >> false
            1 * turn.getPlayer() >> p1
            1 * action.performAction(turn)

        and: 'Put the player back into the queue of players'
            this.game.players.toList() == [p2, p1]
    }

    void "Test that a player can repeat its turn"() {
        given: 'Game with two players'
            Player p1 = new Player('player-1', 40000.0, this.game.board.startField)
            Player p2 = new Player('player-2', 40000.0, this.game.board.startField)

            this.game.players.add(p1)
            this.game.players.add(p2)

            Turn turn = Mock(Turn)
            MonopolyAction action1 = Mock(MonopolyAction)
            MonopolyAction action2 = Mock(MonopolyAction)

        when: 'p1 plays a turn'
            this.game.playTurnForNextPlayer()

        then: 'The game performs the action to move p1 forward to a new field'
            1 * instanceFactory.newTurnInstance(p1, this.game.dices) >> turn
            2 * turn.playTurn() >>> [action1, action2]
            2 * turn.playAgain() >>> [true, false]
            1 * turn.getPlayer() >> p1
            1 * action1.performAction(turn)
            1 * action2.performAction(turn)

        and: 'Put the player back into the queue of players'
            this.game.players.toList() == [p2, p1]
    }
}
