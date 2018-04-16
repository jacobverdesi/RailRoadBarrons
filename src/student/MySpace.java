package student;

import model.Space;
/**
 * Represents a space on the Railroad Barons map.
 * @Author Jacob Verdesi
 * @Author Andrew Yankowsky
 */
public class MySpace implements Space{

    private int row, col;

    /**
     * creates a new space
     * @param row
     * @param col
     */
    public MySpace(int row, int col){
        this.row = row;
        this.col = col;
    }
    /**
     * Returns the column of the space's location in the map.
     *
     * @return The column of the space's location in the map.
     */
    @Override
    public int getCol() {
        return col;
    }
    /**
     * Returns the row of the space's location in the map.
     *
     * @return The row of the space's location in the map.
     */
    @Override
    public int getRow() {
        return row;
    }
    /**
     * Returns true if the other space is occupying the same physical location
     * in the map as this space.
     *
     * @param other The other space to which this space is being compared for
     *              collocation.
     *
     * @return True if the two spaces are in the same physical location (row
     * and column) in the map; false otherwise.
     */
    @Override
    public boolean collocated(Space other) {
       return other.getCol() == this.col && other.getRow() == this.row;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Row: "+row+" Col: "+col;
    }
}
