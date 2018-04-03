package model;

/**
 * Implemented by classes that should be notified whenever a {@link Player} of
 * interest changes in some way.
 */
public interface PlayerObserver {
    /**
     * Called whenever the {@linkplain Player player} of interest has changed
     * in some way including:
     * <ul>
     *     <li>The number of cards in the player's hand.</li>
     *     <li>The player's score.</li>
     *     <li>The number of train pieces that the player has remaining.</li>
     *     <li>A {@link Route route} has been claimed by the player.</li>
     * </ul>
     * @param player The {@link Player} of interest.
     */
    void playerChanged(Player player);
}
