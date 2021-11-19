package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;  // Record the position to walk to in the next step
	private Location last;  //  Record the position of the previous step
	private boolean isEnd = false;  // If the bug find the exit
	private Deque<ArrayList<Location>> crossLocation = new LinkedList<>(); // Stack of nodes of the record tree
	private Integer stepCount = 0; // Record the steps taken by the maze to the exit
	boolean hasShown = false;// final message has been shown
	
	private Random rand;
	// record the times of the direction the bug choose to move when there are more than one way
	private int [] dirPredict = { 1, 1, 1, 1 };
	// record if the bug has been to the location
	private Set<Location> visited = new HashSet<>();
	// record the direction the bug choose to move when there are more than one way
	private Deque<Integer> choice = new LinkedList<>();
	// record the current node and last node in the stack crossLocation
	private static int curIndex = 0;
	private static int lastIndex = 1;
	
	// choose the next position to move when there are more than one way
	private static int randomMode = 0;
	private static int predictMode = 1;
	private int chooseMode;
	
	/**
	 * Constructs a maze bug
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		chooseMode = predictMode;
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		Location curLoc = getLocation();
		if (stepCount == 0) {
			ArrayList<Location> node = new ArrayList<>(Arrays.asList(curLoc, last));
			crossLocation.push(node);
			visited.add(curLoc);
		}
		boolean willMove = canMove();
		if (isEnd) {
			// to show step count when reach the goal
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			List<Location> notVis = getNotVis(curLoc);
			if (notVis.size() == 0) {
				actBack();
			}
			else {
				if (notVis.size() == 1) {
					actWhenOneWay(notVis, curLoc);
				}
				else {
					actWhenMulWays(notVis, curLoc);
				}
				ArrayList<Location> node = new ArrayList<>(Arrays.asList(next, curLoc));
				crossLocation.push(node);
				visited.add(next);
			}
			last = curLoc;
			move();
			// increase step count when move
			stepCount++;
		}
	}
	
	/**
	 * The bug will act back when there are no location to move
	 */
	public void actBack() {
		ArrayList<Location> lastNode = crossLocation.pop();
		next = lastNode.get(lastIndex);
		setDirection(lastNode.get(curIndex).getDirectionToward(next));
		if (next == last) {
			int forwardDir = choice.pop();
			dirPredict[forwardDir / 90]--;
		}
	}
	
	/**
	 * When there are only one location can move to, the bug's action.
	 */
	public void actWhenOneWay(List<Location> notVis, Location curLoc) {
		next = notVis.get(0);
		if (getValid(curLoc).size() > 2) {
			int forwardDir = curLoc.getDirectionToward(next);
			dirPredict[forwardDir / 90]++;
			choice.push(forwardDir);
		}
	}
	
	/**
	 * When there are more than one location can move to, the bug's action.
	 */
	public void actWhenMulWays(List<Location> notVis, Location curLoc) {
		next = getNextLoc(notVis);
		int forwardDir = curLoc.getDirectionToward(next);
		dirPredict[forwardDir / 90]++;
		choice.push(forwardDir);
	}
	
	/**
	 * Find all positions that can be move to and not visited.
	 * 
	 * @param loc the location to detect.
	 * @return List of positions.
	 */
	public List<Location> getNotVis(Location loc) {
		List<Location> valid = getValid(loc);
		List<Location> notVis = new ArrayList<>();
		for (Location location : valid) {
			if (!visited.contains(location)) {
				notVis.add(location);
			}
		}
		return notVis;
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc the location to detect.
	 * @return List of positions.
	 */
	public List<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return new ArrayList<>();
		ArrayList<Location> valid = new ArrayList<>();
		// There are four directions that can be move to.
		int[] dirs =
			{Location.NORTH, Location.EAST, Location.SOUTH,
		            Location.WEST};
		for (Location neighborLoc : getLocationsInDirections(dirs)) {
			Actor neighbor = gr.get(neighborLoc);
			// The location should be valid and contains not rock.
			if (gr.isValid(neighborLoc) && !(neighbor instanceof Rock)) {
				valid.add(neighborLoc);
			}
			// If the location contains a red rock, then the bug finds the exists.
			else if (neighbor instanceof Rock && neighbor.getColor().equals(Color.RED)) {
				valid.removeAll(valid);
				next = neighborLoc;
				isEnd = true;
				break;
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Location loc = getLocation();
		return getValid(loc).size() > 0;
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	/**
	 * When there are more than one location to move, get the next location.
	 */
	private Location getNextLoc(List<Location> notVis) {
		if (chooseMode == randomMode) {
			return getNextLocByRandom(notVis);
		}
		else {
			return getNextLocByPredict(notVis);
		}
	}
	
	/**
	 * When there are more than one location to move, get the next location by random.
	 */
	@SuppressWarnings("unused")
	private Location getNextLocByRandom(List<Location> notVis) {
		int size = notVis.size();
		try {
            rand = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e) {
            Logger log = Logger.getLogger("MazeBug");
            log.info("rand error!");
        }
        int r = this.rand.nextInt(size);
        return notVis.get(r);
	}
	
	/**
	 * When there are more than one location to move, get the next location by prediction.
	 */
	private Location getNextLocByPredict(List<Location> notVis) {
		int size = notVis.size();
		Location curLoc = getLocation();
		int[] predict = new int[] {0, 0, 0, 0};
		// The direction in the notVis List
		int[] dir = new int [4];
		// Count the selection times of the directions that can move.
		for(int i = 0; i < size; ++i) {
			int forwardDir = curLoc.getDirectionToward(notVis.get(i));
			predict[forwardDir / 90] = dirPredict[forwardDir / 90];
			dir[forwardDir / 90] = i;
		}
		
		// Create the probability array.
		int total = predict[0];
		for (int i = 1; i < 4; ++i) {
			total += predict[i];
			predict[i] += predict[i - 1];
		}
		
		// Selecting a direction during random selection by the probability.
		try {
            rand = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e) {
            Logger log = Logger.getLogger("MazeBug");
            log.info("rand error!");
        }
        int r = this.rand.nextInt(total);
        
        int index = 0;
        if (r >= 0 && r < predict[0]) {
        	index = 0;
        }
        else {
        	for(int i = 0; i < 3; ++i) {
            	if (r >= predict[i] && r < predict[i + 1]) {
            		index = i + 1;
            	}
            }
        }
        return notVis.get(dir[index]);
	}
	
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public List<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)) {
            	locs.add(neighborLoc);
            }
        }
        return locs;
    }    
}
