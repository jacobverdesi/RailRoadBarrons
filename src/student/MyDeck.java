package student;

import model.Card;
import model.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Interface for a deck of {@linkplain Card cards} used in a Railroad Barons
 * game. The default deck has 20 of each type of playable card.
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MyDeck implements Deck{

    private ArrayList<Card> deck, drawn;

    /**
     * creates a deck with the cards
     * @param deck
     */
    public MyDeck(List<Card> deck){
        this.deck = new ArrayList<>();
        this.deck.addAll(deck);
        drawn = new ArrayList<>();
    }
    /**
     * Draws the next {@linkplain Card card} from the "top" of the deck.
     *
     * @return The next {@link Card}, unless the deck is empty, in which case
     * this should return {@link Card#NONE}.
     */
    @Override
    public Card drawACard() {
        Card card = deck.remove(0);
        drawn.add(card);
        return card;
    }
    /**
     * Returns the number of {@link Card cards} that have yet to be drawn.
     * @return The number of {@link Card cards} that have yet to be drawn.
     */
    @Override
    public int numberOfCardsRemaining() {
        return deck.size();
    }
    /**
     * Resets the {@linkplain Deck deck} to its starting state. Restores any
     * {@linkplain Card cards} that were drawn and shuffles the deck.
     */
    @Override
    public void reset() {
        deck.addAll(drawn);
        long seed = System.nanoTime();
        Collections.shuffle(deck, new Random(seed));
    }
}
