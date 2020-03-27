package textEditor;

import java.io.*;



/**
 * 
 * @author dasApfel - Konstantinos Pantelis
 * @version 1.0 
 * {@summary A class to store information concerning user's choices that needs to be remebered.} 
 * 
 *
 */
public class Session 
{
	boolean raw;
	int currentLine;
	File f;
	String cmd;
	
	/**
	 * {@summary Class constructor}
	 * @param r A boolean to hold printing type (raw or with line numbering).
	 * @param curr An Integer to depict the current line of cursor.
	 * @param fp A File to work with.
	 * @param command A String depicting the command that the user has typed in the console (see {@link Main.java} for more).  
	 */
	public Session(boolean r, int curr, File fp, String command) 
	{
		raw = r;
		currentLine = curr;
		f = fp;
		cmd = command;
	
	}
	
	/**
	 * {@summary Empty Class constuctor}.
	 */
	
	public Session() 
	{
		raw = false;
		currentLine = 0;
		f = null;
		cmd = null;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public boolean isRaw() {
		return raw;
	}

	public void setRaw(boolean raw) {
		this.raw = raw;
	}

	public int getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}

	public File getF() {
		return f;
	}

	public void setF(File f) {
		this.f = f;
	}

	/**
	 * @param None
	 *  @return Nothing
	 * {@summary A utility method to alter the print mode.}
	 * 				
	 *
	 * 
	 * Printing can be either raw or not.
	 * This is indicated by checking the value of {@code boolean raw}
	 */
	public void alterPrintMode() 
	{
		if(raw)
			raw = false;
		else
			raw = true;
		
	}

}
