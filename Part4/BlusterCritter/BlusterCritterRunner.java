import java.awt.Color;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains bluster critter. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritterRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        BlusterCritter b1 = new BlusterCritter(2);
        b1.setColor(Color.RED);
        BlusterCritter b2 = new BlusterCritter(2);
        b2.setColor(Color.YELLOW);
        BlusterCritter b3 = new BlusterCritter(2);
        b3.setColor(Color.BLUE);
        world.add(new Location(4, 4), b1);
        world.add(new Location(4, 6), b2);
        world.add(new Location(5, 8), b3);
        world.show();
    }
}