package student;

import model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a train route in the Railroad Barons game. A route comprises
 * {@linkplain Track tracks} between two {@linkplain Station stations} on the
 * map. Valid routes must include two distinct (non-equal) stations, must be
 * either {@linkplain Orientation#HORIZONTAL horizontal} or
 * {@linkplain Orientation#VERTICAL vertical}, and the origin station must be
 * north of or to the west of the destination station (this simplifies some of
 * the route methods).
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MyRoute implements Route{

    private List<Track> tracks;
    private Baron baron;
    private Station origin, dest;

    /**
     * Initilizes origin destination orientation in route
     * adds tracks to the list of tracks
     * @param origin
     * @param dest
     * @param ort
     */
    public MyRoute(Station origin, Station dest, Orientation ort){
        this.origin = origin;
        this.dest = dest;
        this.baron = Baron.UNCLAIMED;
        tracks = new ArrayList<>();
        if (ort.equals(Orientation.HORIZONTAL)){
           for (int i = origin.getCol()+1; i <= dest.getCol()-1; i++){
                tracks.add(new MyTrack(ort,new MySpace( origin.getRow(), i), this));
            }
        }else {
            for (int i = origin.getRow()+1; i <= dest.getRow()-1; i++){
                tracks.add(new MyTrack(ort, new MySpace(i, origin.getCol()), this));
            }
        }
    }
    /**
     *
     * Attempts to claim the route on behalf of the specified
     * {@linkplain Baron}. Only unclaimed routes may be claimed.
     *
     * @param claimant The {@link Baron} attempting to claim the route. Must
     *                 not be null or {@link Baron#UNCLAIMED}.
     * @return True if the route was successfully claimed. False otherwise.
     */
    @Override
    public boolean claim(Baron claimant) {
        if (this.baron == Baron.UNCLAIMED){
            this.baron = claimant;
            return true;
        }else {
            return false;
        }
    }
    /**
     * Returns the {@linkplain Baron} that has claimed this route. Note that
     * this route may be {@linkplain Baron#UNCLAIMED unclaimed}.
     *
     * @return The {@link Baron} that has claimed this route.
     */
    @Override
    public Baron getBaron() {
        return baron;
    }
    /**
     * Returns the {@linkplain Station station} at the end of this route. The
     * destination must be directly south of or to the east of the origin.
     *
     * @return The {@link Station} at the end of this route.
     */
    @Override
    public Station getDestination() {
        return dest;
    }
    /**
     * Returns the {@linkplain Station station} at the beginning of this
     * route. The origin must be directly north of or to the west of the
     * destination.
     *
     * @return The {@link Station} at the beginning of this route.
     */
    @Override
    public Station getOrigin() {
        return origin;
    }
    /**
     * Returns the length of the route, not including the {@linkplain Station
     * stations} at the end points.
     *
     * @return The number of {@link Track Tracks} comprising this route.
     */
    @Override
    public int getLength() {
        return tracks.size();
    }
    /**
     * Returns the {@linkplain Orientation orientation} of this route; either
     * {@linkplain Orientation#HORIZONTAL horizontal} or
     * {@linkplain Orientation#VERTICAL vertical}.
     *
     * @return The {@link Orientation} of this route.
     */
    @Override
    public Orientation getOrientation() {
        return tracks.get(0).getOrientation();
    }
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
    @Override
    public int getPointValue() {
        switch (getLength()){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            default:
                return 5 * (getLength() - 3);
        }
    }
    /**
     * The set of {@linkplain Track tracks} that make up this route.
     *
     * @return The {@link List} of {@link Track tracks} that make up this
     * route.
     */
    @Override
    public List<Track> getTracks() {
        return tracks;
    }
    /**
     * Returns true if the route covers the ground at the location of the
     * specified {@linkplain Space space} and false otherwise.
     *
     * @param space The {@link Space} that may be in this route.
     *
     * @return True if the {@link Space Space's} coordinates are a part of
     * this route, and false otherwise.
     */
    @Override
    public boolean includesCoordinate(Space space) {
        for (Track t : tracks){
            if (t.getCol() == space.getCol() && t.getRow() == space.getRow()){
                return true;
            }
        }
        return false;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Route: "+origin.getName()+" -> "+dest.getName()+" Ort:"+getOrientation()+" claimed: "+baron;
    }
}
