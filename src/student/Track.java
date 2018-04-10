package student;

import model.Baron;
import model.Orientation;
import model.Route;

/**
 * Represents a track segment on the map.
 * Tracks combine to form routes.
 */
public interface Track extends Space {

    /**
     * Returns the orientation of the track; either horizontal or vertical. This is based
     * on the orientation of the route that contains the track.
     * @return The Orientation of the Track; this is the same as the Orientation of the Route that contains the track.
     */
    Orientation getOrientation();

    /**
     * Returns the current owner of this track, either unclaimed if
     * the track has not been claimed, or the owner that corresponds
     * with the color of the player that successfully claimed the
     * route of which this track is a part.
     * @return The Baron that has claimed the route of which this track is a part.
     */
    Baron getBaron();

    /**
     * Returns the route of which this track is a part.
     * @return The Route that contains this track.
     */
    Route getRoute();

}
