//Truong Giang Le - s3624420
package robot.ascii;

import com.googlecode.lanterna.terminal.Terminal;

public class Bar implements Drawable
{
	int barPos;
	int barHeight;
	public Bar(int barHeight, int barPos)  //create a constructor for Bar
	{
		this.barHeight = barHeight;
		this.barPos = barPos + 3;  //bars start from column 3 as 2 first columns are for the blocks
	}
	
	@Override
	public void draw(Terminal terminal)
	{
		// (0 index for terminal)
		int maxRow = terminal.getTerminalSize().getRows()-1;
		int maxCol = terminal.getTerminalSize().getColumns()-1;
		
		terminal.applyForegroundColor(Terminal.Color.GREEN);
		//draw the bars by looping through the positions and rows
			for (int rowPos = maxRow; rowPos > maxRow - barHeight ; rowPos--)
			{
				terminal.moveCursor(barPos, rowPos); //move cursor to the position
				terminal.putCharacter('*');	//draw the character
			}	
	}
}