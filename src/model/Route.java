package model;

import java.util.List;

/**
 * Represents a train route in the Railroad Barons game. A route comprises
 * {@linkplain Track tracks} between two {@linkplain Station stations} on the
 * map. Valid routes must include two distinct (non-equal) stations, must be
 * either {@linkplain Orientation#HORIZONTAL horizontal} or
 * {@linkplain Orientation#VERTICAL vertical}, and the origin station must be
 * north of or to the west of the destination station (this simplifies some of
 * the route methods).
 */
public interface Route {
    /**
     * Returns the {@linkplain Baron} that has claimed this route. Note that
     * this route may be {@linkplain Baron#UNCLAIMED unclaimed}.
     *
     * @return The {@link Baron} that has claimed this route.
     */
    Baron getBaron();

    /**
     * Returns the {@linkplain Station station} at the beginning of this
     * route. The origin must be directly north of or to the west of the
     * destination.
     *
     * @return The {@link Station} at the beginning of this route.
     */
    Station getOrigin();

    /**
     * Returns the {@linkplain Station station} at the end of this route. The
     * destination must be directly south of or to the east of the origin.
     *
     * @return The {@link Station} at the end of this route.
     */
    Station getDestination();

    /**
     * Returns the {@linkplain Orientation orientation} of this route; either
     * {@linkplain Orientation#HORIZONTAL horizontal} or
     * {@linkplain Orientation#VERTICAL vertical}.
     *
     * @return The {@link Orientation} of this route.
     */
    Orientation getOrientation();

    /**
     * The set of {@linkplain Track tracks} that make up this route.
     *
     * @return The {@link List} of {@link Track tracks} that make up this
     * route.
     */
    List<Track> getTracks();

    /**
     * Returns the length of the route, not including the {@linkplain Station
     * stations} at the end points.
     *
     * @return The number of {@link Track Tracks} comprising this route.
     */
    int getLength();

    /**
     * Returns the number of points that this {@linkplain Route route} is
     * worth according to the following algorithm:
     * <ul>
     *     <li>1 - 1 point</li>
     *     <li>2 - 2 points</li>
     *     <li>3 - 4 points</li>
     *     <li>4 - 7 points</li>
     *     <li>5 - 10 points</li>
     *     <li>6 - 15 points</li>
     *     <li>7 (or more) - 5 * (length - 3) points</li>
     * </ul>
     *
     * @return The number of points that this route is worth.
     */
    int getPointValue();

    /**
     * Returns true if the route covers the ground at the location of the
     * specified {@linkplain Space space} and false otherwise.
     *
     * @param space The {@link Space} that may be in this route.
     *
     * @return True if the {@link Space Space's} coordinates are a part of
     * this route, and false otherwise.
     */
    boolean includesCoordinate(Space space);

    /**
     * Attempts to claim the route on behalf of the specified
     * {@linkplain Baron}. Only unclaimed routes may be claimed.
     *
     * @param claimant The {@link Baron} attempting to claim the route. Must
     *                 not be null or {@link Baron#UNCLAIMED}.
     * @return True if the route was successfully claimed. False otherwise.
     */
    boolean claim(Baron claimant);
}
