/** @author Brandon Alfaro */

// Change to your net id
package bxa220020;

// Importing classes needed for this project
import java.util.HashMap;
import java.util.TreeMap;

import java.util.TreeSet;
 
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

// If you want to create additional classes, place them in this file as subclasses of MDS
public class MDS {

    // Item class that holds id, cost, and TreeSet that holds description.
    // Also adding "Comparable<Item>"" since we are storing Item in a TreeSet
    public class Item implements Comparable<Item>{
        int id = -1;
        int cost = -1;        
        List<Integer> descriptor = null;

        // Entry Constructor
        Item(int id, int cost, List<Integer> new_list){
            this.id = id;
            this.cost = cost;
            setlist(new_list);
        }

        // Setting new Price
        private void setprice(int new_price){
            this.cost = new_price;
        }

        // Allocating List to hold list
        private void setlist(List<Integer> new_list){
            this.descriptor = null;
            this.descriptor = new ArrayList<>(new_list);
        }

        // printdata() function is a tool to see what variables "Item" holds
        public void printdata(){
            System.out.println("ID:" + this.id + " | COST: " + this.cost + " | DESCRIPTION: " + this.descriptor);

        }
        
        // Since TreeSet needs an integer to compare, we need to 
        // add in "compareTo(Item)" and return an int
        public int compareTo(Item item_check) {
            return ((Integer)this.id).compareTo((Integer)item_check.id);
        }
    }

    // Intialize TreeMap and HashMap to null
    TreeMap<Integer, Item> TrMap = null;
    HashMap<Integer, TreeSet<Item>> HaMap = null;

    // Constructors, allocate TreeMap and HashMap
    public MDS() {
        this.TrMap = new TreeMap<>();
        this.HaMap = new HashMap<>();
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
        int delete_total = 0;        

        // Remove and get Item from TrMap
        Item item = TrMap.remove(id);

            // If Item exist, then we will delete 
            // value for both Hash and Tree Maps
            if(item != null) {

                // Remove every node in HashMap that carries a Descriptor Node
                update_hashmap(item);

                // We deleted the Hash and Tree Map, but we still have the "Item" object
                // Since we have the "Item" object, we get every descriptor and assign it to "delete_success"

                // Loop trought the Tree Set and add every descriptor
                List<Integer> tmp_list = item.descriptor;
                for(int i : tmp_list){
                    delete_total += i;
                }
            }

        // Return total or 0 if fail 
	    return delete_total;
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

        // Loop trought keys
        while (current_index.hasNext()){

            // Assign the keys to "index_key" class
            Item index_key = current_index.next();

            if(find_max_success == 0){
                find_max_success = index_key.cost;
            }

            // Check if cost is higher, if so then replace "find_max_success" 
            // with new cost and assign "stop_loop" to true and stop loop
            if(find_max_success > index_key.cost){
                find_max_success = index_key.cost;
            }    
        }

        // Return Result or 0 if fail
        return find_max_success;
    }

    //////////////////////////////////////////

    // findPriceRange() Finds the number of costs whos descriptors is
    // withing the Item, cost must be beetween low and high
    public int findPriceRange(int n, int low, int high) {

        // Initialize find price success variable
        int find_price_success = 0;

        // Using HashMap, locate the descriptor and all its TreeSets
        for (Item i : HaMap.get(n)){

            // Check if Item.cost is between low and hight and increase by 1
            if(low <= i.cost && i.cost <= high){
                find_price_success++;
            }
        }    

        // Return result
        return find_price_success;
    }

    //////////////////////////////////////////

    // removeNames() Changes removes an Items descriptor
    public int removeNames(int id, List<Integer> list) {
       
        // Initialize remove success variable
        int remove_name_success = 0;

        // If Item exist, continue and call in del_specific_descriptor()
        if(TrMap.containsKey(id) == true){
            remove_name_success = del_specific_descriptor(TrMap.get(id), list);
        }

        return remove_name_success;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



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
        for (int descriptor : adding_item.descriptor ){
            // Getting TreeSet<Item> from HaMap base on descriptor
            TreeSet<Item> tmp = HaMap.get(descriptor);

            // If the TreeSet is not null, add Item to
            // TreeSet<Item> thats stored in HashMap
            if(tmp != null && tmp.contains(adding_item) == false){
                tmp.add(adding_item);
            }
            // If it is null, allocate a new TreeSet and add Item and
            // place it in HaMap with descriptor being the key.
            else if (tmp == null){
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
        for (int descriptor : tmp_item.descriptor){

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

    // ----------------------

    // del_specific_descriptor() Removes the Items Descriptor and updates the HashMap
    private int del_specific_descriptor(Item item, List<Integer> list){

        // Initialize a variable that adds descriptors that were deleted
        int total_remove = 0;
        
        // Looping trough the List
        for(int i : list){

            // Go here if the Item's SetTree contanins a descriptor that is to be deleted
            if (item.descriptor.contains(i) == true){

                // First, check if HashMap contains that Descriptor, if so
                // get it and delete it if HashMap TreeSet size bigger than 1
                if(HaMap.get(i) != null && HaMap.get(i).size() > 1){
                    HaMap.get(i).remove(item);
                }

                // If Size is 1, delete Entry
                else if (HaMap.get(i) != null && HaMap.get(i).size() == 1){
                HaMap.remove(i);
                }

                // Increment "total_remove" by i, i being the descriptor int
                total_remove += i;
            }
        }
        // Return result
        return total_remove;
    }
    // --------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------
}