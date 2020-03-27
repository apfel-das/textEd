package searchOps;

import java.io.IOException;
import java.util.ArrayList;

import Files.FilePageAccess;
import util.Word;


/**
 * 
 * @author dasApfel
 * {@summary This is to implement a functionality of searching through pages in a serial way.}
 * @implNote Implements FileSearch
 *
 */
public class SerialFileSearch implements FileSearch 
{
	private int diskAccess;
	private int totalAccess;
	private int filePages;
	private FilePageAccess f;
	ArrayList<Word> found;

	
	public SerialFileSearch(FilePageAccess f) 
	{
		diskAccess = 0;
		totalAccess = 0;
		this.f = f;
		filePages = f.getDataPages();
		
	}
	
	/**
	 * Gets the number of total accesses.
	 * @return totalAccess
	 */
	public int getTotalAccess() {
		return totalAccess;
	}
	
	 
	/**
	 * Gets the number of disk accesses.
	 * @return diskAccess
	 * */
	public int getDiskAccess() 
	{
		return diskAccess;
	}

	/**
	 * Resets the diskAccess counter to zero.
	 * 
	 */
	public void deleteRecords() 
	{
		this.diskAccess = 0;
	}
	

	
	
	/**
	 * 
	 * Searches the whole RandomAccessFile for a given key, page-wise performing a Serial Search.
	 * @return Alters the {@code boolean isFound in class}
	 * 
	 */
	@Override
	public void search(String key) {
		
		
		boolean isFound = false;
		//structures to assist.
		ArrayList<Word> tokens;
		found = new ArrayList<Word>();
		
		
		//a page pointer.
		int currPage = 0;
		
		while(currPage < filePages && isFound == false) 
		{
			//get the content of the page.
			tokens = loadPage(currPage+1);
			
			//parse it to seek for the key
			for(int i =0; i < tokens.size(); i++) 
			{
				
				//check for equality, then append.
				if(tokens.get(i).getContext().compareTo(key) == 0) 
				{
					
					found.add(tokens.get(i));
					
					//last element of any page might have another occurrence on next page.
					if((i == tokens.size() - 1)) 
					{
						isFound = false;
						
						//move to next page, then break.
						this.filePages = currPage+2;
					}
					else
						isFound = true;
				}
			}
			
			currPage++;
		}
		
		
		
		
		
	}
	
	/**
	 * Loads a page specified by {@code int n}. Updates records.
	 * @return An ArrayList containing the contents loaded as Word -s objects
	 * 
	 */


	@Override
	public ArrayList<Word> loadPage(int n)  {
		
		ArrayList<Word> words = null;
		try {
				words = f.readPage(n);
			} 
		catch (NullPointerException | IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();

		}
		
		diskAccess++;
		totalAccess += diskAccess;
		return words;
		
	}


	@Override
	public void print() {
		
		if(found.size() > 0) 
		{
			//print the key.
			System.out.print("\""+found.get(0).getContext()+"\""+" is on lines: ");
			
			//print the lines.
			for(int i = 0; i < found.size(); i++) 
			{
				System.out.print(found.get(i).getLine());
				if((i+1) != found.size())
					System.out.print(", ");
			}
			//print disk accesses.
			
			System.out.format("\nDisk Accesses: %s \n",this.diskAccess);
			
		}
		else
			System.out.println("Key not found");
		
		
		
	}
	

	
	

}
