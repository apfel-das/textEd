package util;

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
	
	@Override
	public int compareTo(Word w) {
		
		int line = this.context.compareTo(w.getContext());
		
		
		return line == 0 ? this.line - w.getLine() : line; 
		
	}
	


	
	
	
	
	

}
