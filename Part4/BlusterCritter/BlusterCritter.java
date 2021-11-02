import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

/**
 * A <code>BlusterCritter</code> is an actor that moves through its world, processing
 * other actors in some way and then moving to a new location. Define your own
 * critters by extending this class and overriding any methods of this class
 * except for <code>act</code>. When you override these methods, be sure to
 * preserve the postconditions. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter {
    private static final double LIGHTENING_FACTOR = -0.5;
    private static final double DARKENING_FACTOR = 0.5;
    private int courageVal;

    public BlusterCritter(int c) {
        courageVal = c;
    }

    /**
     * A bluster critter gets the actors within two steps of its current location
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<>();
        Grid gr = getGrid();
        int beginRow = getLocation().getRow() - 2;
        int beginCol = getLocation().getCol() - 2;
        for(int row = beginRow; row <= beginRow + 4; ++row) {
            for(int col = beginCol; col <= beginCol + 4; ++col) {
                Location loc = new Location(row, col);
                if (gr.isValid(loc)) {
                    Actor actor = (Actor)gr.get(loc);
                    if ((actor instanceof Critter) && actor != this) {
                        actors.add(actor);
                    }
                }
            }
        }

        return actors;
    }

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
        int actorNum = actors.size();
        if (actorNum < courageVal) {
            changeColor(LIGHTENING_FACTOR);
        }
        else {
            changeColor(DARKENING_FACTOR);
        }
    }

    private void changeColor(double factor)
    {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - factor));
        if (red < 0 || red > 255) {
            red /= (1 - factor);
        }
        int green = (int) (c.getGreen() * (1 - factor));
        if (green < 0 || green > 255) {
            green /= (1 - factor);
        }
        int blue = (int) (c.getBlue() * (1 - factor));
        if (blue < 0 || blue > 255) {
            blue /= (1 - factor);
        }

        setColor(new Color(red, green, blue));
    }
}
