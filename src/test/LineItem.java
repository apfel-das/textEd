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
	
	public LineItem(int lNum, String context){
		this.line=new Line(lNum, context);
	}
	
	/*
	 * Overwritten - Implemented methods
	 * - Only key should be the Line code.	 
	 * - By this rule we override methods. 
	 * */

	@Override
	public boolean less(Item item) {
		
		return line.getLNum() < (Integer)item.key();
		//return line.getLNum().compareTo(item.key().toString()) < 0;
	}

	@Override
	public boolean equals(Item item) {		
		return (this.line.getLNum() == (Integer)item.key());
		
	}

	@Override
	public Object key() {
		return this.line.getLNum();
	}
	
	// a utility print function
	public void print(){
		this.line.print();
	}
	public void printRaw() 
	{
		this.line.printRaw();
	}

	@Override
	public Object getData() {		
		return this.line;
	}
}