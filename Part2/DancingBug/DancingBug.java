import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a dancing route of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int sideLength;
    private int [] turnsTimes;
    private int turnsTimesEntry = 0;

    /**
     * Constructs a box bug that traces a dancing route of a given side length
     * @param length the side length
     */
    public DancingBug(int length, int [] turnsTimes)
    {
        steps = 0;
        sideLength = length;
        this.turnsTimes = turnsTimes;
    }
    
    public void turns(int turnTimes) {
    	for (int i = 0; i < turnTimes; ++i) {
    		turn();
    	}
    }

    /**
     * Moves to the next location of the dancing route.
     */
    public void act()
    {
        if (steps < sideLength)
        {
        	if (canMove())
            {
                move();
                steps++;
            }
        }
        else
        {	
        	turns(turnsTimes[turnsTimesEntry]);
        	turnsTimesEntry = (turnsTimesEntry + 1) % turnsTimes.length;
            steps = 0;
        }
    }
}
