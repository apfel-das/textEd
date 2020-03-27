package util;

/**
 * Represents and dictates the common rule under which any Item able to participate in a Node should be implemented.
 * @author dasApfel
 * @version 1.0
 * @see textEditor.Line
 * @see textEditor.LineItem 
 */

public interface Item {
	
	abstract public boolean equals(Item k);

	abstract public boolean less(Item k);

	abstract public Object key();

	abstract public void print();
	
	abstract public String toString();
	
	abstract public Object getData();

}
