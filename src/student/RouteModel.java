package student;

import model.*;

import java.util.List;

public class RouteModel implements Route{

    public RouteModel(){

    }

    @Override
    public boolean claim(Baron claimant) {
        return false;
    }

    @Override
    public Baron getBaron() {
        return null;
    }

    @Override
    public Station getDestination() {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public Orientation getOrientation() {
        return null;
    }

    @Override
    public int getPointValue() {
        return 0;
    }

    @Override
    public Station getOrigin() {
        return null;
    }

    @Override
    public List<Track> getTracks() {
        return null;
    }

    @Override
    public boolean includesCoordinate(Space space) {
        return false;
    }
}
