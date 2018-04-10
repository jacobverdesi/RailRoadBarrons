package student;

import model.*;

import java.util.Collection;
import java.util.List;

public class RailroadMapModel implements RailroadMap {

    private List<Space> spaces;
    private List<Station> stations;
    private List<Track> tracks;
    private List<Route> routes;

    public RailroadMapModel(){

    }

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
