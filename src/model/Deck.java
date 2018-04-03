package model;

/**
 * Interface for a deck of {@linkplain Card cards} used in a Railroad Barons
 * game. The default deck has 20 of each type of playable card.
 */
public interface Deck {
    /**
     * Resets the {@linkplain Deck deck} to its starting state. Restores any
     * {@linkplain Card cards} that were drawn and shuffles the deck.
     */
    void reset();

    /**
     * Draws the next {@linkplain Card card} from the "top" of the deck.
     *
     * @return The next {@link Card}, unless the deck is empty, in which case
     * this should return {@link Card#NONE}.
     */
    Card drawACard();

    /**
     * Returns the number of {@link Card cards} that have yet to be drawn.
     *
     * @return The number of {@link Card cards} that have yet to be drawn.
     */
    int numberOfCardsRemaining();
}
