package model;

/**
 * Exception used whenever an invalid {@linkplain Route route} is created or
 * added to the {@linkplain RailroadMap Railroad Barons map}.
 */
public class InvalidRouteException extends RailroadBaronsException {
    /**
     * The message used for most invalid route exceptions.
     */
    private static final String MESSAGE = "Invalid route; ";

    /**
     * Creates a new invalid route exception with the specified origin and
     * destination {@linkplain Station stations}.
     *
     * @param origin The {@link Station} at the beginning of the invalid
     *               {@link Route}.
     * @param destination The {@link Station} at the end of the invalid
     *                    {@link Route}.
     */
    public InvalidRouteException(Station origin, Station destination) {
        super(MESSAGE + origin + "-" + destination);
    }
}
