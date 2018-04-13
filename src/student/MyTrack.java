package student;

import model.*;

public class MyTrack implements Track {

    private Baron baron;
    private Orientation ort;
    private Route route;
    private int row, col;

    public MyTrack(Orientation ort, int row, int col, Route route){
        this.baron = route.getBaron();
        this.ort = ort;
        this.route = route;
        this.row = row;
        this.col = col;
    }

    @Override
    public Baron getBaron() {
        return baron;
    }

    @Override
    public Orientation getOrientation() {
        return ort;
    }

    @Override
    public Route getRoute() {
        return route;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public boolean collocated(Space other) {
        if (other.getCol() == this.col && other.getRow() == this.row){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " Row: "+row+" Col: "+col +" ";
    }
}
