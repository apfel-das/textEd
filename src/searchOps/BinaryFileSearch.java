package searchOps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Files.FilePageAccess;
import util.Word;

public class BinaryFileSearch implements FileSearch{
	
	private int diskAccess;
	private int totalAccess;
	private int filePages;
	private FilePageAccess f;
	ArrayList<Word> found;
	
	
	//class constructor.
	public BinaryFileSearch(FilePageAccess f)
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
	 * Searches the whole RandomAccessFile for a given key, page-wise performing a Binary Search. 
	 * @return Alters the {@code boolean isFound in class}
	 * 
	 */
	
	
	/*
	 * An iterative binary search should expose any keys matching the given one.
	 * 
	 * */
	
	@Override
	public void search(String key)
	{
		
	
		//structures to assist.
		ArrayList<Word> tokens;
		found = new ArrayList<Word>();
		
		//a flag to mark finding event.
		boolean isFound = false;
		
		//define right and left parts.
		int left = 0;
		int right = filePages;
		int mid = left +(right-left)/2;
		
		//parse pages, distinguish the middle to be accesed.
		while(left <= right && isFound == false) 
		{
		
			
			/* Remove if debugging
				
				System.out.println("Loading Page: "+mid);
			
			*/
			
			//load the middle page
			
			tokens = loadPage(mid+1);
			
			//parse it to seek for the key
			for(int i =0; i < tokens.size(); i++) 
			{
				
				
				//check for equality, then append.
				if(tokens.get(i).getContext().compareTo(key) == 0) 
				{
					found.add(tokens.get(i));
					
					//last element of any page might have another occurrence on next page.
					if((i == tokens.size() - 1) || i == 0) 
					{
						isFound = false;
						
					}
					else
						isFound = true;
				}
			}
			
			//adjust the new middle position.
			if(!isFound) 
			{
				//last page.
				if(tokens.size() == 0)
					return;
				
				String first = tokens.get(0).getContext();
				String last = tokens.get(tokens.size()-1).getContext();
				
				
				if(!(key.compareTo(first) > 0)) 
				{
					right = mid - 1;
					/*
					 * Debug Lines
					 * */
					//System.out.println("<-");
				}
				else if(!(key.compareTo(last) < 0)) 
				{
				
					left = mid + 1;
					/*
					 * Debug Lines.
					 * 
					 * */
					//System.out.println("->");
				}
				else 
				{
					//key is not on the given file. yet we mark the finding event to finish searching.
					isFound = true;
				}
				
			}
			mid = left +(right-left)/2;
			
		}
		
		
				
	}
	
	/**
	 * Loads a page specified by {@code int n}
	 * 
	 */

	@Override
	public ArrayList<Word> loadPage(int n) 
	{
		
		ArrayList<Word> words = null;
		try {
				words = f.readPage(n);
			} 
		catch (NullPointerException | IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();

		}
		
		diskAccess++;
		totalAccess = getTotalAccess() + diskAccess;
		return words;
		
	}	

	@Override
	public void print() 
	{
		
		if(found.size() > 0) 
		{
		
			Collections.sort(found);
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
