package model;

/**
 * An exception that may be thrown if an invalid {@linkplain Space space} is
 * created, i.e. a space with a negative row or column coordinate.
 */
public class SpaceException extends RailroadBaronsException {
    /**
     * Creates a new {@link SpaceException} for the specified coordinates.
     *
     * @param row The row for the invalid {@link Space}.
     * @param col The column for the invalid {@link Space}.
     */
    public SpaceException(int row, int col) {
        super("Invalid space: row=" + row + ",col=" + col);
    }
}
