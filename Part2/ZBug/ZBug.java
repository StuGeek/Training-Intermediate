/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a "Z" route of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int changeDirTimes;

    /**
     * Constructs a box bug that traces a "Z" route of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        changeDirTimes = 0;
        turns(2);
    }
    
    public void turns(int turnTimes) {
    	for (int i = 0; i < turnTimes; ++i) {
    		turn();
    	}
    }

    /**
     * Moves to the next location of the "Z" route.
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
        	if (changeDirTimes == 0) turns(3);
        	else if (changeDirTimes == 1) turns(5);
        	else return;
        	changeDirTimes++;
            steps = 0;
        }
    }
}
