import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains circle bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class CircleBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(3);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(1);
        world.add(new Location(6, 0), alice);
        world.add(new Location(5, 3), bob);
        world.show();
    }
}