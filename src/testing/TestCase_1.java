package testing;
import java.util.ArrayList;

import Files.FilePageAccess;
import searchOps.*;

/**
 * A class to instantiate test scenarios.
 * 
 * <br><br>  <strong>Common Test Scenario:</strong>
 * <ul>
 * 		<li>Serial Search on the RandomAccessFile for {@code num} keys, non existent.</li>	
 * 		<li>Binary Search on the RandomAccessFile for {@code num} keys, non existent.</li>
 * </ul>
 * Mean disk accesses for each technique and other metrics will be printed.
 * @author dasApfel
 *
 */
public class TestCase_1 {
	
	int n, min, max;
	ArrayList<String>	keys;
	SerialFileSearch 	sfs;
	BinaryFileSearch 	bfs;
	
	
	
	
	//Class constructor.
	
	/**
	 * 
	 * @param min	Minimum character count for the random generated keys
	 * @param max	Maximum character count for the random generated keys
	 * @param n		The number of random keys to be produced
	 * @param fpa	An instance of a FilePageAccess class with an open random file to initialize search operations
	 * 
	 */
	public TestCase_1(int n, int min, int max, FilePageAccess fpa ) {
		
		this.keys = randomKeyFill(n,min, max);
		this.min = min;
		this.max = max;
		this.n = n;
		this.sfs = new SerialFileSearch(fpa);
		this.bfs = new BinaryFileSearch(fpa);
	}
	
	
	
	public void init_testing() 
	{
		
		//random testing.
		for(int i = 0; i< keys.size(); i++) 
		{
			System.out.println(keys.get(i));
		}
		searchRandom(keys, sfs,bfs);
		
		
		
	}
	
	//methods.
	
	private void searchRandom(ArrayList<String> keys , SerialFileSearch sfs, BinaryFileSearch bfs) 
	{
		
		ArrayList<Integer> sAcc = new ArrayList<Integer>();
		ArrayList<Integer> bAcc = new ArrayList<Integer>();
		
		int totS = 0;
		float serialSearchdA = 0;
		int totB = 0;
		float binarySearchdA = 0;
		
		
		//serial search
		for(int i =0; i<keys.size(); i++) 
		{
			//search for key
			sfs.search(keys.get(i));
			
			//saving accesses and printing.
			sAcc.add(i, sfs.getDiskAccess());
			
			totS += sAcc.get(i);
			
			//calibrate
			sfs.deleteRecords();
			
			
			
		}
		System.out.println("Serial File Search for random keys: OK.");
		
		serialSearchdA = totS/keys.size();
		
		
		
		
		//serial search
		for(int i = 0; i<keys.size(); i++) 
		{
			//search for key
			bfs.search(keys.get(i));
			
			//saving accesses and printing.
			bAcc.add(i, bfs.getDiskAccess());
			
			totB += bAcc.get(i);
			
			
			//calibrate
			bfs.deleteRecords();
			
			
			
		}
		

		System.out.println("Binary File Search for random keys: OK.");
		binarySearchdA = totB/keys.size();
		
		
		System.out.format("\n\n%s\n","Disk Accesses Mean Value (S - Serial, B - Binary):");
		System.out.println("S: "+serialSearchdA+" B: "+binarySearchdA);
		
		
		
		
	}
	
	
	
	/**
	 * Fills an ArrayList with n - randomly generated keys in the range [min, max]
	 * 
	 * @param n 	Dictates the number of keys to be generated
	 * @param min	The minimum size of a key
	 * @param max	The maximum size of a key
	 * @return An ArrayList with the keys generated.
	 * @see randStringGen
	 */
	private ArrayList<String> randomKeyFill( int n, int min, int max)
	{
		ArrayList<String> keys = new ArrayList<String>();
		
		//fill the given ArrayList with n random keys.
		
		for(int i =0; i<n; i++) 
		{
			keys.add(randStringGen(min, max));
		}
		
		
		/*
		 * A print module.
		 */
		System.out.println(n+" Random Keys Generated: OK.");
		
		
		
		return keys;
		
		
	}
	
	
	
	
	
	
	
	/**
	 * A random String generator of fixed length specified by {@code max, min}.
	 * 
	 * @param min The minimum size of the String to be produced.
	 * @param max The maximum size of the String to be produced.
	 * @return The randomly generated String.
	 */
	private String randStringGen(int min, int max)
	
	{
		//random size.
	
		int siz = (int)(Math.random()*((max - min) + 1)) + min;
		
		
		
		//a pool to get characters from.
		String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"; 
		
		// a buffer to fit siz characters. 
        StringBuilder sb = new StringBuilder(siz); 
		
        for(int i =0; i<siz; i++) 
        {
        	//a random offset in range (0, siz).
        	int pos = (int )( charPool.length()*Math.random());
        	
        	//append
        	sb.append(charPool.charAt(pos));
        }
		return sb.toString();
		
		
	}

}
