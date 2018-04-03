package model;

/**
 * Interface for a class that should be notified whenever the state of a
 * {@linkplain RailroadBarons Railroad Barons game} changes.
 */
public interface RailroadBaronsObserver {
    /**
     * Called when a {@linkplain Player player's} turn has started.
     * @param game The {@link RailroadBarons} game for which a new turn has
     *             started.
     * @param player The {@link Player} that has just started a turn.
     */
    void turnStarted(RailroadBarons game, Player player);

    /**
     * Called when a {@linkplain Player player's} turn has ended.
     *
     * @param game The {@link RailroadBarons} game for which the current turn
     *             has ended.
     * @param player The {@link Player} whose turn has ended.
     */
    void turnEnded(RailroadBarons game, Player player);

    /**
     * Called when the {@linkplain RailroadBarons Railroad Barons game} is
     * over.
     *
     * @param game The {@link RailroadBarons} game that has ended.
     * @param winner The winning {@link Player}.
     */
    void gameOver(RailroadBarons game, Player winner);
}
