package test;
import util.FileOps;
import util.List;
import util.Node;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
	
	
	
	public static void main(String[] args) throws IOException,FileNotFoundException {
		
		
		List list = new List();
		Scanner in = new Scanner(System.in);
		User u = new User();
		
		
		//arguments check
		if(args.length != 1 || !args[0].contains(".txt")) 
		{
			System.out.println("Usage: java Main.java xxx.txt");
			System.exit(0);
		}
		
		//open given .txt file, read line-wise
		
		FileOps fops = new FileOps(args[0],list,80);
	
		// read and fill the LineList
		fops.readLines();
		
		//get user input, act accordingly after checking for "Bad Commands".
		
		do
		{
			
			// use newline as delimiter
			in.useDelimiter("\\n"); 
			//get trimmed input
			u.setCmd(in.nextLine().trim());
			
		
			
			if(inputCheck(u.getCmd()) != null) 
			{
				execCommand(u,list);
			}
		
			
		}while(u.getCmd().compareTo("q") != 0);
		
		
		
		
		
		
		
		
		
		
		
		

	
		
}
	
	private static void execCommand(User u, List lines) 
	{
		
		
		Node curr;
		switch(u.cmd) 
		{
		
			case "a":
				break;
			case "t":
				break;
			case "d":
				break;	
			case "l":
				//print all lines, nodes of the file
				lines.print();				
				break;
			case "n":
				break;
			case "p":
				//print current line of text, current node of the list.
			
				curr = lines.seek(u.getCurrentLine());
				//case raw format is not selected.
				if(!u.isRaw()) 
				{				
					curr.print();
					break;
				}
				
				// user has selected raw line printing.
				curr.printRaw();
				
				break;
			case "q":
				break;
			case "w":
				break;
			case "x":
				break;
			case "^":
				//u.currentLine is already 0, nothing to do here.
				break;
			case "+":
				//go to next line of text, next node of the list.
				if(u.getCurrentLine() == lines.getLength()-1) 
				{
					System.out.println("There is no further line, command will be ignored.");
					break;
				}
				u.setCurrentLine(u.getCurrentLine()+1);
				break;
			case "=":
				//print current line index, current node index.
				System.out.println("Line: "+(u.currentLine+1));
				break;
			case "#":
				//print lines and characters
				System.out.println(lines.getLength()+" lines, "+lines.getCharCount()+" characters.");
				
				break;
			case "-":
				//go to previous line of text, prev node of the list.
				if(u.getCurrentLine() <= 0) 
				{
					System.out.println("There is no previous line, command will be ignored.");
					break;
				}
				u.setCurrentLine(u.getCurrentLine()-1);
				
				break;
			case "$":
				//go to the last line of text, last node of the list.
				
				u.setCurrentLine(lines.getLength()-1);
				break;
			
		
		}
		
		
	}

	/*
	 * Checks given input for validity and prints a user message.
	 * 
	 * */
	
	private static String inputCheck(String inp) 
	{
		
		
		
		//using a regex to check input and another one to check whether to print message to user or not.
		String valid = "[atdlnpqwx=#\\-\\$\\^\\+]"; 
		String printable = "[lp=#]"; 
		
		Matcher m = Pattern.compile(valid).matcher(inp);
		Matcher mp = Pattern.compile(printable).matcher(inp);
		
		
		if(!m.matches()) 
		{
			System.out.println("Bad command");
			return null;
		}
		else if(!mp.matches())
		{
			
			System.out.println("Ok");
		}
		
	
		return inp;
	}


}
