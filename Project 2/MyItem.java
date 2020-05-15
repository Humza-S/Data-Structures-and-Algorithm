/*
    Name: Humza Salman
    NET ID: MHS180007
*/

import java.util.ArrayList;
import java.util.List;

public class MyItem implements IDedObject {

    private int itemID;
    private int itemPrice;
    private List<Integer> itemDescription;

    // constructors
    MyItem() {
        itemID = 000;
        itemPrice = 10;
        itemDescription = new ArrayList<Integer>(42);
    }

    MyItem(int itemID, int itemPrice, List<Integer> description) {
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        itemDescription = new ArrayList<Integer>(description);
    }


    // returns the itemID for the item
    public int getID() {
        return itemID;
    }
    
    // returns a String object which contains the item ID, item price, and item description
    public String printID() {
        String item = itemID + " " + itemPrice;

        // appends each number in the item description to the string
        for (Integer i : itemDescription) { 
            item += " " + i;
        }
        System.out.println(item);
        return item;
    }    
}