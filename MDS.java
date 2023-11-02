/** Starter code for P3
 *  @author
 */

// Change to your net id
package bxa220020;

// Importing classes needed for this project
import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.List;


// If you want to create additional classes, place them in this file as subclasses of MDS

public class MDS {

    // Item class that holds id, cost, and TreeSet that holds description
    static class Item{
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
    HashMap<Item, TreeSet<Integer>> HaMap = null;

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
            // For HaMap, put in Item object for key and BinaryTree from "Item" for value
            HaMap.put(new_item, new_item.TSetDescr);

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

            // If list is not empty, then get rid of old descriptor and setting new one
            else{

                get_item.setprice(price);
                get_item.setlist(list);

                // Assign new Binary Tree to Hash Map
                HaMap.put(get_item, get_item.TSetDescr);
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

            // Remove and get Value from TrMap
            Item testing = TrMap.remove(id);

            // From the value returned from Trmap.remove(), use it to remove from HashMap
            HaMap.remove(testing);

            // We deleted the Hash and Tree Map, but we still have the "Item" object
            // Since we have the "Item" object, we get every descriptor and assign it to "delete_success"
            Iterator <Integer> test = testing.TSetDescr.iterator();

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

        // Get the set of keys to loop trought
        Iterator<Item> current_index =  HaMap.keySet().iterator();

        // Loop trought keys
        while(current_index.hasNext()){

            // Assign the keys to "index_key" class
            Item index_key = current_index.next();

            // Go here if n exist in the TreeMap
            if(HaMap.get(index_key).contains(n) == true){

                // Check if cost is higher, if so then replace "find_max_success" with new cost
                if(find_max_success < index_key.cost){
                    find_max_success = index_key.cost;
                }
            }    
        }
        // Return Result or 0 if fail
        return find_max_success;
    }

    //////////////////////////////////////////

   
    public int findMinPrice(int n) {
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


    // Helper functions below
    // --------------------------------------------------------------------------------------------------------------

    // Helper function print_result() to help us see Keys and Values for Tree and Hash Maps
    public void print_result(){
        for(int item : TrMap.keySet()){
        
            System.out.println("TreeMap ID: " + item);
            TrMap.get(item).printdata();

            System.out.println(HaMap.get(TrMap.get(item)));

            if(HaMap.get(TrMap.get(item)) == TrMap.get(item).TSetDescr){
                System.out.println("BOTH TreeMap.Item.BSTree and HashMap.Value are Equal...\n");
            }
        }
    }


}

