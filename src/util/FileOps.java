package util;
import java.io.*;
import test.*;

public class FileOps {
	
	File f;
	BufferedReader r;
	int maxL;
	
	public FileOps(String fNam, int max) throws FileNotFoundException 
	{
		
		f = new File(fNam);
		r = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		maxL = max;
		
	}
	public Item readLines() throws IOException 
	{
		String line = r.readLine();
		int lCount = 0;
		
		
		//itterate through the file
		while(line != null) 
		{
			
			
			//print context
			System.out.println("Line: "+line);
			
			//process
			formatInput(line.trim(), lCount);
			
			//move to next line
			line = r.readLine();
			lCount++;
			
		}
		//close reader
		
		r.close();
		
		
		
		
		
		
		
		
		return null;
		
		
	}
	
	/*
	 *  -Gets a String representing an ASCII text and a line Number. Returns a LineItem with info given. 
	 * 	-Trims given string - input in case that this exceeds maxL characters.
	 * 
	 * */
	
	public Item formatInput(String lContext, int lNum) 
	{
		
		if(lContext.length() > maxL) 
		{
			
			//trim given input to meet size requirements, ignore characters exceeding limit 
			lContext = lContext.substring(0, maxL);
			
			
			
		}
		LineItem l = new LineItem(lNum,lContext);
		
		
		return l;
		
	}
	
	
	
	

}
