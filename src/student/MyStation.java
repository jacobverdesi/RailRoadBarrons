package student;

import model.Space;
import model.Station;
/**
 * Represents a train station on the map. A train station is a
 * {@linkplain Space space} that has a name and is at one end (origin) or the
 * other (destination) of at least one train route.
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MyStation implements Station {

    private int row, col;
    private String name;
    /**
     * creates a new station
     * @param name
     * @param row
     * @param col
     */
    public MyStation(String name, int row, int col) {
        this.row = row;
        this.col = col;
        this.name = name;
    }
    /**
     * The name of the station, e.g. "Trainsville Station".
     * @return The name of the station.
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Returns the row of the space's location in the map.
     *
     * @return The row of the space's location in the map.
     */
    @Override
    public int getRow() {
        return row;
    }
    /**
     * Returns the column of the space's location in the map.
     *
     * @return The column of the space's location in the map.
     */
    @Override
    public int getCol() {
        return col;
    }
    /**
     * Returns true if the other space is occupying the same physical location
     * in the map as this space.
     *
     * @param other The other space to which this space is being compared for
     *              collocation.
     *
     * @return True if the two spaces are in the same physical location (row
     * and column) in the map; false otherwise.
     */
    @Override
    public boolean collocated(Space other) {
        return other.getCol() == this.col && other.getRow() == this.row;
    }
    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Name: " + name + " Row: " + row + " Col: " + col;
    }
}
