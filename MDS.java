/** Starter code for P3
 *  @author
 */

// Change to your net id
package bxa220020;

// Importing classes needed for this project
import java.util.HashMap;
import java.util.TreeMap;

import java.util.TreeSet;

import java.util.Iterator;
import java.util.List;


// If you want to create additional classes, place them in this file as subclasses of MDS
public class MDS {

    // Item class that holds id, cost, and TreeSet that holds description.
    // Also adding "Comparable<Item>"" since we are storing Item in a TreeSet
    class Item implements Comparable<Item>{
        int id = -1;
        int cost = -1;
        TreeSet<Integer> TSetDescr = null;  

        // Entry Constructor
        Item(int id, int cost, List<Integer> new_list){
            this.id = id;
            this.cost = cost;   
            this.TSetDescr = new TreeSet<>();

            // For TreeSet, add every description from List
            for(int element : new_list){
                this.TSetDescr.add(element);
            }
        }

        // Since TreeSet needs an integer to compare, we need to 
        // add in "compareTo(Item)" and return an int
        public int compareTo(Item item_check) {
            return ((Integer)this.id).compareTo((Integer)item_check.id);
        }


        // Setting new Price
        private void setprice(int new_price){
            this.cost = new_price;
        }

        // Setting new List by setting variable to null then allocating a new one
        private void setlist(List<Integer> new_list){
            this.TSetDescr = null;
            this.TSetDescr = new TreeSet<>();

            // For TreeSet, add every description from List
            for(int element : new_list){
                this.TSetDescr.add(element);
            }
        }

        // printdata() function is a tool to see what variables "Item" holds
        public void printdata(){
            System.out.println("ID:" + this.id + " | COST: " + this.cost + " | DESCRIPTION: " + this.TSetDescr);

        }
    }

    // Intialize TreeMap and HashMap to null
    TreeMap<Integer, Item> TrMap = null;
    HashMap<Integer, TreeSet<Item>> HaMap = null;

