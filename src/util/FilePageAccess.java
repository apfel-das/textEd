package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class FilePageAccess 
{
	
	private int pageSize;
	private int tokenSize;
	private int dataPages;
	private int keySize;
	RandomAccessFile dF;
	
	public FilePageAccess(int ps, int k,String f) throws FileNotFoundException 
	{
		pageSize = ps;
		tokenSize = k+4;
		keySize = k;
		dataPages = 0;
		dF = new RandomAccessFile(f,"rw");
		
	}
	
	
	public int getDataPages() 
	{
		return this.dataPages;
	}
	public int getPageSize() 
	{
		return this.pageSize;
	}

	
	/*
	 * Fills a RandomAceesFile .ndx with raw data in given in an ArrayList of tuples<String , Integer> (see Word.java).
	 * Filling gets to happen in quantums of pageSize which can easily be altered.
	 * */

	
	public void fillDictionary(ArrayList<Word> words) throws IOException,FileNotFoundException 
	{
		
		//declare a byte buffer for a page.
		
		int tokens = words.size();
		int pageKeys = pageSize/tokenSize < tokens ? pageSize/tokenSize : tokens;
		
		 
		//stats
		
		int processed = 0;
		int fp = 0;
		
		//proccess the keys
		while(processed < tokens) 
		{
			ByteBuffer bb = ByteBuffer.allocate(pageSize);
			
			System.out.println("Keys Written: "+processed);
			//convert to bytes, page-wise.
					
			for(int i = 0; i < pageKeys; i++)
			{
				
				//catch the case where keys do not fit a whole page.
				if((i+processed ) < words.size()) 
				{
					bb.put(paddBytes(words.get(i+processed).getContext().getBytes(java.nio.charset.StandardCharsets.US_ASCII)));
					bb.putInt(words.get(i+processed).getLine());
				}
				
				
				
				
			}
			
		
			//place the file pointer where it has to be.
			byte [] b = bb.array();
			dF.seek(fp);
			dF.write(b);
			
			
			
			//calculate indexes, file pointer, etc.
			processed +=pageKeys;
			fp += processed*tokenSize;
			
			//clear the stream
			bb.clear();
			
			
			//inform stats.
			dataPages++;
			
			
			
			
			
		}
		
		
		
		dF.close();
		
		
		
		
	}
	


	public void readPage(int pageNum) throws IOException,NullPointerException 
	{
		
		
		//a byte array to store info.
		byte[] buffer = new byte[pageSize];
		
		// conversion array	
		byte byteArray[] = new byte[tokenSize]; 
		
		//set a file pointer.
		int fp = (pageNum-1)*tokenSize;
		
		// seek the position wishing to be read.
		dF.seek(fp);
		dF.read(buffer);
		
		
		for(int i =  0; i< buffer.length; i++) 
		{
			System.out.println(buffer[i]);
		}
		
	
		ByteBuffer bb = ByteBuffer.wrap(buffer);
	
		
		
		
		
		
		
		bb.get(byteArray, 0,4); 
		
		// fills byteArray with 20 bytes from ByteBuffer bb, starting from offset 10
		String someString = new String(byteArray, java.nio.charset.StandardCharsets.US_ASCII);
		int someInt = bb.getInt();
		
		System.out.println("\nPage "+pageNum+" String: "+someString+" Line: "+someInt);
		
		
		
		
		dF.close();
		
		
		
	}

	/*
	 * Fill the missing parts of a string -> byte conversion with spaces (ASCII 32).
	 * 
	 * */
	private byte[] paddBytes(byte s[]) 
	{
		
		byte[] padded = new byte[tokenSize-4];
		
		for(int i = 0; i<padded.length; i++) 
		{
			if(s.length > i) 
			{
				padded[i] = s[i];
			}
			else
				padded[i] = 32;
		}
		
		// Debug lines. To be removed
		for(int i =0; i<padded.length; i++) 
		{
			System.out.print(padded[i]);
		}
		
		System.out.println("\n------------------------");
		
		return padded;
	}

	
	
}
