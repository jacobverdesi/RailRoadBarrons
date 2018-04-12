package student;

import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyRailRoadMap implements RailroadMap {

    private List<Space> spaces;
    private List<Station> stations;
    private List<Track> tracks;
    private List<Route> routes, claimed;
    private List<RailroadMapObserver> observers;

    public MyRailRoadMap(List<Route> routes, List<Station> stations, List<Space> spaces) {
        this.routes = routes;
        this.claimed = new ArrayList<>();
        this.stations = stations;
        this.spaces = spaces;
        observers = new ArrayList<>();
        for (Route r : routes) {
            tracks.addAll(r.getTracks());
        }
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
        return result;
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
        return result;
    }

    @Override
    public Space getSpace(int row, int col) {
        for (Space s : spaces) {
            if (s.getRow() == row && s.getCol() == col) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void routeClaimed(Route route) {
        claimed.add(route);
    }
}
