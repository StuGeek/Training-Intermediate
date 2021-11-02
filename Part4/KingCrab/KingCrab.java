import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>KingCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    /**
     * Processes the elements of <code>actors</code>. New actors may be added
     * to empty locations. Override this method in subclasses to
     * process actors in a different way. <br />
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        Grid gr = getGrid();
        Location loc = getLocation();
        for (Actor a : actors) {
            Location actorLoc = a.getLocation();
            int dir;
            for (dir = Location.NORTH; dir <= Location.NORTHWEST; dir += Location.HALF_RIGHT) {
                if (loc.getAdjacentLocation(dir).equals(actorLoc)) {
                    break;
                }
            }
            Location nextLoc = a.getLocation().getAdjacentLocation(dir);
            if (gr.isValid(nextLoc)) {
                a.moveTo(nextLoc);
            }
            else {
                a.removeSelfFromGrid();
            }
        }
    }
}
