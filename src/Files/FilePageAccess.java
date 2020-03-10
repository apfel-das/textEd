package Files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import util.Word;

public class FilePageAccess 
{
	
	private int pageSize;
	private int tokenSize;
	private int dataPages;
	private int keySize;
	RandomAccessFile dF;
	ArrayList<Word> words;
	
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
				
			
			//convert to bytes, page-wise.
					
			for(int i = 0; i < pageKeys; i++)
			{
				//catch the case where keys do not fit a whole page. Could also be done by try/catch statement.
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
			fp += pageSize;
			
			
			
			//clear the stream
			bb.clear();
			
			
			//inform stats.
			dataPages++;
				
			
		}
		
	}
	
	/*
	 * Read a dictionary (RandomAccessFile) page - wise,at once.
	 * Return the information retrieved in an arraylist. 
	 * 
	 * 
	 * */
	
	
	public ArrayList<Word> readDictionary() throws NullPointerException, IOException
	{
		ArrayList<Word> total = new ArrayList<Word>();
		
		//get the content of each datapage.
		for(int i = 0; i<dataPages; i++) 
		{
			ArrayList<Word> partial = readPage(i+1);
			
			//append to an arraylist element-wise.
			for(int j = 0; j < partial.size(); j++) 
			{
					total.add(partial.get(j));
			}
		}
		
		
		return total;
		
	}


	public ArrayList<Word> readPage(int pageNum) throws IOException,NullPointerException 
	{
		
		
		//an arraylist of tuples to store results
		ArrayList<Word> words = new ArrayList<Word>(); 
		
		//stats
		int maxKeys = pageSize/tokenSize;
		int read = 0;
		
		//a byte array to store info.
		byte[] buffer = new byte[pageSize];
		
		// conversion array	
		byte byteArray[] = new byte[keySize]; 
		
		//set a file pointer.
		int fp = (pageNum-1)*pageSize;
		
		
		
		// seek the position wishing to be read.Then read a page on buffer
		dF.seek(fp);
		dF.read(buffer);

		//wrap it.
		ByteBuffer bb = ByteBuffer.wrap(buffer);
		
		
		//last page might have less keys. So we're handling it in a different manner. 
		int offset = 0;
		while(read < maxKeys && bb.get(offset) != 0) 
		{
			
			//get the String, convert to actual string.
			bb.get(byteArray); 
			
			// fills byteArray with keySize bytes from ByteBuffer bb, starting from the current offset.
			String someString = new String(byteArray, java.nio.charset.StandardCharsets.US_ASCII).trim();
			
			//get the Integer
			int someInt = bb.getInt();
			
			
			
			//append a new object tuple.
			words.add(new Word(someString, someInt));
			
			offset += tokenSize;
			read++;
			
		}
			
			
			
	
		return words;
		
		
		
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
		
		
		
		return padded;
	}

	/*
	 * Close the used fp.
	 * 
	 * */
	
	public void close() throws IOException 
	{
		dF.close();
		
	}
	
	public void printFile( ) throws NullPointerException, IOException 
	{
		
		ArrayList<Word> w = this.readDictionary();
		
		for(int i = 0; i < w.size(); i++) 
		{
			System.out.format("%-20s %-4d\n",w.get(i).getContext(),w.get(i).getLine());
		}
		if(w.isEmpty())
			System.out.println("Empty File");
		
		
	}
	
}
