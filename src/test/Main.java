package test;
import util.FileOps;
import util.List;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) throws IOException,FileNotFoundException {
		
		Scanner in = new Scanner(System.in);
		FileOps fops = new FileOps("input.txt",10);
		String details;
		int ch;
		int ctr = 0;
	
		
		
		
		fops.readLines();
		
		
		
		
		
		List list = new List();
		
		list.push(new LineItem(ctr,"Databases"));
		ctr++;
		list.push(new LineItem(ctr,"Introduction to Computer Programming"));
		ctr++;
		list.append(new LineItem(ctr,"SW Engineering & Object Oriented Programming"));
		ctr++;
		list.append(new LineItem(ctr,"Data Structures"));
		ctr++;
		
		list.push(new LineItem(ctr,"Structured Programming"));
		
		
		
		
		
		
		
	
	/*	
		do{
			ch = menuPrint(in);
		
			
			switch(ch) 
			{
				
				case 1:
					list.print();
					list.printReverse();
					break;
				case 2:
					System.out.println("Insert Course Code and Course Name:");
				
					details = in.nextLine().trim();
					list.push(new LineItem(Integer.parseInt(details.split(" ")[0]),details.split(" ")[1]));
					break;
					
				case 3:
					System.out.println("Insert Course Code and Course Name:");
					
					details = in.nextLine().trim();
					list.push(new LineItem(Integer.parseInt(details.split(" ")[0]),details.split(" ")[1]));
					break;
				case 4:
					System.out.println("Insert Course Code and Course Name:");
					
					details = in.nextLine().trim();
					LineItem t = new LineItem(Integer.parseInt(details.split(" ")[0]),"");
					list.delete(t);
					break;
					
				default:
					break;
					
				
				
			
			
			
			}
		}while(ch != 5);
		
		*/
		
		
	
	
		
}
	
	private static int menuPrint(Scanner inp) 
	{
		int ch;
		do {
			
			System.out.println("Select a choice (1-5):");
			System.out.println("1)Print the list");
			System.out.println("2)Push an item in the list");
			System.out.println("3)Append an item in the list");
			System.out.println("4)Delete an item");
			System.out.println("5)Quit");
			try 
			{
				ch = inp.nextInt();
				
				//consume \n leftovers
				inp.nextLine();
				
			}
			catch (Exception e)
			{
				
				ch = -1;
				break;
			}
						
		
		}while(!isValid(ch));
		
		return ch;
		
	}
	
	private static boolean isValid(int num) 
	{
		if((num < 1) || (num  > 5))
			return false;
		return true;
		
	}

}
