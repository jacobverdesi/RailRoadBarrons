package student;

import model.Card;
import model.Pair;
import model.Player;
import model.RailroadBarons;

/**
 * {@linkplain Card Cards} in a {@linkplain RailroadBarons Railroad Barons}
 * game are dealt to each {@linkplain Player} in pairs. This class is used to
 * hold one such pair of {@linkplain Card cards}. Note that, if the deck is
 * empty, one or both cards may have a value of "{@link Card#NONE none}."
 */
public class MyPair implements Pair {
    private Card first, second;

    /**
     * creates a pair of cards
     * @param first
     * @param second
     */
    public MyPair(Card first, Card second){
        this.first = first;
        this.second = second;
    }
    /**
     * Returns the first {@linkplain Card card} in the pair. Note that, if the
     * game deck is empty, the value of this card may be {@link Card#NONE}.
     *
     * @return The first {@link Card} in the pair.
     */
    public Card getFirstCard() {
        return first;
    }
    /**
     * Returns the second {@linkplain Card card} in the pair. if the
     * game deck is empty, the value of this card may be {@link Card#NONE}.
     *
     * @return The second {@link Card} in the pair.
     */
    public Card getSecondCard() {
        return second;
    }

    /**
     * Tostring for testing
     * @return
     */
    @Override
    public String toString() {
        return "First:"+first+" , Second: "+second;
    }
}
