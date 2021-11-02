import info.gridworld.grid.*;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A <code>SparseBoundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private HashMap<Location, E> occupantMap; // the map storing the grid elements
    private int rowsNum;
    private int columnsNum;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid2
     * @param cols number of columns in SparseBoundedGrid2
     */
    public SparseBoundedGrid2(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rowsNum = rows;
        columnsNum = cols;
        occupantMap = new HashMap<>();
    }

    /**
     * Bounded grid's rows is rowsNum.
     */
    public int getNumRows()
    {
        return rowsNum;
    }

    /**
     * Bounded grid's columns is columnsNum.
     */
    public int getNumCols()
    {
        return columnsNum;
    }

    /**
     * The number of row should be bigger than 0 and
     * smaller than rowsNum, and the number of columns shoule be
     * bigger than 0 and smaller than columnsNum in bounded grid.
     */
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
  
    /* 
     * Use the method in UnboundedGrid without change
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    /* 
     * Use the method in UnboundedGrid and add isValid method
     */
    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("The loc is null");
        if (!isValid(loc))
            throw new IllegalArgumentException("The Location " + loc
                    + " isn't valid");
        return occupantMap.get(loc);
    }

    /* 
     * Use the method in UnboundedGrid and add isValid method
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
        return occupantMap.put(loc, obj);
    }

    /* 
     * Use the method in UnboundedGrid and add isValid method
     */
    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.remove(loc);
    }
}