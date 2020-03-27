package util;


/**
 * Represents a Tuple of {@literal <String, Integer>} used for classification of any text-word as a key.
 * @param context  A string representation of the actual word.
 * @param line  An integer representation of the line from which the context was taken.
 * @implNote Implements {@linkplain Comparable } using the {@linkplain context } field. 
 * @see Comparable
 * @author dasApfel
 *
 */
public class Word implements Comparable<Word>
{
	
	
	
	private String context;
	private int line;
	
	
	public Word(String context, int line) 
	{
		
		this.context = context;
		this.line = line;
	}


	public String getContext() {
		return context;
	}


	public void setContext(String context) {
		this.context = context;
	}


	public int getLine() {
		return line;
	}


	public void setLine(int line) {
		this.line = line;
	}



	/*	
	 * Sorting by line if context is the same,else by context <String>, this is to enable sorting by line in BinaryFileSearch.java.
	 *	
	 * It has no effect on sorting by actual context if given strings are different.
	 *
	 */
	
	/**
	 * Comparison method.
	 * @param w : a Word object.
	 * @implNote If given and instantiated Word objects are equal, as by their context, then sorting happens with a line criteria.
	 * <blockquote>
	 * ex:<br> 
	 * 						<br> Word w1 = new Word("shithappens", 1);
	 * 					 	<br> Word w2 = new Word("shithappens",3);
	 * 						<br>
	 * 						<br> w1.compareTo(w2) = -2
	 * 						<br> w2.compareTo(w1) =  2
	 * </blockquote>
	 * 
	 * 
	 * @see Comparable
	 * 
	 */
	@Override
	public int compareTo(Word w) {
		
		int line = this.context.compareTo(w.getContext());
		
		
		return line == 0 ? this.line - w.getLine() : line; 
		
	}
	


	
	
	
	
	

}
