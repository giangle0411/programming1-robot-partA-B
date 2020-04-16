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

	//block3Start is an integer number that used to show the position of the block with the height of 3
	int block3Start = 3;
	
	/*because maximum height of a column is 12, so there will be maximum 4 blocks with the height of 3
	each block of 3 is placed on 1 bar, so bar 1 to 4 is counted, therefore, including 2 columns for 1 and 2
	so there are total  6 columns to be counted, i started from 1 so i created an array with 7 */
	int[] columnHeight = new int[7];
	
	
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
		
		this.barHeights = new int[] { 7, 3, 1, 7, 5, 3, 2 };
		this.blockHeights = new int[]{ 3, 2, 2, 3, 1, 1 };
			
			
						
		// initialise the robot
		robot.init(barHeights, blockHeights, height, width, depth);

		// a simple private method to demonstrate how to control (assignment PART B)
		// note use of constant from Control interface
		
		
		// assignment part B implemented here
	
		columnHeight();
		pickAndDrop();
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
	
	//create a function to move the robot's arm horizontally
	private void moveHorizontal(int width)
	{
		//if the input width > current robot's arm width
		while (this.width < width)
		{
			//move the arm to the input width
			robot.extend();
			this.width++;
		}
		//if the input width < current robot's arm width
		while (this.width > width)
		{
			//move the arm to the input width
			robot.contract();
			this.width--;
		}
	}
	
	//create a function to move the robot's arm vertically
	private void moveVertical(int depth)
	{
		//if the input depth > current robot's arm depth
		while (this.depth < depth)
		{
			//move the arm to the input depth
			robot.lower();
			this.depth++;
		}
		//if the input depth < current robot's arm depth
		while (this.depth > depth)
		{
			//move the arm to the input depth
			robot.raise();
			this.depth--;
		}
	}
	
	
	//create an array that store information of each column's height, including height of bars and blocks
	private void columnHeight()
	{
		for (int i = 0; i < blockHeights.length; i++)
		{
			//if the block height is 1 or 2
			if (blockHeights[i] <= 2)
			{
				//the column of block 1 and block 2 will be counted by the number of each kind of block
				columnHeight[blockHeights[i]] = columnHeight[blockHeights[i]]+blockHeights[i];
			}
			//if the block height is 3
			else
			{
				/*the column of block 3 will be counted by the sum of a bar height and 3 for each column
				 and because bars start from column 3 (=block3Start)*/
				columnHeight[block3Start] = columnHeight[block3Start]+barHeights[block3Start-3]+3;
				//move to next column as each bar can only have 1 block of 3
				block3Start = block3Start+1;
			}
		}
	}
	//create a main pick and drop blocks function
	private void pickAndDrop()
	{
		/*in the columnHeight() function, at the end of the loop, block3Start is added by 1, but now we'll pick the
		 block 3 from right to left, so block3Start is subtracted by 1 so it will point to the most right block 3*/
		block3Start=block3Start-1;
		//create a value to count the height of the destination column
		int desColumnHeight = 0;
		//there are maximum 12 loops if there are 12 blocks of 1
		for (int j=1;j<=12;j++)
		{
			//loop through the value of i, which is the height of blocks
			for (int i=1; i <=3; i++)
			{
				//for the blocks with the height = 1 or 2
				if (i != 3)
				{
					//if the column height is > 0 so there will be blocks there, process pick and drop
					if (columnHeight[i] > 0)
					{
						moveHorizontal(i);
						moveVertical(12-columnHeight[i]);
						robot.pick();
						columnHeight[i]=columnHeight[i]-i;
						moveVertical(0);
						moveHorizontal(10);
						moveVertical(12-desColumnHeight-i);
						robot.drop();
						desColumnHeight = desColumnHeight + i;
						//after pick and drop, robot's arm goes back to the default position
						moveVertical(0);
						moveHorizontal(1);
					}
				}
				//for the blocks with the height = 3
				else
				{
					//only pick block 3 if it's in a block3Start column, process pick and drop
					if (block3Start>=3)
					{
						moveHorizontal(block3Start);
						moveVertical(12-columnHeight[block3Start]);
						robot.pick();
						columnHeight[block3Start]=block3Start-i;
						//move to next column that has block 3 for the next pick and drop
						block3Start = block3Start - 1;
						moveVertical(0);
						moveHorizontal(10);
						moveVertical(12-desColumnHeight - i);
						robot.drop();
						desColumnHeight = desColumnHeight + i;
						//after pick and drop, robot's arm goes back to the default position
						moveVertical(0);
						moveHorizontal(1);
					}
				}
			}
		}
	}
}
