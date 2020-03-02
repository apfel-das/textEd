package util;
import java.io.*;
import test.*;

public class FileOps {
	
	File f;
	BufferedReader r;
	List lList;
	static int threshold;
	
	public FileOps(String fNam, List l, int t) throws FileNotFoundException 
	{
		
		f = new File(fNam);
		r = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		lList = l;
		threshold = t;
		
		
		
	}
	public Item readLines() throws IOException 
	{
		String line = r.readLine();
		int lCount = 0;
		LineItem l;
		
		
		
		//itterate through the file, line - wise		
		while(line != null) 
		{
			
			
			//process, get an Item back
			l = (LineItem) formatInput(line.trim(), lCount);
			
			//store LineItem
			
			storeLine(lList,l);
			
			
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
	 * 	-Trims given string - input in case that this exceeds threshold characters.
	 * 
	 * */
	
	public static Item formatInput(String lContext, int lNum) 
	{
		
		if(lContext.length() > threshold) 
		{
			
			//trim given input to meet size requirements, ignore characters exceeding limit 
			lContext = lContext.substring(0, threshold);
			
			
			
		}
		
		//create a LineItem
		LineItem l = new LineItem(lNum,lContext);
		
		
		return l;
		
	}
	
	/*
	 * Stores a created line item in a double linked list.
	 * */
	
	public void storeLine(List l, LineItem line) 
	{
		
		l.append(line);
		
	}
	
	
	
	

}
