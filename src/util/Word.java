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



	@Override
	public int compareTo(Word w) {
		return this.context.compareTo(w.getContext()); 
		
	}
	
	
	
	

}
