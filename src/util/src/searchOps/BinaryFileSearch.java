package searchOps;

import java.io.IOException;
import java.util.ArrayList;

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
	
	
	

	@Override
	public void searchPage(String k)
	{
		
		
				
	}

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
		totalAccess += diskAccess;
		return words;
		
	}	

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
