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
		
		
		List list = new List();
		
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
		
		
		
		
		
		
		

	
		
}
	


}
