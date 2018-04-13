package student;

import model.Space;

public class MySpace implements Space{

    private int row, col;

    public MySpace(int row, int col){
        this.row = row;
        this.col = col;
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
        return "Row: "+row+" Col: "+col;
    }
}