    // Constructors, allocate TreeMap and HashMap
    public MDS() {
        TrMap = new TreeMap<>();
        HaMap = new HashMap<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // insert() function can now add in new Objects to TreeMap and HashMap
    public int insert(int id, int price, List<Integer> list) {

        // Intialize a int varibale to see if insert succesfully added new Item Hash and Tree Maps
        int insert_success = 0;

        // Get Item or null from TreeMap        
        Item get_item = TrMap.get(id);

        // If "get_item" is null in hash, allocate new Item and put it in "TMap"
        if(get_item == null){

            // Allocate Item object and pass in varibles
            Item new_item = new Item(id, price, list);

            // For TrMap, we put in id for key and Item object for value
            TrMap.put(id, new_item);
            
            // For HaMap, call add_hashmap() function to insert descriptors in the HashMap            
            add_hashmap(new_item);

            // Set "insert_success" to 1
            insert_success = 1;    
        }

        // Go here if ID exist in the Hash and Tree Map        
        // NOTE: If we are here, then we are either only setting new price or descriptor 
        else{

            // If list is Empty or null, then we only need to change price
            if(list == null || list.isEmpty()){
                get_item.setprice(price);
            }
            
            // If list is not empty, then get rid of old descriptor and set new one
            else{

                // Remove any Item thats assign to its own descriptor in HashMap
                update_hashmap(get_item);

                // Change Price and List
                get_item.setprice(price);
                get_item.setlist(list);

                // With new list in place, add new Descriptors in HashMap
                add_hashmap(get_item);
            }
            
        }
        // Return insert success 1 or 0 if fail
        return insert_success;
    }

    //////////////////////////////////////////

    // delete() function deletes Hash and Tree map and returns total descriptors
    public int delete(int id) {

        // Intialize a int var for the success of delete()
        int delete_success = 0;
        
        // If key exist and value is not null, then we will 
        // delete the Key and Value for both Hash and Tree Maps
        if(TrMap.containsKey(id) == true && TrMap.get(id) != null){

            // Remove and get Item from TrMap
            Item item = TrMap.remove(id);

            // Remove every node that is in every Descriptor in HashMap
            update_hashmap(item);

            // We deleted the Hash and Tree Map, but we still have the "Item" object
            // Since we have the "Item" object, we get every descriptor and assign it to "delete_success"
            Iterator <Integer> test = item.TSetDescr.iterator();

            // Loop trought the Tree Set and add every descriptor
            while(test.hasNext()){
                delete_success += test.next();
            }
        }
        // Return total or 0 if fail 
	    return delete_success;
    }

    //////////////////////////////////////////

    // find(id) function return price of item with given id returns price or 0 if value does not exist
    public int find(int id) {

        // Intialize int var to collect price
        int find_success = 0;
        
        // If value does exist, go here
        if(TrMap.containsKey(id) == true){

            // Get Item value from Tree Map
            Item tmp_item = TrMap.get(id);

            // Get cost and assigned it to "find_success"
            find_success = tmp_item.cost;
        }
        // Return cost or 0 if fail
	    return find_success;
    }

    //////////////////////////////////////////

    // findMaxPrice() function gets the highest price that contains a specific description
    public int findMaxPrice(int n) {

        // Return back cost value if found, if not return 0
        int find_max_success = 0;
        
        // Get value from n as the key in HashMap, loop trought the TreeSet and find the higest price
        TreeSet<Item> tmp_item = HaMap.get(n);

        if(tmp_item != null){
            for (Item item : tmp_item){   

                // If cost is higher, replace find_max_success with cost
                if(find_max_success < item.cost){
                    find_max_success = item.cost;
                }
            }
        }
        // Return Result or 0 if fail
        return find_max_success;
    }

    //////////////////////////////////////////

   
    // findMaxPrice() function gets the lowest price that contains a specific description
    // NOTE: Code here is the same as "findMaxPrice()", only difference is having to stop 
    // the while loop the moment we find the dirst descriptor, since lowest cost is found.
    public int findMinPrice(int n) {
	    
        // Return back cost value if found, if not return 0
        int find_max_success = 0;

        
        // Get the set of keys to loop trought
        Iterator<Item> current_index =  HaMap.get(n).iterator();

        // Intialize a boolean var, since we will stop the while 
        // loop the moment we find the first descriptor
        boolean stop_loop = false;

        // Loop trought keys
        while (stop_loop == false && current_index.hasNext()){

            // Assign the keys to "index_key" class
            Item index_key = current_index.next();

            // Check if cost is higher, if so then replace "find_max_success" 
            // with new cost and assign "stop_loop" to true and stop loop
            if(find_max_success < index_key.cost){
                stop_loop = true;
                find_max_success = index_key.cost;
            }    
        }
        // Return Result or 0 if fail
        return find_max_success;
    }

    //////////////////////////////////////////


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
    public int removeNames(int id, List<Integer> list) {
        int total = 0;
	    return total;
    }


    // HELPER FUNCTIONS
    // --------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------

    // Helper function print_result() will print HashMap and TreeMap help us see Keys and Values
    public void print_result(){
        System.out.println("PRINTING HASHMAP... ");         

        // Get keys for HaMap
        for(int item : HaMap.keySet()){
        
            // Print current descriptor from keySet
            System.out.println("TreeMap Descriptor: " + item);

            // There could be multiple nodes in TreeSet in HashMap value
            // so assign HashMap current value to Iterator
            Iterator<Item> testing = HaMap.get(item).iterator();

            // Print out the Item in current Treeset
            while(testing.hasNext()){
                Item tet = testing.next();
                tet.printdata();
            }
            // Print newline for next TreeSet
            System.out.println();
        }
        // Print end of HashMap
        System.out.println("END OF HASHMAP...\n\n");         
    
        // Now begin TreeMap
        System.out.println("NOW PRINTING TREEMAP...");     

        for(int i : TrMap.keySet()){
            TrMap.get(i).printdata();
        }
        // Print end of HashMap
        System.out.println("\nEND OF TREEMAP...\n");         
    }

     // ----------------------

    // add_hashmap() adds Items to a TreeSet<Item> in HashMap
    // correlating with Descriptor - key, TreeSet<Item> - value
    private void add_hashmap(Item adding_item){

        // Loop trought descriptors from newly added Item
        for(int descriptor : adding_item.TSetDescr){

            // Getting TreeSet<Item> from HaMap base on descriptor
            TreeSet<Item> tmp = HaMap.get(descriptor);

            // If the TreeSet is not null, add Item to
            // TreeSet<Item> thats stored in HashMap
            if(tmp != null){
                tmp.add(adding_item);
            }

            // If it is null, allocate a new TreeSet and add Item and
            // place it in HaMap with descriptor being the key.
            else{
                tmp = new TreeSet<>();
                tmp.add(adding_item);
                HaMap.put(descriptor, tmp);
            }
        }
    }

     // ----------------------

    /* update_hasmap() removes Items base on their own descriptors,
    *  since that Item is either being removed or its list is changed
    *
    *  If removed, than we need to remove every Item in any descriptor
    *  that holds that Item
    *
    *  If list is being updated, we would need to remove Item from
    *  any descriptor since their own descriptor is going to be updated*/

    private void update_hashmap(Item tmp_item){

        // Get descriptor from Item TreeSet<Integer>
        for (int descriptor : tmp_item.TSetDescr){

            // Using desriptor, check if HashMap carries value exist

            // If HashMap is not null and its size if greater than 1, then only
            // remove Item from the TreeSet<Item>
            if(HaMap.get(descriptor) != null && HaMap.get(descriptor).size() > 1){
                HaMap.get(descriptor).remove(tmp_item);
            }

            // If size is == 1, then only remove the HashMap value
            else if (HaMap.get(descriptor) != null && HaMap.get(descriptor).size() == 1){
                HaMap.remove(descriptor);
            }
        }
    }
    // --------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------
}

