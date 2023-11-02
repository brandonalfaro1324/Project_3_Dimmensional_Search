/** Starter code for P3
 *  @author
 */

// Change to your net id
package bxa220020;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.RowFilter.Entry;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

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


    /* Public methods of MDS. Do not change their signatures.
       __________________________________________________________________
       a. Insert(id,price,list): insert a new item whose description is given
       in the list.  If an entry with the same id already exists, then its
       description and price are replaced by the new values, unless list
       is null or empty, in which case, just the price is updated. 
       Returns 1 if the item is new, and 0 otherwise.
    */
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
        return insert_success;
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

