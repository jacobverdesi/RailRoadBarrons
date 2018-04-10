package student;

import model.RailroadMap;
import model.RailroadMapObserver;
import model.Route;
import model.Space;

import java.util.Collection;

public class RailroadMapModel implements RailroadMap {

    @Override
    public void addObserver(RailroadMapObserver observer) {

    }

    @Override
    public int getCols() {
        return 0;
    }

    @Override
    public int getLengthOfShortestUnclaimedRoute() {
        return 0;
    }

    @Override
    public Route getRoute(int row, int col) {
        return null;
    }

    @Override
    public Collection<Route> getRoutes() {
        return null;
    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public Space getSpace(int row, int col) {
        return null;
    }

    @Override
    public void removeObserver(RailroadMapObserver observer) {

    }

    @Override
    public void routeClaimed(Route route) {

    }
}
