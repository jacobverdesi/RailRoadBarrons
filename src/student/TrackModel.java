package student;

import model.*;

public class TrackModel implements Track {

    public TrackModel(){

    }

    @Override
    public Baron getBaron() {
        return null;
    }

    @Override
    public Orientation getOrientation() {
        return null;
    }

    @Override
    public Route getRoute() {
        return null;
    }

    @Override
    public int getCol() {
        return 0;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public boolean collocated(Space other) {
        return false;
    }
}
