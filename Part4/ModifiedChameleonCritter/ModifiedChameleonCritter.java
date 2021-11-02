import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.logging.Logger;

/**
 * A <code>ModifiedChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ModifiedChameleonCritter extends Critter
{
    // lose 50% of color value in each step
    protected static final double DARKENING_FACTOR = 0.5;
    private Random rand;

    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, the color will darken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        // If the list of actors to process is empty, the color of the ModifiedChameleonCritter will darken
        if (n == 0) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            if (red < 0 || red > 255) {
                red /= (1 - DARKENING_FACTOR);
            }
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            if (green < 0 || green > 255) {
                green /= (1 - DARKENING_FACTOR);
            }
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            if (blue < 0 || blue > 255) {
                blue /= (1 - DARKENING_FACTOR);
            }

            setColor(new Color(red, green, blue));

            return;
        }

        try {
            rand = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e) {
            Logger log = Logger.getLogger("ModifiedChameleonCritter");
            log.info("rand error!");
        }
        int r = this.rand.nextInt(n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }
}
