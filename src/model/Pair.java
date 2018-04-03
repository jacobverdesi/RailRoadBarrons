package model;

/**
 * {@linkplain Card Cards} in a {@linkplain RailroadBarons Railroad Barons}
 * game are dealt to each {@linkplain Player} in pairs. This class is used to
 * hold one such pair of {@linkplain Card cards}. Note that, if the deck is
 * empty, one or both cards may have a value of "{@link Card#NONE none}."
 */
public interface Pair {
    /**
     * Returns the first {@linkplain Card card} in the pair. Note that, if the
     * game deck is empty, the value of this card may be {@link Card#NONE}.
     *
     * @return The first {@link Card} in the pair.
     */
    Card getFirstCard();

    /**
     * Returns the second {@linkplain Card card} in the pair. if the
     * game deck is empty, the value of this card may be {@link Card#NONE}.
     *
     * @return The second {@link Card} in the pair.
     */
    Card getSecondCard();
}
