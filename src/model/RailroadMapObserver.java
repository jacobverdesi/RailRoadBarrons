package model;

/**
 * The interface that should be implemented by a class that observes a
 * {@linkplain RailroadMap Railroad Barons map} for significant events.
 */
public interface RailroadMapObserver {
    /**
     * Called when a {@linkplain Route route} is successfully claimed on the
     * {@linkplain RailroadMap map}.
     *
     * @param map The {@link RailroadMap} on which the {@link Route} has been
     *            claimed.
     *
     * @param route The {@link Route} that has been claimed.
     */
    void routeClaimed(RailroadMap map, Route route);
}
