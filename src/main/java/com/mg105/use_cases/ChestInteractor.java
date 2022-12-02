package com.mg105.use_cases;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.mg105.entities.GameState;
import com.mg105.entities.Item;
import com.mg105.entities.Room;
import com.mg105.entities.TreasureChest;
import com.mg105.use_cases.Inventory.InventoryInteractor;

public class ChestInteractor {

    private final GameState gameState;
    private final InventoryInteractor interactor;

    /**
     * Creates a ChestInteractor to interact with chests in a GameState.
     *
     * @param gameState the game state.
     * @param interactor the inventory interactor.
     */
    public ChestInteractor(GameState gameState, InventoryInteractor interactor) {
        this.gameState = gameState;
        this.interactor = interactor;
    }

    /**
     * Creates a list of four adjacent coordinates on the map next to the WalkingCharacter.
     * @return the list of adjacent coordinates.
     */
    public static List<Point> getAdjacents(Point coordinates) {
        int charX = coordinates.x;
        int charY = coordinates.y;
        ArrayList<Point> adjacents = new ArrayList<>();
        Point adjacentOne = new Point((charX - 1), charY);
        Point adjacentTwo = new Point((charX + 1), charY);
        Point adjacentThree = new Point(charX, (charY - 1));
        Point adjacentFour = new Point(charX, (charY + 1));
        adjacents.add(adjacentOne);
        adjacents.add(adjacentTwo);
        adjacents.add(adjacentThree);
        adjacents.add(adjacentFour);
        return adjacents;
    }

    /**
     * Checks if a given spot on the map contains a TreasureChest.
     *
     * @return the TreasureChest if chest is found. Otherwise, return null.
     */
    public static TreasureChest compareLists(Point coordinates, List<TreasureChest> chests) {
        for (TreasureChest roomChest : chests) {
            if (coordinates.equals(roomChest.getPosition()) && !roomChest.isOpened()) {
                return roomChest;
            }
        }
        return null;
    }

    /**
     * Checks if a chest is adjacent to the player character.
     *
     * @return the TreasureChest if chest is found. Otherwise, return null.
     */

    public TreasureChest verifyChest() {

        Point charPosition = gameState.getWalkingCharacter().getCharPosition();
        Room room = gameState.getCurrentRoom();
        java.util.List<TreasureChest> chests = room.getChests();
        List<Point> adjacents = getAdjacents(charPosition);

        for (Point adjacentSpot : adjacents) {
            if (compareLists(adjacentSpot, chests) != null) {
                return compareLists(adjacentSpot, chests);
            }
        }
        return null;
    }

    /**
     * Adds the item in an adjacent chest (if one exists) to the inventory,
     * then shows the user text describing what happened.
     */

    public void getChestItem() {

        TreasureChest chest = verifyChest();
        if (chest != null) {
            if (!chest.isOpened()) {
                Item reward = chest.open();
                interactor.addItem(reward.getName());
            }
        }
    }
}
