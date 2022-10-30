package com.mg105.entities;
import java.util.ArrayList;

public class Inventory {
    final static int inventoryLimit = 10;
    private static ArrayList<Item> items = new ArrayList<>();

    public static int getInventoryLimit(){
        return inventoryLimit;
    }

    public static int getNumberOfItems(){
        return items.size();
    }

    public static boolean isInventoryFull(){
        return getNumberOfItems() >= getInventoryLimit();
    }

    /**
     * Precondition: Inventory is not full
     * If precondition is violated, the function throws a RuntimeException
     * */
    public static void addItem(Item item) throws RuntimeException{
        if(isInventoryFull()){
            throw new RuntimeException("Item cannot be added when Inventory is full");
        }
        items.add(item);

    }

    public static boolean removeItemByType(String itemName){
        for(int i = 0; i < getNumberOfItems(); i++){
            if(items.get(i).getItemName().equals(itemName)){
                items.remove(i);
                return true;

            }
        }
        return false;
    }

    public static int numberOfItemsByType(String itemName){
        int count = 0;
        for(Item item: items){
            if(item.getItemName().equals(itemName)){
                count++;

            }
        }
        return count;
    }






}
