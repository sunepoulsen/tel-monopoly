package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.actions

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.Turn
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.dices.Dices
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.LackOfFoundsException
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.exceptions.MonopolyActionException
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.Ground
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.grounds.GroundGroup
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.tests.TestPlayer
import spock.lang.Specification

class ByeGroundActionSpec extends Specification {
    void "Test that a ground can be brought successfully by a player"() {
        given: 'A player with sufficient fonds'
            TestPlayer player = new TestPlayer('player', 1000.0, null)

        and: 'a ground with a lower price'
            Ground ground = new Ground(new GroundGroup('group'), 'ground', 200.0, 100.0)

        when: 'the player buys the ground'
            new ByeGroundAction(ground).performAction(new Turn(player, Mock(Dices)))

        then: 'the players fonds is decreased with the price of the ground'
            player.cash == 800.0

        and: 'the player owns the ground'
            ground.owner == player
    }

    void "Test that a ground can not be brought by a player with insufficient fonds"() {
        given: 'A player with sufficient fonds'
            TestPlayer player = new TestPlayer('player', 1000.0, null)

        and: 'a ground with a higher price'
            Ground ground = new Ground(new GroundGroup('group'), 'ground', 1001.0, 100.0)

        when: 'the player tries to buy the ground'
            new ByeGroundAction(ground).performAction(new Turn(player, Mock(Dices)))

        then: 'the player is rejected and an exception is thrown'
            Exception ex = thrown(MonopolyActionException)
            ex.cause.class == LackOfFoundsException
    }
}
