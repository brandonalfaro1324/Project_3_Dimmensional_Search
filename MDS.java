/** Starter code for P3
 *  @author
 */

// Change to your net id
package bxa220020;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.Iterator;

import java.util.List;


// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {

    // Entry class that holds id, cost, and TreeSet that holds description
    static class Entry{
        int id = -1;
        int cost = -1;
        TreeSet<Integer> TSet = null;  

        // Entry Constructor
        Entry(int id, int cost, List<Integer> new_list){
            this.id = id;
            this.cost = cost;   
            this.TSet = new TreeSet<>();

            // For TreeSet, add every description from List
            for(int element : new_list){
                TSet.add(element);
            }
        }
    }


    //TreeMap<Integer, Entry> TreeMap = null;
    //HashMap<Integer, TreeSet<Integer>> hash_map = null;




    // Add fields of MDS here
    // Constructors
    public MDS() {
       //hash_map = new HashMap<>(null);
    }

    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated. 
       Returns 1 if the item is new, and 0 otherwise.
    */
    public int insert(int id, int price, java.util.List<Integer> list) {

        /*      <TESTING ENTRY>
        Entry new_entry = new Entry(id, price, list);
        Iterator<Integer> testing = new_entry.TSet.iterator();   
        while(testing.hasNext()){
            Integer element = testing.next();
            System.out.println(element);  
        }
         */
        
        return 0;
    }

    // b. Find(id): return price of item with given id (or 0, if not found).
    public int find(int id) {
	    return 0;
    }

    /* 
       c. Delete(id): delete item from storage.  Returns the sum of the
       ints that are in the description of the item deleted,
       or 0, if such an id did not exist.
    */
    public int delete(int id) {
	return 0;
    }

    /* 
       d. FindMinPrice(n): given an integer, find items whose description
       contains that number (exact match with one of the ints in the
       item's description), and return lowest price of those items.
       Return 0 if there is no such item.
    */
    public int findMinPrice(int n) {
	return 0;
    }

    /* 
       e. FindMaxPrice(n): given an integer, find items whose description
       contains that number, and return highest price of those items.
       Return 0 if there is no such item.
    */
    public int findMaxPrice(int n) {
	return 0;
    }

    /* 
       f. FindPriceRange(n,low,high): given int n, find the number
       of items whose description contains n, and in addition,
       their prices fall within the given range, [low, high].
    */
    public int findPriceRange(int n, int low, int high) {
	return 0;
    }

    /*
      g. RemoveNames(id, list): Remove elements of list from the description of id.
      It is possible that some of the items in the list are not in the
      id's description.  Return the sum of the numbers that are actually
      deleted from the description of id.  Return 0 if there is no such id.
    */
    public int removeNames(int id, java.util.List<Integer> list) {
	return 0;
    }
}

