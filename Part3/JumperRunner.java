import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        
        /**
         * The location in front of the jumper is empty, 
         * but the location two cells in front contains a rock
         */
        Jumper jumper1 = new Jumper(Color.RED);
        world.add(new Location(5, 0), jumper1);
        world.add(new Location(3, 0), new Rock());
        
        /**
         * The location in front of the jumper is empty, 
         * but the location two cells in front contains a flower
         */
        Jumper jumper2 = new Jumper(Color.ORANGE);
        world.add(new Location(3, 1), jumper2);
        world.add(new Location(1, 1), new Flower());

        /**
         * The location two cells in front of the jumper is out of the grid 
         */
        Jumper jumper3 = new Jumper(Color.YELLOW);
        world.add(new Location(1, 2), jumper3);

        /**
         * The jumper is facing an edge of the grid
         */
        Jumper jumper4 = new Jumper(Color.GREEN);
        world.add(new Location(0, 3), jumper4);

        /**
         * If a jumper encounters another actor (not a flower or a rock)
         * is in the cell that is two cells in front of the jumper
         */
        Jumper jumper5 = new Jumper(Color.BLUE);
        world.add(new Location(5, 4), jumper5);
        Bug bug1 = new Bug();
        world.add(new Location(3, 4), bug1);

        /**
         * If a jumper encounters another jumper in its path, 
         * and two cells in front of the jumper is empty 
         */
        Jumper jumper6 = new Jumper(Color.PINK);
        world.add(new Location(5, 5), jumper6);
        Jumper jumper7 = new Jumper(Color.BLACK);
        world.add(new Location(4, 5), jumper7);

        /**
         * If a jumper encounters another jumper in its path, 
         * and two cells in front of the jumper is an actor or jumper
         */
        Jumper jumper8 = new Jumper(Color.GRAY);
        world.add(new Location(9, 6), jumper8);
        Jumper jumper9 = new Jumper(Color.WHITE);
        jumper9.setDirection(Location.EAST);
        world.add(new Location(7, 4), jumper9);

        /**
         * If a jumper encounters another jumper in its path, 
         * and two cells in front of the jumper is empty
         */
        Jumper jumper10 = new Jumper(Color.RED);
        world.add(new Location(4, 9), jumper10);
        Jumper jumper11 = new Jumper(Color.ORANGE);
        jumper11.setDirection(Location.EAST);
        world.add(new Location(3, 7), jumper11);

        /**
         * The location in front of the jumper is an rock, 
         * but the location two cells in front is empty
         */
        Jumper jumper12 = new Jumper(Color.YELLOW);
        world.add(new Location(8, 8), jumper12);
        world.add(new Location(7, 8), new Rock());
        
        /**
         * The location in front of the jumper is an flower, 
         * but the location two cells in front contains a rock
         */
        Jumper jumper13 = new Jumper(Color.BLUE);
        world.add(new Location(8, 9), jumper13);
        world.add(new Location(6, 9), new Rock());
        world.add(new Location(7, 9), new Flower());

        /**
         * The location in front of the jumper is empty, 
         * and the location two cells in front is empty
         */
        Jumper jumper14 = new Jumper(Color.BLACK);
        world.add(new Location(8, 1), jumper14);

        world.show();
    }
}