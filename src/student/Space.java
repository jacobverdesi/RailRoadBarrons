package student;

/**
 * Represents a space on the Railroad Barons map.
 */
public interface Space {

    /**
     * Returns the row of the space's location in the map.
     * @return The row of the space's location in the map.
     */
    int getRow();

    /**
     * Returns the column of the space's location in the map.
     * @return The column of the space's location in the map.
     */
    int getCol();

    /**
     * Returns true if the other space is occupying the
     * same physical location in the map as this space.
     * @param other The other space to which this space is being compared for collocation.
     * @return True if the two spaces are in the same physical location (row and column) in the map; false otherwise.
     */
    boolean collocated(Space other);

}
