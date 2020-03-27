package textEditor;
import util.Item;

/*
 * Wrapper Class to wrap around Line..
 * */

/**
 * Represents a sort of a wrapper class containing an Item of a Line ({@linkplain textEditor.Line}). This form of encapsulation allows the Line object to be inserted into the List {@linkplain util.List}.
 * 
 * @author dasApfel
 * @see util.Item
 * @see textEditor.Line
 * @see util.List
 * @implSpec Any object of a class that implements Item ({@linkplain util.Item}) can fit in the List ({@linkplain util.List}). 
 *
 */

public class LineItem implements Item {

	private Line line;
	
	
	public LineItem(Line l) {
		this.line=l;
	}
	
	public LineItem(String context){
		this.line=new Line(context);
	}
	
	/*
	 * Overwritten - Implemented methods
	 *
	 */

	@Override
	public boolean less(Item item) {
		
		
		return line.getContext().compareTo(item.key().toString()) < 0;
	}

	@Override
	public boolean equals(Item item) {		
		return (this.line.getContext().compareTo(item.key().toString()) == 0);
		
	}

	@Override
	public Object key() {
		return this.line.getContext();
	}
	
	// a utility print function
	public void print(){
		this.line.print();
	}
	public String toString() 
	{
		return this.line.toString();
	}

	@Override
	public Object getData() {		
		return this.line;
	}
}