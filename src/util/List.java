package util;

import textEditor.Line;

/**
 * Represents a dynamic approach on the implementation of double linked list.
 * 
 * @author dasApfel
 * @version 1.2
 * @see util.Node
 * 
 */
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
		charCount += countChars(((Line) t.getData()).getContext());
		
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
	
	
	/*
	 * Given any node of a list, finds and returns the tail.
	 * 
	 * */
	
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
		charCount += countChars(((Line) t.getData()).getContext());
		
		
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
	public void append(Item t, int pos) 
	{
		//markers
		Node curr = head;
		Node prev = head;
		
		//update info
		length++;
		charCount += countChars(((Line) t.getData()).getContext());
		
		
		
		
		Node n = new Node(t);
		
		if(seek(pos) == null) 
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
			curr = seek(pos);
			prev = curr.getPrev();
			
		
			//node linkage
			n.setPrev(prev);
			n.setNext(curr);
			
			//list linkage
			curr.setPrev(n);
			prev.setNext(n);
			
		}
		
	}
	
	public void delete(int pos) 
	{
		Node curr = seek(pos);
		Node prev = curr.getPrev();
		
		
		
		
		//the element has been found
		if(curr != null) 
		{
			//update info
			length--;
			charCount -= countChars(curr.getValue().getData().toString());
			
			//single node list or head is to be deleted
			if( curr == head) 
			{
				head = head.getNext();
				
			}
			else 
			{
				
				//right linkage
				prev.setNext(curr.getNext());
				
				//left linkage
				if(curr != tail)
					curr.getNext().setPrev(prev);;
				
					
			}
			
			//destroy element's linkage
			curr.setNext(null);
			curr.setPrev(null);
			
			
		}
		
		
	}
	
	public void print(boolean spec) 
	{
		int ind = 0;
		Node curr = head;
		
		while(curr != null) 
		{
			if(!spec) 
			{
				System.out.print((ind+1)+") ");
				curr.print();
			}
			else
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
	
	/*
	 * Count the characters a given String contains using a regex.
	 * */
	
	private int countChars(String t) 
	{
		int cc = 0;
		String delim = "\\W+";
		
		String [] words = t.split(delim);
		
		for(int i = 0; i<words.length; i++) 
		{
			cc += words[i].length();
		}
		
		return cc;
	}

}
