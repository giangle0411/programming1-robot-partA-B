//Truong Giang Le - s3624420

package control;

import java.util.Scanner;

import robot.Robot;

//Robot Assignment for Programming 1 s1 2017
//Adapted by Caspar from original Robot code in RobotImpl.jar written by Dr Charles Thevathayan
public class RobotControl implements Control
{
	private Robot robot;

	// note use of constants from Control interface
	private int height = Control.INITIAL_HEIGHT; 
	private int width = Control.INITIAL_WIDTH;
	private int depth = Control.INITIAL_DEPTH;

	private int[] barHeights;
	private int[] blockHeights;
	
	// called by RobotImpl
	// the unused arrays are based on cmd line params to RobotImpl but not used in this assignment
	@Override
	public void control(Robot robot, int barHeightsUnused[],
			int blockHeightsUnused[])
	{
		// save robot so we can access it from other methods
		this.robot = robot;

		// ------ASSIGNMENT PART A-------
		// replace this code with a console based menu to create and populate the arrays (in separate method(s))
		
			Scanner scanner = new Scanner(System.in);
			//input number of bars
			int numBar = nextIntValidated("Enter number of bars (min " + MIN_BARS + "/max " + MAX_BARS + "):", MIN_BARS, MAX_BARS , scanner);
			
			//input height of bars
			barHeights = new int[numBar];
			for (int i = 0; i < numBar; i++)
			{
				barHeights[i] = nextIntValidated("Enter height of bars "  + (i+1)  + " of " + numBar + "(min " + MIN_BAR_HEIGHT + " /max " + MAX_BAR_HEIGHT + "):", MIN_BAR_HEIGHT, MAX_BAR_HEIGHT , scanner);
			}
			
			//input number of blocks
			int numBlock = nextIntValidated("Enter number of blocks (min 1/max 12):", 1, 12 , scanner);
			
			//input height of blocks
			blockHeights = new int[numBlock];
			for (int i = 0; i < numBlock; i++)
			{
				blockHeights[i] = nextIntValidated("Enter height of blocks " + (i+1) + " of " + numBlock + "(min " + MIN_BLOCK_HEIGHT + " /max " + MAX_BLOCK_HEIGHT + "):", MIN_BLOCK_HEIGHT, MAX_BLOCK_HEIGHT , scanner);
			}
			
			
						
		// initialise the robot
		robot.init(barHeights, blockHeights, height, width, depth);

		// a simple private method to demonstrate how to control (assignment PART B)
		// note use of constant from Control interface
		moveToWidth(Control.MAX_WIDTH);

		// assignment part B implemented here
	}

	private void moveToWidth(int width)
	{
		while (this.width < width)
		{
			robot.extend();
			this.width++;
		}
	}

	// assignment part A methods implemented here
	public static int nextIntValidated(String prompt, int minValue, int maxValue, Scanner scanner)
	{
		
		int num=0;
		boolean doAgain=true;
		do
		{
			System.out.print(prompt);
			if(scanner.hasNextInt())
			{
				num = scanner.nextInt();
				if((num <= maxValue) & (num >= minValue))
				{
				doAgain=false;
				}
			}
			else
				scanner.nextLine();
		}
		while(doAgain==true);
		
		return num;
		//this method is used above to check if the input is valid
	}
	// assignment part B methods implemented here
}
