import info.gridworld.grid.*;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.*;

/**
 * An <code>UnboundedGrid2</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements
    private int dimension;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        dimension = 16;
        occupantArray = new Object[dimension][dimension];
    }

    /**
     * Unbounded grid's rows is -1.
     */
    public int getNumRows()
    {
        return -1;
    }

    /**
     * Unbounded grid's columns is -1.
     */
    public int getNumCols()
    {
        return -1;
    }

    /**
     * The number of row and the number of columns is 
     * bigger than 0 in unbounded grid.
     */
    public boolean isValid(Location loc)
    {
        return loc.getCol() >= 0 && loc.getRow() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<>();
        // Look at all grid locations.
        for (int r = 0; r < dimension; r++)
        {
            for (int c = 0; c < dimension; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    /**
     * The number of row and the number should be less than dimension, 
     * return null if they are no less than dimension. 
     */
    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("The loc is null");
        if (!isValid(loc))
            throw new IllegalArgumentException("The Location " + loc
                    + " isn't valid");
        int row = loc.getRow();
        int col = loc.getCol();
        if (row >= dimension || col >= dimension) {
            return null;
        }
        return (E) occupantArray[row][col];
    }

    /**
     * If the number of row and the number is no less than dimension, 
     * double size the array. 
     */
    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        int row = loc.getRow();
        int col = loc.getCol();
        if (row >= dimension || col >= dimension) {
            doubleSize(row, col);
        }
        E oldOccupant = get(loc);
        occupantArray[row][col] = obj;
        return oldOccupant;
    }

    /**
     * The number of row and the number should be less than dimension, 
     * return null if they are no less than dimension. 
     */
    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        int row = loc.getRow();
        int col = loc.getCol();
        if (row >= dimension || col >= dimension) {
            return null;
        }
        E r = get(loc);
        occupantArray[row][col] = null;
        return r;
    }

    /**
     * If the number of row and the number is no less than dimension, 
     * double size the array,
     * until the dimension is bigger than row or col
     */
    public void doubleSize(int row, int col) {
        int newDimension = dimension;
        while (newDimension <= row || newDimension <= col) {
            newDimension *= 2;
        }
        Object[][] newArray = new Object[newDimension][newDimension];
        for(int i = 0; i < dimension; ++i) {
            for(int j = 0; j < dimension; ++j) {
                newArray[i][j] = occupantArray[i][j];
                if (j == dimension - 1) {
                    break;
                }
            }
        }
        occupantArray = newArray;
        dimension = newDimension;
    }
}
