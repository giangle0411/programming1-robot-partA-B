package control;

import robot.Robot;

//Control interface by Caspar (used internally by Robot)
//do not modify this code!
public interface Control
{
	public static final int MIN_HEIGHT = 2;
	public static final int MIN_WIDTH = 1;
	public static final int MIN_DEPTH = 0;
	
	public static final int MAX_HEIGHT = 13;
	public static final int MAX_WIDTH = 10;
	public static final int MAX_DEPTH = MAX_HEIGHT - 1;
	
	public static final int MIN_BARS = 1;
	public static final int MAX_BARS = 7;
	public static final int MIN_BAR_HEIGHT = 1;
	public static final int MAX_BAR_HEIGHT = 7;
	
	public static final int MIN_BLOCK_HEIGHT = 1;
	public static final int MAX_BLOCK_HEIGHT = 3;
	public static final int MAX_TOTAL_BLOCK_HEIGHT = 12;
	
	public static final int FIRST_BAR_COLUMN = 3;
	public static final int DEST_COLUMN = MAX_WIDTH;

	public static final int INITIAL_HEIGHT = MAX_HEIGHT;
	public static final int INITIAL_WIDTH = 1;
	public static final int INITIAL_DEPTH = 0;
	
	public void control(Robot robot, int barHeights[], int blockHeights[]);
}
