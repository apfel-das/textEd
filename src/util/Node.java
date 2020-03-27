package util;


/**
 * Represents an abstract Node on a Double Linked List
 * @author dasApfel
 * @version 1.0
 * @see util.Item
 * 
 */
public class Node {
	
	private Item data;
	private Node next;
	private Node prev;

	
	//Constructors and basic methods
	
	public Node(Item d)
	{
		this.data = d;
		
	}
	public Node(Item d, Node n, Node p) 
	{
		this.data = d;
		this.next = n;
		this.prev = p;
		
	}
	
	public void print() 
	{
		if(data != null)
			data.print();
		else
			System.out.print("No data to display");
		
	}
	
	
	//getters - setters
	public Item getValue() 
	{
		return this.data;
		
	}
	public void setNext(Node n) 
	{
		
		this.next = n;
	}
	public Node getNext() 
	{
		return this.next;
		
	}
	public void setPrev(Node p) 
	{
		this.prev = p;
		
	}
	
	public Node getPrev() 
	{
		return this.prev;
		
	}

}
