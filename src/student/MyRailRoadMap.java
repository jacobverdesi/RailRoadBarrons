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

//        for (Route r : routes) {
//            tracks.addAll(r.getTracks());
//        }
    }

    @Override
    public void addObserver(RailroadMapObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(RailroadMapObserver observer) {
        this.observers.remove(observer);
    }

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

    @Override
    public Collection<Route> getRoutes() {
        return routes;
    }

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

    @Override
    public Space getSpace(int row, int col) {
        return spaces[row][col];
    }

    @Override
    public void routeClaimed(Route route) {
        for (RailroadMapObserver o : observers) {
            o.routeClaimed(this, route);
        }
    }
}
