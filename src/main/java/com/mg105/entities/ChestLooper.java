package com.mg105.entities;

import java.awt.*;
import java.util.List;

public class ChestLooper {

    public ChestLooper() {
    }

    /**
     * Checks if a given spot on the map contains a TreasureChest.
     *
     * @return the TreasureChest if chest is found. Otherwise, return null.
     */
    public static TreasureChest compareLists(Point coordinates, List<TreasureChest> chests) {
        for (TreasureChest roomChest : chests) {
            if (coordinates == roomChest.getPosition() && !roomChest.isOpened()) {
                return roomChest;
            }
        }
        return null;
    }
}
