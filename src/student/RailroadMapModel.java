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
        int result = 0;
        for (Station s : stations){
            if (s.getCol() > result){
                result = s.getCol();
            }
        }
        return result;
    }

    @Override
    public int getLengthOfShortestUnclaimedRoute() {
        int result = routes.get(0).getLength();
        for (Route r : routes){
            if (r.getLength() < result){
                result = r.getLength();
            }
        }
        return result;
    }

    @Override
    public Route getRoute(int row, int col) {
        for (Route r : routes){
            for (Track t : r.getTracks()){
                if (t.getRow() == row && t.getCol() == col){
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
        for (Station s : stations){
            if (s.getRow() > result){
                result = s.getRow();
            }
        }
        return result;
    }

    @Override
    public Space getSpace(int row, int col) {
        for (Space s : spaces){
            if (s.getRow() == row && s.getCol() == col){
                return s;
            }
        }
        return null;
    }

    @Override
    public void removeObserver(RailroadMapObserver observer) {

    }

    @Override
    public void routeClaimed(Route route) {
        for (Track t : route.getTracks()){

        }
    }
}
