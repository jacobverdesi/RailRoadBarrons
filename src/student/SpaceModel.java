package student;

import model.Space;

public class SpaceModel implements Space{

    private int row, col;

    public SpaceModel(int row, int col){
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
}
