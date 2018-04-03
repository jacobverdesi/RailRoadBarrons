package model;

/**
 * A general exception that may be thrown by any class in the game if an error
 * occurs.
 */
public class RailroadBaronsException extends Exception {
    /**
     * Creates a new Railroad Barons exception with the specified message.
     *
     * @param message The error message associated with this exception.
     */
    public RailroadBaronsException(String message) {
        super(message);
    }
}
