package student;

import model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Represents a Railroad Barons map, which consists of empty
 * {@linkplain Space spaces}, {@linkplain Station stations},
 * {@linkplain Track tracks}, and {@linkplain Route routes}.
 */
public class MyRailRoadMap implements RailroadMap {

    private Space[][] spaces;
    private List<Station> stations;
    private List<Route> routes;
    private List<RailroadMapObserver> observers;

    /**
     * initilizes all the routs and creates stations
     * @param routes
     */
    public MyRailRoadMap(List<Route> routes) {
        this.routes = routes;
        this.stations=new ArrayList<>();
        for (Route route : routes) {
            if (!stations.contains(route.getOrigin())) {
                stations.add(route.getDestination());
            }
            if (!stations.contains(route.getDestination())) {
                stations.add(route.getDestination());
            }
        }
        spaces = new Space[this.getRows() + 1][this.getCols() + 1];
        for (Route route : routes) {
            Station origin=route.getOrigin();
            Station dest=route.getDestination();
            spaces[origin.getRow()][origin.getCol()]=origin;
            spaces[dest.getRow()][dest.getCol()]=dest;
            for (Track track : route.getTracks()) {
                spaces[track.getRow()][track.getCol()] = track;
            }
        }
        observers = new ArrayList<>();

    }
    /**
     * Adds the specified {@linkplain RailroadMapObserver observer} to the
     * map. The observer will be notified of significant events involving this
     * map such as when a {@linkplain Route route} has been claimed by a
     * {@linkplain Baron}.
     *
     * @param observer The {@link RailroadMapObserver} being added to the map.
     */
    @Override
    public void addObserver(RailroadMapObserver observer) {
        this.observers.add(observer);
    }
    /**
     * Removes the specified {@linkplain RailroadMapObserver observer} from
     * the map. The observer will no longer be notified of significant events
     * involving this map.
     *
     * @param observer The observer to remove from the collection of
     *                 registered observers that will be notified of
     *                 significant events involving this map.
     */
    @Override
    public void removeObserver(RailroadMapObserver observer) {
        this.observers.remove(observer);
    }
    /**
     * Returns the number of columns in the map. This is determined by the
     * location of the easternmost {@linkplain Station station} on the map.
     *
     * @return The number of columns in the map.
     */
    @Override
    public int getCols() {
        int result = 0;
        for (Station s : stations) {
            if (s.getCol() > result) {
                result = s.getCol();
            }
        }
        return result+1;
    }
    /**
     * Returns the length of the shortest unclaimed {@linkplain Route route}
     * in the map.
     *
     * @return The length of the shortest unclaimed {@link Route}.
     */
    @Override
    public int getLengthOfShortestUnclaimedRoute() {
        int result = routes.get(0).getLength();
        for (Route r : routes) {
            if (r.getLength() < result) {
                result = r.getLength();
            }
        }
        return result;
    }
    /**
     * Returns the {@linkplain Route route} that contains the
     * {@link Track track} at the specified location (if such a route exists}.
     *
     * @param row The row of the location of one of the {@link Track tracks}
     *            in the route.
     * @param col The column of the location of one of the
     * {@link Track tracks} in the route.
     *
     * @return The {@link Route} that contains the {@link Track} at the
     * specified location, or null if there is no such {@link Route}.
     */
    @Override
    public Route getRoute(int row, int col) {
        for (Route r : routes) {
            for (Track t : r.getTracks()) {
                if (t.getRow() == row && t.getCol() == col) {
                    return r;
                }
            }
        }
        return null;
    }
    /**
     * Returns all of the {@link Route Routes} in this map.
     *
     * @return A {@link Collection} of all of the {@link Route Routes} in this
     * RailroadMap.
     */
    @Override
    public Collection<Route> getRoutes() {
        return routes;
    }
    /**
     * Returns the number of rows in the map. This is determined by the
     * location of the southernmost {@linkplain Station station} on the map.
     *
     * @return The number of rows in the map.
     */
    @Override
    public int getRows() {
        int result = 0;
        for (Station s : stations) {
            if (s.getRow() > result) {
                result = s.getRow();
            }
        }
        return result+1;
    }
    /**
     * Returns the {@linkplain Space space} located at the specified
     * coordinates.
     *
     * @param row The row of the desired {@link Space}.
     * @param col The column of the desired {@link Space}.
     *
     * @return The {@link Space} at the specified location, or null if the
     * location doesn't exist on this map.
     */
    @Override
    public Space getSpace(int row, int col) {
        return spaces[row][col];
    }
    /**
     * Called to update the {@linkplain RailroadMap map} when a
     * {@linkplain Baron} has claimed a {@linkplain Route route}.
     *
     * @param route The {@link Route} that has been claimed.
     */
    @Override
    public void routeClaimed(Route route) {
        for (RailroadMapObserver o : observers) {
            o.routeClaimed(this, route);
        }
    }
}
