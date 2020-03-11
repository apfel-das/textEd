package test;
import util.Item;

/*
 * Wrapper Class to wrap around Line..
 * */

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