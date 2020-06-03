//Truong Giang Le - s3624420
package robot.ascii;

import com.googlecode.lanterna.terminal.Terminal;

public class Block implements Drawable
{
	int blockHeight;
	int colPos;
	int rowPos;

	
	
	public Block(int blockHeight, int colPos, int rowPos)
	{
		this.blockHeight = blockHeight;
		this.colPos = colPos;
		this.rowPos = rowPos;
	}
	
	public int getRowPos()	//get block position vertically
	{
		return rowPos;
	}
	
	public int getColPos()	//get block position horizontally
	{
		return colPos;
	}
	public void liftBlock()	//lift the block up
	{
		rowPos=rowPos-1;
	}
	public void lowerBlock()//lower the block down
	{
		rowPos=rowPos+1;
	}
	public void extendBlock()//move the block to the right
	{
		colPos=colPos+1;
	}
	public void contractBlock()//move the block to the left
	{
		colPos=colPos-1;
	}
	
	
	@Override
	public void draw(Terminal terminal)
	{
		for(int blockCol = rowPos; blockCol < rowPos+blockHeight; blockCol++)	//for block columns
		{
			if(blockHeight==1)	//draw block 1 with yellow color
			{
				terminal.applyForegroundColor(Terminal.Color.YELLOW);
				terminal.moveCursor(colPos, blockCol);
				terminal.putCharacter('+');
			}
			else if(blockHeight==2)	//draw block 2 with red color
			{
				terminal.applyForegroundColor(Terminal.Color.RED);
				terminal.moveCursor(colPos, blockCol);
				terminal.putCharacter('+');
			}
			else	//for block 3, draw with blue color
			{
				terminal.applyForegroundColor(Terminal.Color.BLUE);
				terminal.moveCursor(colPos,blockCol);
				terminal.putCharacter('+');

			}
		}
	}
}
