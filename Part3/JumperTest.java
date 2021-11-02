import static org.junit.Assert.*;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

import java.awt.Color;

public class JumperTest {
    /**
     * The location in front of the jumper is empty, 
     * but the location two cells in front contains a rock
     */
    @Test
	public void test1() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper1 = new Jumper(Color.RED);
        world.add(new Location(5, 0), jumper1);
        world.add(new Location(3, 0), new Rock());

        world.step();

		assertEquals(new Location(5, 0), jumper1.getLocation());
        assertEquals(Location.NORTHEAST, jumper1.getDirection());
	}
    
    /**
     * The location in front of the jumper is empty, 
     * but the location two cells in front contains a flower
     */
    @Test
	public void test2() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper2 = new Jumper(Color.ORANGE);
        world.add(new Location(3, 1), jumper2);
        world.add(new Location(1, 1), new Flower());

        world.step();

		assertEquals(new Location(3, 1), jumper2.getLocation());
        assertEquals(Location.NORTHEAST, jumper2.getDirection());
	}

    /**
     * The location two cells in front of the jumper is out of the grid 
     */
    @Test
	public void test3() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper3 = new Jumper(Color.YELLOW);
        world.add(new Location(1, 2), jumper3);

        world.step();

		assertEquals(new Location(1, 2), jumper3.getLocation());
        assertEquals(Location.NORTHEAST, jumper3.getDirection());
	}

    /**
     * The jumper is facing an edge of the grid
     */
    @Test
	public void test4() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper4 = new Jumper(Color.GREEN);
        world.add(new Location(0, 3), jumper4);

        world.step();

		assertEquals(new Location(0, 3), jumper4.getLocation());
        assertEquals(Location.NORTHEAST, jumper4.getDirection());
	}

    /**
     * If a jumper encounters another actor (not a flower or a rock)
     * is in the cell that is two cells in front of the jumper
     */
    @Test
	public void test5() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper5 = new Jumper(Color.BLUE);
        world.add(new Location(5, 4), jumper5);
        Bug bug1 = new Bug();
        world.add(new Location(3, 4), bug1);

        world.step();

		assertEquals(new Location(5, 4), jumper5.getLocation());
        assertEquals(Location.NORTHEAST, jumper5.getDirection());
	}

    /**
     * If a jumper encounters another jumper in its path, 
     * and two cells in front of the jumper is empty 
     */
    @Test
	public void test6() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper6 = new Jumper(Color.PINK);
        world.add(new Location(5, 5), jumper6);
        Jumper jumper7 = new Jumper(Color.BLACK);
        world.add(new Location(4, 5), jumper7);

        world.step();

		assertEquals(new Location(3, 5), jumper6.getLocation());
        assertEquals(Location.NORTH, jumper6.getDirection());
	}

    /**
     * If a jumper encounters another jumper in its path, 
     * and two cells in front of the jumper is an actor or jumper
     */
    @Test
	public void test7() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper8 = new Jumper(Color.GRAY);
        world.add(new Location(9, 6), jumper8);
        Jumper jumper9 = new Jumper(Color.WHITE);
        jumper9.setDirection(Location.EAST);
        world.add(new Location(7, 4), jumper9);

        world.step();

		assertEquals(new Location(9, 6), jumper8.getLocation());
        assertEquals(Location.NORTHEAST, jumper8.getDirection());
	}

    /**
     * If a jumper encounters another jumper in its path, 
     * and two cells in front of the jumper is empty
     */
    @Test
	public void test8() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper10 = new Jumper(Color.RED);
        world.add(new Location(4, 9), jumper10);
        Jumper jumper11 = new Jumper(Color.ORANGE);
        jumper11.setDirection(Location.EAST);
        world.add(new Location(3, 7), jumper11);

        world.step();

		assertEquals(new Location(2, 9), jumper10.getLocation());
        assertEquals(Location.NORTH, jumper10.getDirection());
	}

    /**
     * The location in front of the jumper is an rock, 
     * but the location two cells in front is empty
     */
    @Test
	public void test9() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper12 = new Jumper(Color.YELLOW);
        world.add(new Location(8, 8), jumper12);
        world.add(new Location(7, 8), new Rock());

        world.step();

		assertEquals(new Location(6, 8), jumper12.getLocation());
        assertEquals(Location.NORTH, jumper12.getDirection());
	}

    /**
     * The location in front of the jumper is an flower, 
     * but the location two cells in front contains a rock
     */
    @Test
	public void test10() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper13 = new Jumper(Color.BLUE);
        world.add(new Location(8, 9), jumper13);
        world.add(new Location(6, 9), new Rock());
        world.add(new Location(7, 9), new Flower());

        world.step();

		assertEquals(new Location(8, 9), jumper13.getLocation());
        assertEquals(Location.NORTHEAST, jumper13.getDirection());
	}

    /**
     * The location in front of the jumper is empty, 
     * and the location two cells in front is empty
     */
    @Test
	public void test11() {
        ActorWorld world = new ActorWorld();
        
        Jumper jumper14 = new Jumper(Color.BLACK);
        world.add(new Location(8, 1), jumper14);

        world.step();

		assertEquals(new Location(6, 1), jumper14.getLocation());
        assertEquals(Location.NORTH, jumper14.getDirection());
	}
}
