import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can jump.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red Jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a Jumper of a given color.
     * @param jumperColor the color for this Jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * jumps if it can jump, turns otherwise.
     */
    public void act()
    {
        if (canJump())
            jump();
        else
            turn();
    }

    /**
     * Turns the Jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * jumps the Jumper forward.
     */
    public void jump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        int direction = getDirection();
        Location next = loc.getAdjacentLocation(direction).getAdjacentLocation(direction);
        if (gr.isValid(next)) {
            moveTo(next);   
        }
        else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this Jumper can jump forward into a location that is empty.
     * @return true if this Jumper can jump.
     */
    public boolean canJump()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        int direction = getDirection();
        Location next = loc.getAdjacentLocation(direction).getAdjacentLocation(direction);
        if (!gr.isValid(next)) {
            return false;
        }
        Actor nextActor = gr.get(next);
        return nextActor == null;
        // ok to jump into empty location
        // not ok to jump onto any other actor or jumper
    }
}
