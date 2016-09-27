package hardysTaxi;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Provides the static method hardySolutionsLessThan(N) which finds all "taxicab
 * numbers that are less than n. These are represented as TaxicabNumber objects,
 * which include the sum and two different ways of writing that as a sum of two
 * cubes. Used the Google HashSet discussed in the FAQ.  Discussed proof with 
 * Dr. B on the limits for the while loop and how once the max exponent reaches
 * the cuberoot of the nth hardy number, it is ok to stop.
 * 
 * @author Claude Anderson, Ryan Coffman, Jack Richard
 * 
 */
public class Hardy {

	/**
	 * Find the nth Hardy number (start counting with 1, not 0) and the numbers
	 * whose cubes demonstrate that it is a Hardy number.
	 * 
	 * @param n
	 * @return the nth Hardy number
	 */
	public static long nthHardyNumber(int n) {
		// max exponent the loop will go to
		long max = 1;
		
		// using the google Heap friendly HashSet to store all the possible
		// Hardy Numbers
		HashSet<Long> possibles = new HashSet<Long>();
		
		// Using a tree set to store all found Hardy numbers
		TreeSet<Long> hardyNumbers = new TreeSet<Long>();
		
		// a value to store the sum of the two cubes
		long cube;
		
		// has the initial limit as an arbitrarily large number until the limit
		// can be found
		long limit = Long.MAX_VALUE;
		
		// while loop goes past the specified n values to account for ones
		// that arent calculated in order
		while(max <= limit) {
			
			// for loop does all powers from 1 to a specified max that 
			// increases as the loop goes on
			for(long i = 1; i <= max; i++) {
				
				// the sum of the two cubes is calculated
				cube = i*i*i + max*max*max;
				
				// if the sum of cubes has already been listed,
				// it goes into the hardy number list
				// if it is not then it is added as a sum of cubes
				if(possibles.contains(cube)) {
					hardyNumbers.add(cube);
					
					// if it has n hardy numbers, it takes the maximum and finds
					// the cube root of it to find the max limit
					if(hardyNumbers.size() == n){
						limit = (long) Math.ceil(Math.cbrt(hardyNumbers.last()));
					}
				} else {
					possibles.add(cube);
				}
			}
			// max number for the for loop to calculate 
			max++;
		}
		
		//iterates through the tree set to find the nth element
		Iterator<Long> iterator = hardyNumbers.iterator();
		for(int j = 0; j < n - 1; j++){
			iterator.next();
		}
		
		// returns the nth element
		return iterator.next();

	}

}
