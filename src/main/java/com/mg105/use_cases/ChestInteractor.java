package com.mg105.use_cases;

import com.mg105.entities.GameState;
import com.mg105.entities.Item;
import com.mg105.entities.Room;
import com.mg105.entities.TreasureChest;
import com.mg105.use_cases.inventory.InventoryInteractor;
import com.mg105.use_cases.map.RoomUpdaterInterface;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class ChestInteractor {

    private final @NotNull GameState gameState;
    private final @NotNull InventoryInteractor interactor;
    private final @NotNull RoomUpdaterInterface updater;

    /**
     * Creates a ChestInteractor to interact with chests in a GameState.
     *
     * @param gameState  the game state.
     * @param interactor the inventory interactor.
     * @param updater    the room updater that will be called when the room changes.
     */
    public ChestInteractor(@NotNull GameState gameState,
                           @NotNull InventoryInteractor interactor,
                           @NotNull RoomUpdaterInterface updater) {
        this.gameState = gameState;
        this.interactor = interactor;
        this.updater = updater;
    }

    /**
     * Checks if a given spot on the map contains a TreasureChest.
     *
     * @return the TreasureChest if chest is found. Otherwise, return null.
     */
    public TreasureChest compareLists(Point coordinates, List<TreasureChest> chests) {
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
        List<Point> adjacents = AdjacentGetter.getAdjacents(charPosition);

        for (Point adjacentSpot : adjacents) {
            if (compareLists(adjacentSpot, chests) != null) {
                return compareLists(adjacentSpot, chests);
            }
        }
        return null;
    }

    /**
     * Adds the item in an adjacent chest (if one exists) to the inventory.
     */

    public void getChestItem() {
        TreasureChest chest = verifyChest();
        if (chest != null) {
            if (!chest.isOpened()) {
                Item reward = chest.open();
                interactor.addItem(reward.getName());
                updater.updateRoom();
            }
        }
    }
}
