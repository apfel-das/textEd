package util;

public interface Item {
	
	abstract public boolean equals(Item k);

	abstract public boolean less(Item k);

	abstract public Object key();

	abstract public void print();
	
	abstract public Object getData();

}
