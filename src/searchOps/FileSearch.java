package searchOps;

import java.util.ArrayList;



import util.Word;


/**
 * 
 * @author dasApfel - Konstantinos 
 *{@summary An interface to dictate the way that any FileSearch class should be written}
 *
 *
 */
public interface FileSearch {
	
	/**
	 * 
	 * @param key: A string to dictate the key to be searched.
	 * @return a flag to dictate whether key is found or not.
	 */
	abstract public void search(String key);
	
	/**
	 * 
	 * @param n
	 * @return A page of keys in an ArrayList of its type ({@link Word.java})
	 * @
	 */
	
	abstract public ArrayList<Word> loadPage(int n);
	
	
	/**
	 * {@summary Prints a message concerning the result of the search operation}
	 * @return Nothing
	 */
	
	abstract public void print();
	
	
	
	
		
}
