//Truong Giang Le - s3624420
package robot.ascii;

import robot.Robot;

import javax.swing.JFrame;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import control.RobotControl;

//Robot Assignment for Programming 1 s1 2017
//ASCIIBot classes written by Caspar Ryan
public class ASCIIBot implements Robot
{

	// the Lanterna terminal
	private Terminal terminal;
	
	int startPos = 3;			//start position of block 3
	int[] columnHeight = {0,0};	//position of block 1 and 2
	int maxRow;
	int maxCol;
	boolean armCarry= false;	//when arm is not carrying a block
	int blockCarry;				//the block that being carried
	Arm arm;
	Bar bar;
	Block[] block;
	int[] barHeights;			
	// for simplicity I do not do graceful exiting code so just use the eclipse STOP button to exit
	public static void main(String[] args)
	{
		// instantiate ASCIIBot and run control()
		new RobotControl().control(new ASCIIBot(),null, null);
	}

	// the constructor initialises the Lanterna Terminal
	public ASCIIBot()
	{
		// create the terminal 20 rows, 15 columns
		terminal = TerminalFacade.createSwingTerminal(
				22, 14);
		
		// required by Lanterna framework to initialise
		terminal.enterPrivateMode();
		terminal.setCursorVisible(false);
		terminal.clearScreen();
		int maxRow = terminal.getTerminalSize().getRows();
		int maxCol = terminal.getTerminalSize().getColumns();
		this.maxRow = maxRow;
		// set close operation so program with exit if "X" clicked
		if(terminal instanceof SwingTerminal)
		{
			((SwingTerminal) terminal).getJFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}
	
	@Override
	public void init(int[] barHeights, int[] blockHeights, int height,
			int width, int depth)
	{
		this.barHeights = barHeights;
		//create an arm
		arm = new Arm(height, width, depth);
		//create bars
		for (int i = 0; i < barHeights.length; i++)	//for each bar
		{
			Bar bar = new Bar(barHeights[i], i);	//create bar
		}
		//create blocks
		block = new Block[blockHeights.length];	//for each block
		for (int i = 0; i < blockHeights.length; i++)	//create block
		{
			if(blockHeights[i] < 3) //for block 1 and 2, they have 2 columns for each
			{
				columnHeight[blockHeights[i]-1] = columnHeight[blockHeights[i]-1] + blockHeights[i];
				block[i] = new Block(blockHeights[i], blockHeights[i], maxRow - columnHeight[blockHeights[i]-1]);
				
			}
			else //for block 3, each will be on a bar, start of the first bar is 3
			{
				block[i] = new Block(blockHeights[i], startPos, maxRow - barHeights[startPos-3]-3);
				startPos++;	//move to next bar if there is any more block 3
			}
		}
		drawAll();	//draw arm, blocks, bars
		
		delayAnimation(200);
	}
		// do real code here ..
	public void drawAll()	//create a function to draw arm, blocks, bars
	{
		terminal.clearScreen();
		arm.draw(terminal);			//draw arm
		for (int i = 0; i < barHeights.length; i++)		//for each bar
		{
			Bar bar = new Bar(barHeights[i], i);
			bar.draw(terminal);							//draw bar
		}
		for (int i = 0; i < block.length; i++)			//for each block
		{
			block[i].draw(terminal);					//draw block
		}
		delayAnimation(200);

	}	
	

	@Override
	public void pick()					//create a pick function
	{
		for (int i=0;i<block.length;i++)		//for each block
		{
			if (arm.getArmRow()==block[i].getRowPos())	//if arm depth connect a block vertically
			{
				if (arm.getArmWidth() ==block[i].getColPos()) //if arm depth connect a block horizontally
				{
					armCarry=true;	//the arm carry the block
					blockCarry=i;	//the block with the height of 'i' will be carried
				}
			}
		}
	}

	@Override
	public void drop()		//create a drop function
	{
		armCarry=false;	//the arm doesn't carry the block anymore
	}

	@Override
	public void up()	//move arm height up
	{
	if (armCarry==true)	//if carrying a block
	{
		arm.upArm();	//lift the arm up
		block[blockCarry].liftBlock();	//lift the block up with the arm
	}
	else
	{
		arm.upArm();	//lift the arm up
	}
		drawAll();
		
	}

	@Override
	public void down()
	{
		if (armCarry==true)	//if carrying a block
		{
			arm.downArm();	//lower the arm
			block[blockCarry].lowerBlock();	//lower the block with the arm
		}
		else
		{
			arm.downArm();	//lower the arm
		}
			drawAll();
			
		}

	@Override
	public void contract()
	{
		if (armCarry==true)	//if carrying a block
		{
			arm.leftArm();	//contract the arm
			block[blockCarry].contractBlock();	//contract the block with the arm
		}
		else
		{
			arm.leftArm();	//contract the arm
		}
			drawAll();
			
		}

	@Override
	public void extend()
	{
		if (armCarry==true)	//if carrying a block
		{
			arm.rightArm();		//extend the arm
			block[blockCarry].extendBlock();	//extend the block with the arm
		}
		else
		{
			arm.rightArm();		//extend the arm
		}
			drawAll();
			
		}
	@Override
	public void lower()
	{
		if (armCarry==true)	//if carrying a block
		{
			arm.lowerArm();			//lower the arm depth
			block[blockCarry].lowerBlock();	//lower the block with the arm
		}
		else
		{
			arm.lowerArm();			//lower the arm depth
		}
			drawAll();
			
		}

	@Override
	public void raise()
	{
		if (armCarry==true)	//if carrying a block
		{
			arm.raiseArm();					//lift the arm depth up
			block[blockCarry].liftBlock();	//lift the block up with the arm
		}
		else
		{
			arm.raiseArm();					//lift the arm depth up
		}
			drawAll();
			
		}

	// delay in ms
	private void delayAnimation(int ms)
	{
		try
		{
			Thread.sleep(ms);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
