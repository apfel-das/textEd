package util;
import java.io.*;
import test.*;

public class FileOps {
	
	File f;
	List lList;
	static int threshold;
	
	public FileOps(String fNam, List l, int t) throws FileNotFoundException 
	{
		
		f = new File(fNam);
		lList = l;
		threshold = t;
		
		
		
	}
	public void retrieveContext() throws IOException,FileNotFoundException
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
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
		
		
		
	
		
		
	}
	public void storeContext() throws IOException,FileNotFoundException 
	{
		
		//create a writer object showing on the given file. Whatever inside the file f will be deleted.
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		
		Node curr = lList.getHead();
		
		//itterate through the whole text - list.
		while(curr != null) 
		{
					
			//write on file, line wise.
			w.write(curr.getValue().toString());
			w.newLine();
			
			//proceed
			
			curr = curr.getNext();
		}
		
		w.close();
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
		LineItem l = new LineItem(lContext);
		
		
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
