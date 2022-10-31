package com.mg105.entities;

import java.util.ArrayList;

public class Inventory {
    public final int INVENTORY_LIMIT = 10;
    private final ArrayList<Item> items = new ArrayList<>();

    public int getInventoryLimit(){
        return this.INVENTORY_LIMIT;
    }

    public int getNumberOfItems() {
        return this.items.size();
    }

    public boolean isInventoryFull() {
        return getNumberOfItems() >= this.INVENTORY_LIMIT;
    }

    // Precondition: Inventory is not full
    // If precondition is violated, the function throws a RuntimeException

    public void addItem(Item item) throws RuntimeException {
        assert isInventoryFull() : new RuntimeException("Item cannot be added when Inventory is full");
        this.items.add(item);
    }

    public boolean removeItem(String itemName) {
        for (int i = 0; i < getNumberOfItems(); i++) {
            if (this.items.get(i).getName().equals(itemName)) {
                this.items.remove(i);
                return true;
            }
        }
        return false;
    }

    public int numberOfItems(String itemName) {
        int count = 0;
        for (Item item : this.items) {
            if (item.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    public boolean inInventory(String itemName){
        for (Item item : this.items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
}
