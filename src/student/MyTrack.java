package student;

import model.*;
/**
 * Represents a track segment on the map. Tracks combine to form
 * {@linkplain Route routes}.
 */
public class MyTrack implements Track {
    private Baron baron;
    private Orientation ort;
    private Route route;
    private int row, col;
    /**
     * creates a track with a route and orientaton
     * @param ort
     * @param row
     * @param col
     * @param route
     */
    public MyTrack(Orientation ort, int row, int col, Route route){
        this.baron = route.getBaron();
        this.ort = ort;
        this.route = route;
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the current {@link Baron owner} of this track, either
     * {@linkplain Baron#UNCLAIMED unclaimed} if the track has not been
     * claimed, or the {@linkplain Baron owner} that corresponds with the
     * color of the player that successfully claimed the
     * {@linkplain Route route} of which this track is a part.
     * @return The {@link Baron} that has claimed the route of which this
     * track is a part.
     */
    @Override
    public Baron getBaron() {
        return baron;
    }
    /**
     * Returns the orientation of the track; either
     * {@linkplain Orientation#HORIZONTAL horizontal} or
     * {@linkplain Orientation#VERTICAL vertical}. This is based on the
     * {@linkplain Orientation orientation} of the {@linkplain Route route}
     * that contains the track.
     * @return The {@link Orientation} of the {@link Track}; this is the same
     * as the {@link Orientation} of the {@link Route} that contains the
     * track.
     */
    @Override
    public Orientation getOrientation() {
        return ort;
    }
    /**
     * Returns the {@linkplain Route route} of which this
     * {@linkplain Track track} is a part.
     * @return The {@link Route} that contains this track.
     */
    @Override
    public Route getRoute() {
        return route;
    }
    /**
     * Returns the column of the space's location in the map.
     * @return The column of the space's location in the map.
     */
    @Override
    public int getCol() {
        return col;
    }
    /**
     * Returns the row of the space's location in the map.
     * @return The row of the space's location in the map.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns true if the other space is occupying the same physical location
     * in the map as this space.
     * @param other The other space to which this space is being compared for
     *              collocation.
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
        return " Row: "+row+" Col: "+col +" ";
    }
}
