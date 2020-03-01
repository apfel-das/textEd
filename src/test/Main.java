package test;
import util.FileOps;
import util.List;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
	
	public Main() {
		
	}
	
	public static void main(String[] args) throws IOException,FileNotFoundException {
		
		
		List list = new List();
		Scanner in = new Scanner(System.in);
		
		String ch;
		
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
		//print outcome			
		list.print();
		
		
		do
		{
			
			// use newline as delimiter
			in.useDelimiter("\\n"); 
			//get trimmed input
			ch = in.nextLine().trim();
			
		
			inputCheck(ch);
		
			
		}while(ch.compareTo("q") != 0);
		
		
		
		
		
		
		
		
		
		
		
		

	
		
}
	
	/*
	 * Checks given input for validity and prints a user message.
	 * 
	 * */
	
	private static boolean inputCheck(String inp) 
	{
		
		//using a regex to check input
		String valid = "[atdlnpqwx\\^\\+]";
		Matcher m = Pattern.compile(valid).matcher(inp);
		
		
		if(!m.matches()) 
		{
			System.out.println("Bad command");
			return false;
		}
		else 
		{
			System.out.println("Ok");
		}
		
		return true;
	}


}
