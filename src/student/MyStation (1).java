package student;

import model.Space;
import model.Station;

public class MyStation implements Station{

    private int row, col;
    public int stationNum;
    private String name;

    public MyStation(String name,int stationNum, int row, int col){
        this.row = row;
        this.col = col;
        this.name = name;
        this.stationNum=stationNum;
    }

    public int getStationNum() {
        return stationNum;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public boolean collocated(Space other) {
        if (other.getCol() == this.col && other.getRow() == this.row){
            return true;
        }
        return false;
    }
}
