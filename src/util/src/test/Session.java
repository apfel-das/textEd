package test;

import java.io.*;

public class Session 
{
	boolean raw;
	int currentLine;
	File f;
	String cmd;
	
	public Session(boolean r, int curr, File fp, String command) 
	{
		raw = r;
		currentLine = curr;
		f = fp;
		cmd = command;
	
	}
	
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

	public void alterPrintMode() 
	{
		if(raw)
			raw = false;
		else
			raw = true;
		
	}

}
