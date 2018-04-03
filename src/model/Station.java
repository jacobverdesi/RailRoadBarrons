package model;

/**
 * Represents a train station on the map. A train station is a
 * {@linkplain Space space} that has a name and is at one end (origin) or the
 * other (destination) of at least one train route.
 */
public interface Station extends Space {
    /**
     * The name of the station, e.g. "Trainsville Station".
     *
     * @return The name of the station.
     */
    String getName();
}
