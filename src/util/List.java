package util;

import test.Line;
import test.LineItem;

public class List {
	
	protected Node head;
	protected Node tail;
	protected int length;
	protected int charCount;
	
	public List() 
	{
		head = null;
		tail = null;
		length = 0;
		charCount = 0;
	}
	
	public boolean isEmpty() 
	{
		
		return (head == null);
		
		
	}
	
	// setters,getters
	
	public Node getHead() 
	{
		return this.head;
	}
	public Node getTail() 
	{
		return this.tail;
	}
	
	public int getLength() 
	{
		return this.length;
	}
	
	public int getCharCount() 
	{
		return this.charCount;
	}
	
	// utility methods
	
	/*
	 * Push (Insert before) a node at a certain position (specified by int pos).
	 * 
	 * 
	 * */
	
	public Node push(Item t, int pos) 
	{
		
		//update info
		length++;
		charCount += ((Line) t.getData()).getContext().length();
		
		Node curr = seek(pos);
		Node prev = curr.getPrev();
		Node n = null;
		
		
		
		//head push
		
		if(curr == head) 
		{
			System.out.println("In here");
			n = new Node(t,head,null);
			
			//fix the linkage
			curr.setPrev(n);
			
			
			head = n;
			
		}
		else 
		{
			
			//inbetween node
			n = new Node(t,curr,prev);
			curr.setPrev(n);
			prev.setNext(n);
			
			
		}
		
		
		
		
		
		
		
		
		return head;
	}
	
	/*Given any node of a list, finds and returns the tail.*/
	
	public Node findTail(Node n) 
	{
		
		if(n == null) 
		{
			return null;
		}
		while(n.getNext() != null)
		{
			n = n.getNext();
			
		}
		
		return n;
		
	}
	
	/*
	 * Create a new node, then append it at the END of the list.
	 * */
	
	public Node append(Item t) 
	{
		//update info
		length++;
		charCount += ((Line) t.getData()).getContext().length();
		
		
		Node n = new Node(t);
		
		Node curr = head;
		
		if(head == null) 
		{
			head = n;
			head.setPrev(null);
			head.setNext(null);
			return head;
			
		}
		
		//if there is a tail.
		if(tail != null)
			curr = tail;
		else
			curr = head;
				
			
		
		//append
		curr.setNext(n);
		n.setPrev(curr);
		
		//tail has changed 
		tail = n;
		
		
		return head;
	}
	
	/*
	 * Adds a node at a specified (by int pos) position.
	 * 
	 * */
	public Node append(Item t, int pos) 
	{
		//markers
		Node curr = head;
		Node prev = head;
		
		//update info
		length++;
		charCount += ((Line) t.getData()).getContext().length();
		
		
		
		
		Node n = new Node(t);
		int currIndex = 0;
		
		// iterate until the given node.
		while(currIndex != pos) 
		{
			
			currIndex++;
			
		}
		
		
		if(seek(currIndex) == null) 
		{
			//assign markers
			curr = tail;
			prev = tail;
			
			//fix linkage, tail is the new node.
			curr.setNext(n);
			n.setPrev(curr);
			tail = n;
			
			
			
		}
		else 
		{
			
			//assign markers for an inbetween element.
			curr = seek(currIndex);
			prev = curr.getPrev();
			
		
			//node linkage
			n.setPrev(prev);
			n.setNext(curr);
			
			//list linkage
			curr.setPrev(n);
			prev.setNext(n);
			
		}
		
			
		
		
		return head;
	}
	
	public Node delete(Item t) 
	{
		// markers
		Node curr = head;
		Node prev = head;
		
		//iterate the list until finding the given Item, mark previous and current Nodes.
		
		while((curr != null) && (!t.equals(curr.getValue()))) 
		{
			prev = curr;
			curr = curr.getNext();
			
		}
		
		//the element has been found
		if(curr != null) 
		{
			//update info
			length--;
			charCount -= curr.getValue().getData().toString().length();
			
			//single node list or head is to be deleted
			if( curr == prev) 
			{
				head = head.getNext();
				head.setPrev(null);
			}
			else 
			{
				
				//right linkage
				prev.setNext(curr.getNext());
				//left linkage
				curr.getNext().setPrev(prev);;
			
			}
			
			//destroy element's linkage
			curr.setNext(null);
			curr.setPrev(null);
			
			
		}
		
		return head;
	}
	
	public void print() 
	{
		int ind = 0;
		Node curr = head;
		
		while(curr != null) 
		{
			
			System.out.print((ind+1)+") ");
			curr.print();
			
			curr = curr.getNext();
			ind++;
		}
		if(ind == 0) 
		{
			System.out.println("Empty File..");
		}
		
		
	}
	
	public void printRaw() 
	{
		int ind = 0;
		Node curr = head;
		
		while(curr != null) 
		{
			
			
			curr.print();
			
			curr = curr.getNext();
			ind++;
		}
		if(ind == 0) 
		{
			System.out.println("Empty File..");
		}
		
		
	}
	/*
	 * Print the list on a reversed order.
	 * */
	
	public void printReverse() 
	{
		int ind = this.getLength() - 1;
		Node curr = tail;
		
		
		while(curr != null) 
		{
			
			System.out.print((ind+1)+") "  );
			curr.print();
			
			curr = curr.getPrev();
			ind--;
			
		}
		if(ind == this.getLength()) 
		{
			System.out.println("Empty List..");
		}
		else 
		{
			System.out.println("-----------------------------");
		}
		
	}
	
	
	/*
	 * Call this and the list will be vanished into thin air.
	 * 
	 * */
	public void destruct() 
	{
		
		head = null;
		tail = null;
		length = 0;
		charCount = 0;
	}	
	
	
	/*
	 * Pops the first element (head) of the list. Returns the poped Item.
	 *
	 **/
	
	public Item pop() 
	{
		Node curr = head;
		
		if(curr != null) 
		{
			//update info
			length--;
			charCount -= curr.getValue().getData().toString().length();
			
			//fix the linkage
			head = head.getNext();
			curr.setNext(null);
			return curr.getValue();
		}
		
		
		return null;
	}
	
	
	
	/*
	 * Finds the element at the given position, if any.
	 * 
	 * */
	
	public Node seek(int pos) 
	{
		int index = 0;
		Node curr = head;
		
		if(pos < this.length) 
		{
			while(curr != null && pos != index) 
			{
				
				index++;
				curr = curr.getNext();
			}
			return curr;
		}
		else 
		{
			return null;
		}
		
		
		
	}
	

	

}
