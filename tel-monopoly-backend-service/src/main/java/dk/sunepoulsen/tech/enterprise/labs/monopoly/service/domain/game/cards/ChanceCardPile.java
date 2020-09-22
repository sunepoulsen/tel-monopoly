package dk.sunepoulsen.tech.enterprise.labs.monopoly.service.domain.game.cards;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * A pile of chance cards.
 * <p>
 *     It can draw the next chance card from a pile and shuffle them.
 * </p>
 */
public class ChanceCardPile {
    private List<ChanceCard> cards;
    private Queue<ChanceCard> pile;

    public ChanceCardPile() {
        this.cards = new ArrayList<>();
    }

    public void addCard(ChanceCard card) {
        this.cards.add(card);
    }

    void shuffle() {
        Random shuffler = new Random();
        List<ChanceCard> tempCards = new ArrayList<>(this.cards);

        this.pile = new ArrayDeque<>();
        while(!tempCards.isEmpty()) {
            int i = shuffler.nextInt(tempCards.size());
            this.pile.add(tempCards.get(i));
            tempCards.remove(i);
        }
    }

    /**
     * Draws the next card from the pile.
     * <p>
     *     The card is put back into the tail of the pile before it is returned.
     * </p>
     *
     * @return The top card from the pile.
     */
    public ChanceCard drawCard() {
        ChanceCard card = this.pile.remove();
        this.pile.add(card);

        return card;
    }
}
