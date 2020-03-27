package Files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import util.Word;

/**
 * 
 * 
 * @author dasApfel - Konstantinos Pantelis
 * {@summary }
 * A class to encapsulate methods for performing r/w operations on RandomAccessFiles representing a dictionary (see {@linkplain RandomAccessFile} for more).
 *
 */

public class FilePageAccess 
{
	
	
	
	private int pageSize;
	private int tokenSize;
	private int dataPages;
	private int keySize;
	RandomAccessFile dF;
	ArrayList<Word> words;
	
	
	/**
	 * 
	 * @param ps Size of a datapage in bytes.
	 * @param k  Tuple of {@literal <String, Integer>} maximum size in bytes.
	 * @param f  The name of a {@linkplain RandomAccessFile} to write the data in bytes, passed as a {@literal String}.
	 * @throws FileNotFoundException
	 */
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

	/**
	 * 
	 * Gets an {@linkplain ArrayList} of {@code Word.class} (tuples of {@literal <String, Integer> }) and fills a RandomAccessFile page - wise.
	 * Uses a {@literal ByteBuffer} {@linkplain java.nio.ByteBuffer} in which {@code pageSize} bytes are allocated.
	 * 
	 * @param words Tuples of {@literal <String, Integer>} as an {@linkplain ArrayList}.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @return Nothing, only informs the {@code int datapages} which depicts the number of pages present.
	 * 
	 */
	
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
	
	/**
	 * 
	 * Reads a dictionary ({@literal RandomAccessFile}) page - wise,at once by utilising {@code readPage(int pageNum)} method.
	 * @return The information retrieved in an {@linkplain ArrayList}. 
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

	/**
	 * Reads a page of the dictionary - {@literal RandomAccessFile} by utilizing a ByteBuffer (see {@linkplain java.nio.ByteBuffer})
	 * @param pageNum Specifies the page to be accessed/read.
	 * @return The page specified as an ArrayList (see {@linkplain java.util.ArrayList} for more).
	 * @throws IOException
	 * @throws NullPointerException
	 */

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
	 * Fills the missing parts of a string -> byte conversion with spaces (ASCII 32).
	 * 
	 * */
	
	/**
	 * Space - padding of a byte array representing a {@literal String} key.
	 * @param s A byte array containing the byte representation of a {@literal String} key.
	 * @return	A byte array containing the byte representation of a {@literal String} key, filled up with spaces (ASCII 32) if {@code key.length < tokenSize } 
	 *  
	 */
	
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
	
	/**
	 * Closes open File.
	 * @throws IOException
	 */
	public void close() throws IOException 
	{
		dF.close();
		
	}
	
	/**
	 * prints a File content after retrieved.
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public void printFile( ) throws NullPointerException, IOException 
	{
		
		ArrayList<Word> w = this.readDictionary();
		
		for(int i = 0; i < w.size(); i++) 
		{
			//draw a line to distinguish pages.
			if(i % (pageSize/tokenSize) == 0 )
				System.out.println("-------------------------------------------------");
			System.out.format("%-20s %-4d\n",w.get(i).getContext(),w.get(i).getLine());
		}
		if(w.isEmpty())
			System.out.println("Empty File");
		
		
	}
	
}
