package student;

import model.Space;
import model.Station;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Represents a train station on the map. A train station is a
 * {@linkplain Space space} that has a name and is at one end (origin) or the
 * other (destination) of at least one train route.
 *
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MyStation implements Station {

    private Space space;
    private String name;
    private ArrayList<MyStation> neighbors;

    /**
     * creates a new station
     *
     * @param name
     * @param space
     */
    public MyStation(String name, Space space) {
        this.space = space;
        this.name = name;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(MyStation n) {
        if (!neighbors.contains(n)) {
            neighbors.add(n);
        }
    }
    public Collection<MyStation> getNeighbors() {
        return neighbors;
    }

    /**
     * The name of the station, e.g. "Trainsville Station".
     *
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
        return space.getRow();
    }

    /**
     * Returns the column of the space's location in the map.
     *
     * @return The column of the space's location in the map.
     */
    @Override
    public int getCol() {
        return space.getCol();
    }

    /**
     * Returns true if the other space is occupying the same physical location
     * in the map as this space.
     *
     * @param other The other space to which this space is being compared for
     *              collocation.
     * @return True if the two spaces are in the same physical location (row
     * and column) in the map; false otherwise.
     */
    @Override
    public boolean collocated(Space other) {
        return other.getCol() == getCol() && other.getRow() == getRow();
    }

    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Name: " + name + " Row: " + getRow() + " Col: " + getCol();
    }
}
