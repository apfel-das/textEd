package searchOps;

import java.util.ArrayList;

import util.Word;

public interface FileSearch {
	
	
	abstract public void searchPage(String k);
	
	abstract public ArrayList<Word> loadPage(int n);
	
	abstract public void print();
	
	
	
	
		
}
