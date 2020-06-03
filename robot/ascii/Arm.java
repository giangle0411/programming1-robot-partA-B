//Truong Giang Le - s3624420
package robot.ascii;

import com.googlecode.lanterna.terminal.Terminal;

public class Arm implements Drawable
{
	int armHeight;
	int armWidth;
	int armDepth;
	int armRowPos;

	

public Arm(int armHeight,int armWidth,int armDepth)  //create a constructor for the arm
{
	this.armDepth = armDepth;
	this.armHeight = armHeight;
	this.armWidth = armWidth;
}
public void getArmDepth()
{
	this.armDepth = armDepth;
}

public void downArm()	//move armHeight down
{
	this.armHeight=this.armHeight-1;
}
public void upArm()		//move armHeight up
{
	armHeight=armHeight+1;
}
public void leftArm()	//contract the arm
{
	armWidth=armWidth-1;
}
public void rightArm()	//extend the arm
{
	armWidth=armWidth+1;
}
public void raiseArm()	//raise the arm depth
{
	armDepth=armDepth-1;
}
public void lowerArm()	//lower the arm detph
{
	armDepth=armDepth+1;
}
public int getArmRow()	//get arm position vertically
{
	return armRowPos;
}
public int getArmWidth()	//get arm position horizontally
{
	return armWidth;
}

	@Override
	public void draw(Terminal terminal)
	{
		int maxRow = terminal.getTerminalSize().getRows()-1;
		int maxCol = terminal.getTerminalSize().getColumns()-1;
		armRowPos=maxRow-armHeight+armDepth+2;	
		terminal.applyForegroundColor(Terminal.Color.WHITE);
		
		for (int rowPos = maxRow; rowPos > maxRow-armHeight+1; rowPos--) //draw arm height
		{
			terminal.moveCursor(0, rowPos);				//move cursor to the position
			terminal.putCharacter('|');					//draw character
		}
		for (int armPos = 0; armPos < armWidth; armPos++)  //draw arm width
		{
			terminal.moveCursor(armPos,maxRow-armHeight+1);//move cursor to the position
			terminal.putCharacter('-');					 //draw character

		}
		for (int rowArmPos = maxRow-armHeight+1; rowArmPos < maxRow-armHeight+armDepth+2; rowArmPos++)  //draw arm depth
		{
			terminal.moveCursor(armWidth, rowArmPos);	 //move cursor to the position
			terminal.putCharacter('|');					 //draw character
		}
		
	}
}
