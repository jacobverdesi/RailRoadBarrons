package model;

/**
 * Represents a track segment on the map. Tracks combine to form
 * {@linkplain Route routes}.
 */
public interface Track extends Space {
    /**
     * Returns the orientation of the track; either
     * {@linkplain Orientation#HORIZONTAL horizontal} or
     * {@linkplain Orientation#VERTICAL vertical}. This is based on the
     * {@linkplain Orientation orientation} of the {@linkplain Route route}
     * that contains the track.
     *
     * @return The {@link Orientation} of the {@link Track}; this is the same
     * as the {@link Orientation} of the {@link Route} that contains the
     * track.
     */
    Orientation getOrientation();

    /**
     * Returns the current {@link Baron owner} of this track, either
     * {@linkplain Baron#UNCLAIMED unclaimed} if the track has not been
     * claimed, or the {@linkplain Baron owner} that corresponds with the
     * color of the player that successfully claimed the
     * {@linkplain Route route} of which this track is a part.
     *
     * @return The {@link Baron} that has claimed the route of which this
     * track is a part.
     */
    Baron getBaron();

    /**
     * Returns the {@linkplain Route route} of which this
     * {@linkplain Track track} is a part.
     *
     * @return The {@link Route} that contains this track.
     */
    Route getRoute();
}
