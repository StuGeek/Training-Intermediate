import info.gridworld.grid.*;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] occupantArray; // the array storing the grid elements
    private int rowsNum;
    private int columnsNum;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rowsNum = rows;
        columnsNum = cols;
        occupantArray = new SparseGridNode[rows];
        for (int i = 0; i < rows; ++i) {
            occupantArray[i] = null;
        }
    }

    public int getNumRows()
    {
        return rowsNum;
    }

    public int getNumCols()
    {
        return columnsNum;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<>();

        // Look at sparse grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            // Put the location in the array.
            SparseGridNode curNode = occupantArray[r];
            while(curNode != null) {
                Location loc = new Location(r, curNode.getCol());
                theLocations.add(loc);
                curNode = curNode.getNext();
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("The Location " + loc
                    + " isn't valid");
        int row = loc.getRow();
        int col = loc.getCol();

        SparseGridNode node = occupantArray[row];
        while (node != null) {
            if (node.getCol() == col) {
                break;
            }
            else {
                node = node.getNext();
            }
        }
        if (node == null) {
            return null;
        }
        else {
            return (E)node.getOccupant();
        }
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        int row = loc.getRow();
        int col = loc.getCol();
        SparseGridNode node = occupantArray[row];
        SparseGridNode preNode = null;
        if (node == null) {
            occupantArray[row] = new SparseGridNode(obj, col, null);
            return null;
        }

        while (node != null) {
            if (node.getCol() == col) {
                break;
            }
            else {
                preNode = node;
                node = node.getNext();
            }
        }
        if (node == null) {
            SparseGridNode newNode = new SparseGridNode(obj, col, null);
            preNode.setNext(newNode);
            return null;
        }
        else if (preNode == null) {
            occupantArray[row].setOccupant(obj);
        }
        else {
            preNode.getNext().setOccupant(obj);
        }
        return (E)node.getOccupant();
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        int row = loc.getRow();
        int col = loc.getCol();

        SparseGridNode node = occupantArray[row];
        SparseGridNode preNode = null;
        while (node != null) {
            if (node.getCol() == col) {
                break;
            }
            else {
                preNode = node;
                node = node.getNext();
            }
        }
        if (node == null) {
            return null;
        }
        else if (preNode == null) {
            occupantArray[row] = null;
        }
        else {
            preNode.setNext(node.getNext());
        }
        return (E)node.getOccupant();
    }
}