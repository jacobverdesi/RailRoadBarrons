package student;

import model.*;

import java.util.List;

public class MyRoute implements Route{

    private List<Track> tracks;
    private Baron baron;
    private Station origin, dest;

    public MyRoute(List<Track> tracks, Station origin, Station dest){
        this.tracks = tracks;
        this.origin = origin;
        this.dest = dest;
        this.baron = this.tracks.get(0).getBaron();
    }

    @Override
    public boolean claim(Baron claimant) {
        return claimant.equals(baron);
    }

    @Override
    public Baron getBaron() {
        return baron;
    }

    @Override
    public Station getDestination() {
        return dest;
    }

    @Override
    public Station getOrigin() {
        return origin;
    }

    @Override
    public int getLength() {
        return tracks.size();
    }

    @Override
    public Orientation getOrientation() {
        return tracks.get(0).getOrientation();
    }

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

    @Override
    public List<Track> getTracks() {
        return tracks;
    }

    @Override
    public boolean includesCoordinate(Space space) {
        for (Track t : tracks){
            if (t.getCol() == space.getCol() && t.getRow() == space.getRow()){
                return true;
            }
        }
        return false;
    }
}
