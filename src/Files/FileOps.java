  package Files;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import textEditor.*;
import util.Item;
import util.List;
import util.Node;
import util.Word;

/**
 * 
 *
 * {@summary }
 * 	A class to encapsulate methods for performing r/w operations on regular Files
 *  @author dasApfel - Konstantinos Pantelis
 *  
 *  @see  FileInputStream FileOutputStream InputStreamReader OutputStreamReader BufferedReader BufferedWriter
 *  
 * 
 *  
 
 */


public class FileOps {
	
	private File f;
	private List lList;
	private int minSize, maxSize;
	static int threshold;
	final int tokenSize = maxSize + 4;
	
	
	/**
	 * Class constructor
	 * @param fNam Filename as String
	 * @param l    List instance
	 * @param t	   Threshold of a line
	 * @param low  Word lower boundary
	 * @param up   Word upper boundary
	 * @throws FileNotFoundException
	 */
	
	public FileOps(String fNam,List l, int t,int low, int up) throws FileNotFoundException 
	{
		
		f = new File(fNam);
		minSize = low;
		maxSize = up;
		lList = l;
		threshold = t;
	
		
		
		
	}
	
	/**
	 * 
	 * A getter function
	 */
	public File getFile() 
	{
		return this.f;
	}
	
	/**
	 * 
	 * A function to read a file line - wise and store its context if between {@code (minSize, maxSize)} in a double linked list, else ignores the given context.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 *
	 * @implNote Uses the function {@link Files.FileOps.formatInput()} formatInput to decide whether input will be ignored or not.
	 * @see  BufferdReader 
	 * @see InputStreamReader
	 * @see FileInputStream
	 * 	
	 */
	
	public void retrieveContext() throws IOException,FileNotFoundException
	{
		BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line;
		int lCount = 0;
		LineItem l;
		
		
		
		//itterate through the file, line - wise		
		while((line = r.readLine()) != null) 
		{
			
			
			//process, get an Item back
			l = (LineItem) formatInput(line.trim(), lCount);
			
			//store LineItem
			if(l != null)
				storeLine(lList,l);
			
			
			//count to next line.
			lCount++;
			
		}
		//close reader
		
		r.close();
		
		
		
	
		
		
	}
	
	/*
	 * 
	 * Store the context of a (double) linked list given as String, in a .txt file.
	 * 
	 * */
	
	/**
	 * 
	 * 	A function to store the context of a double linked list, in a .txt file.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 *
	 * @implNote Stores the context in the same input file by overwriting its content.
	 * @see  BufferdWritter 
	 * @see OutputStreamWriter 
	 * @see FileOutputStream
	 * 	
	 */
	
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
	 *  -Gets a String representing an ASCII text and a line Number. Returns a LineItem with info given or null if given string is emptyLine. 
	 * 	-Trims given string - input in case that this exceeds threshold characters.
	 * 
	 * */
	/**
	 * Constructs a LineItem if the given key {@literal String} matches the requirements. If not key is either trimmed or null is returned (empty key given) 
	 * <br><strong>Key Requirements: </strong> 
	 * 							
	 * 	<blockquote>If a key is greater than {@code threshold} the key gets trimmed to fit the [0, threshold] range.</blockquote>
	 *	<blockquote>If an empty key is given ({@code lContext.isEmpty() == true}) null is returned.</blockquote>	
	 * 
	 * 
	 *  
	 * 
	 * 
	 * 
	 * 
	 *
	 * 
	 * 
	 * 
	 *  
	 * @param lContext A String key.
	 * @param lNum The line in which this key is found inside the text.
	 * @return A LineItem if the key is valid or null.
	 * @see  util.Item 
	 * @see  textEditorLineItem 
	 * @see	 util.List
	 * 
	 * 
	 */
	
	public static Item formatInput(String lContext, int lNum) 
	{
		
		if(lContext.length() > threshold) 
		{
			
			//trim given input to meet size requirements, ignore characters exceeding limit 
			lContext = lContext.substring(0, threshold);
			
			
			
		}
		
		// exclude the empty or whitespaced line.
		if(lContext.isEmpty())
			return null;
		
		//create a LineItem
		LineItem l = new LineItem(lContext);
		
		
		return l;
		
	}
	
	/*
	 * Based on a list of multiple words, the function splits them and creates a sorted ArrayList of tuples<String word, Integer lineOfText> (see Word.java class).
	 * Returns those on a lexicographically sorted  format. 
	 * Sorting gets Implemented based on the fact that the tuples class (see Word.java) implements the Comparable Interface.
	 * 
	 * */
	
	
	/**
	 *Itterates through the double linked list of Line objects ({@linkplain textEditor.Line}) and stores each valid word in an ArrayList of Word objects ({@linkplain util.Word}). 
	 * <blockquote> The splitting procedure uses a regex in order to be performed. </blockquote>
	 * <blockquote> <strong> Valid Words (Keys): </strong></blockquote>
	 * <blockquote>
	 * 		<ul>
	 * 				<li> Any String that is delimited via the "\W+" regular expression is possibly valid.</li>
	 * 				<li> Any possibly valid string that has a  {@code key.length()} &#8707; {@code [minSize, maxSize]} is valid. </li>
	 * 	 	</ul> 
	 * </blockquote>
	 * @implNote <ul> 
	 * 					<li>Validity of a possibly valid Word - Key is checked via the {@code isValidWord()} function. </li>
	 * 					<li>The final ArrayList gets sorted after it is filled.</li> 
	 * 			</ul>
	 * 
	 * @return An {@literal ArrayList} of Word (- s) objects retrieved from the list in an ascending lexicographical order.
	 */
	public ArrayList<Word> fillWordMap()
	{
		int index = 0;
		Node curr = lList.getHead();
		String delim = "\\W+";
		ArrayList<Word> words = new ArrayList<>();
		
		
		//itterate through each line
		while(curr != null) 
		{
			LineItem lineIt = (LineItem) curr.getValue();
			Line l = (Line) lineIt.getData();
			String[] ws = l.getContext().split(delim);
			
			
			
			for(int i=0; i<ws.length; i++) 
			{
				
				//check the validity of the given word.
				if(isValidWord(ws[i])) 
				{
					
					//create a word object (string, int lNum)
					Word w = new Word(ws[i],index+1);
					
					//append on an arraylist.
					words.add(w);
					
				}
				
				
			}
			
			index++;
			curr = curr.getNext();
			
		}
		
		
		
		//sort the array list based on the "word"-key.
		Collections.sort(words);
	
		///return the sorted arraylist.
		
		
		
		return words;
		
		
		
	}
	

	
	
	
	/*
	 * Stores a created line item in a double linked list.
	 * 
	 * */
	/**
	 * Gets a List instance and a LineItem and appends the given Item at the end of the list.
	 * @param l A list reference.
	 * @param line A line item.
	 * @return Nothing
	 */
	public void storeLine(List l, LineItem line) 
	{
		
		l.append(line);
		
	}
	
	/*
	 * Decides for the validity of the given string based on thresholds. (see FileOps class constructor).
	 * 
	 * */
	/**
	 * Gets a String representing an actual key and decides whether is valid or not by checking its length which should  &#8707; [minSize, maxSize].
	 * @param w
	 * @return True if given is valid, or False
	 * 
	 * 
	 * @see Files.FileOps.fillWordMap
	 */
	
	private boolean isValidWord(String w) 
	{
		if(w.length() > maxSize || w.length() < minSize)
			return false;
		return true;
		
	}


}
