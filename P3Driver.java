/** Driver code for Project 3
 *  @author rbk
 */

// Change to your net id
package bxa220020;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class P3Driver {
    public static void main(String[] args) throws Exception {

		// Creating Scanner Object to collect input from file
        Scanner in;

		// If args > 0, then user inputed a test file in terminal
        if (args.length > 0 && !args[0].equals("-")) {

			// Assign text file into variable "in" 
            File file = new File(args[0]);
            in = new Scanner(file);
		} 
		// If no text file is presented, then user will input text manually
		else {
			// Assign user input into "in"
            in = new Scanner(System.in);
        }

		// 
		boolean VERBOSE = false;
		//
		
		if (args.length > 1) { 
			VERBOSE = Boolean.parseBoolean(args[1]); 
			System.out.println(VERBOSE);
		}

		//
		String operation = "";
		int lineno = 0;
		//

		// Intializing MDS object from MDS class in "MDS.java" file
		MDS mds = new MDS();

		// Intializing Timer object
		Timer timer = new Timer();

		// Intializing int variables
		int id, result, total = 0, price;

		// Creating Link List object
		List<Integer> name = new LinkedList<>();


			// Use label "whileloop" to exit out of while loop
			whileloop:

			// Loop trought the "in" text file until we reached the end or
			// a specific string breaks away from the while loop
			while (in.hasNext()) {
				lineno++;
				result = 0;

				// Get next string in text file "in" object
				operation = in.next();
			
				// If "#" is ecounter, skip line and get next string
				if(operation.charAt(0) == '#') {
					in.nextLine();
					continue;
				}

				// Check string and compute the appropriate statement
				switch (operation) {
				
					// Leave while loop
					case "End":
					break whileloop;

					// Insert data to "mds" object
					case "Insert":
						// Get ID, that being the first num in line
						id = in.nextInt();
						
						// Get price, located next int in same line
						price = in.nextInt();
						name.clear();
						while(true) {
							int val = in.nextInt();
							if(val == 0) { 
								break; 
							}
							else { 
								name.add(val); 
							}
						}

						// NOTE: "name" is a LinkList, very intereseting...
						// Passing in id and price of which are  
						result = mds.insert(id, price, name);
					break;
				
					// Find if Data exist
					case "Find":
						// Get ID to search and send it to "mds" object
						id = in.nextInt();
						result = mds.find(id);
					break;
			
					// Delete Entry node
					case "Delete":
						// Get ID to search and send it to "mds" object
						id = in.nextInt();
						result = mds.delete(id);
					break;
				
					// Find the MinPrice in HasMap
					case "FindMinPrice":
						result = mds.findMinPrice(in.nextInt());
					break;

					// Find the MaxPrice in HashMap
					case "FindMaxPrice":
						result = mds.findMaxPrice(in.nextInt());
					break;
				
					// Find Price Range in HashMap
					case "FindPriceRange":
						result = mds.findPriceRange(in.nextInt(), in.nextInt(), in.nextInt());
					break;

					// Remove Names in HashMap
					case "RemoveNames":
						id = in.nextInt();
						name.clear();
				
						while(true) {
							int val = in.nextInt();
							if(val == 0) { 
								break; 
							}
							else { 
								name.add(val); 
							}
						}
						result = mds.removeNames(id, name);
					break;
					
					// Unknown Operation, print it out and go to the next line
					default:
						System.out.println("Unknown operation: " + operation);
				}
		
				// Print total price of every price gathered
				total += result;
				
				// If true print out price, result, operation, and lineno
				if(VERBOSE) { 
					System.out.println(lineno + "\t" + operation + "\t" + result + "\t" + total); 
				}
			}

		// Print total price and time it took to execute this project. FIN
		System.out.println(total);
		System.out.println(timer.end());
    }


	// Timer class to check time program takes to finish executing
	//----------------------------------------------------------------------------
    public static class Timer {
		long startTime, endTime, elapsedTime, memAvailable, memUsed;

		public Timer() {
			startTime = System.currentTimeMillis();
		}

		public void start() {
			startTime = System.currentTimeMillis();
		}

		public Timer end() {
			endTime = System.currentTimeMillis();
			elapsedTime = endTime-startTime;
			memAvailable = Runtime.getRuntime().totalMemory();
			memUsed = memAvailable - Runtime.getRuntime().freeMemory();
			return this;
		}

		public String toString() {
			return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
		}
    }
	//----------------------------------------------------------------------------
}
