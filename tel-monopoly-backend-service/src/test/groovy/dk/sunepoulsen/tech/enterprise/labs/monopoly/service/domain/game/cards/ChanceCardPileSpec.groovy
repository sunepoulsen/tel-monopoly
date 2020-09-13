package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards

import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.ChanceCard
import dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards.ChanceCardPile
import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Unroll

@Slf4j
class ChanceCardPileSpec extends Specification {
    @Unroll
    void "Shuffle pile of #_cardCount chance card"() {
        given: 'Pile with cards'
            ChanceCardPile pile = new ChanceCardPile()

            _cardCount.times {counter ->
                ChanceCard card = Mock(ChanceCard) {
                    id() >> counter
                    toString() >> counter
                }
                pile.addCard(card)
            }

        when: 'Shuffle the cards'
            pile.shuffle()

        then: 'Draw each card once'
            List<ChanceCard> cards = []
            _cardCount.times {
                ChanceCard card = pile.drawCard()
                cards.add(card)
            }

        and: 'Check that the size is the same as pile.cards'
            pile.cards.size() == cards.size()

        and: 'but the order is different'
            pile.cards != cards

        and: 'finally check that the sorted drawn cards equals pile.cards'
            pile.cards == cards.sort {
                Integer.valueOf(it.id())
            }

        where:
            _cardCount << [100]
    }
}
