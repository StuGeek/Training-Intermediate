import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    /**
     * @return list of empty locations to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getTwoSpacesLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);
        if (locs.size() > 0) {
            return locs;
        }
        for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);

        return locs;
    }
    
    /**
     * Finds two spaces valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are two spaces neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getTwoSpacesLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            Location twoLoc = neighborLoc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc) && gr.isValid(twoLoc) &&
                    gr.get(neighborLoc) == null && gr.get(twoLoc) == null) {
                locs.add(twoLoc);
            }
        }
        return locs;
    }    
}
