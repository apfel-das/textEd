package util;

public class List {
	
	protected Node head;
	protected Node tail;
	protected int length;
	
	public List() 
	{
		head = null;
		tail = null;
		length = 0;
	}
	
	public boolean isEmpty() 
	{
		
		return (head == null);
		
		
	}
	
	// setters,getters
	
	public int getLength() 
	{
		return this.length;
	}
	
	// utility methods
	
	
	/*
	 * Create a new node, then APPEND it at the START of the list.
	 * 
	 * */
	
	public Node push(Item t) 
	{
		length++;
		
		//creating a Node
		Node n = new Node(t, head,null);
		
		if(head != null) 
		{
			//backwards linkage
			head.setPrev(n);
		}
		
		//finding the new tail (Maybe change)
		tail = findTail(head);
		//re-setting head
		head = n;
		
		
		
		return head;
	}
	
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
		length++;
		Node n = new Node(t);
		
		Node curr = head;
		
		if(head == null) 
		{
			head = n;
			head.setPrev(null);
			head.setNext(null);
			return head;
			
		}
		
		
		// iterate until the last node
		while(curr.getNext() != null) 
		{
			curr = curr.getNext();
		}
		
		//append
		curr.setNext(n);
		n.setPrev(curr);
		
		//tail has changed 
		tail = n;
		
		
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
			length--;
			
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
			System.out.print("[" +(ind+1) +"]" );
			curr.print();
			
			curr = curr.getNext();
			ind++;
		}
		if(ind == 0) 
		{
			System.out.println("Empty List..");
		}
		else 
		{
			System.out.println("-----------------------------");
		}
		
	}
	
	public void printReverse() 
	{
		int ind = this.getLength() - 1;
		Node curr = tail;
		
		
		while(curr != null) 
		{
			
			System.out.print("[" +(ind+1) +"]" );
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
	
	public void destruct() 
	{
		
		head = null;
		length = 0;
	}	
	/*
	 * Pops the first element (head) of the list. Returns the poped Item.
	 * */
	
	public Item pop() 
	{
		Node curr = head;
		
		if(curr != null) 
		{
			length--;
			head = head.getNext();
			curr.setNext(null);
			return curr.getValue();
		}
		
		
		return null;
	}
	
	public Node search(Item t) 
	{
		Node curr = head;
		while(curr != null) 
		{
			if(t.equals(curr.getValue())) 
			{
				return curr;
			}
			curr = curr.getNext();
		}
		
		return null;
		
	}
	

}
