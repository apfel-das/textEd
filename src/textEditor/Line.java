package textEditor;


/**
 * Represents a way to depict and store lines of text in a double linked list.
 * @author dasApfel
 * @version 1.0
 * @implNote This will be wrapped by {@code LineItem} ({@linkplain textEditor.LineItem}) to "fit" inside a {@code List} ({@link util.List}).
 * @see textEditor.LineItem
 *
 */
public class Line{

	private String context;
	
	public Line(String info) 
	{
		this.context=info;
	}


	public String getContext() {
		return context;
	}

	public void setContext(String c) {
		this.context = c;
	}
	
	public String toString(){
		return this.context;
		
	}

	
	public void print()
	{
		System.out.println(this.toString());
	}
	

}
