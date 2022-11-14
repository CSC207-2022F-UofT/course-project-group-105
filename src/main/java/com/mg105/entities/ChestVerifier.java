package com.mg105.entities;

import java.awt.*;
import java.util.List;

public class ChestVerifier {

    public ChestVerifier() {
    }

    /**
     * Checks if a chest is adjacent to the player character.
     *
     * @return the TreasureChest if chest is found. Otherwise, return null.
     */
    public static TreasureChest verifyChest() {

        Point charPosition = GameState.walkingCharacter.charPosition;
        Room room = GameState.getCurrentRoom;
        List<TreasureChest> chests = room.getChests();
        List<Point> adjacents = PositionChecker.getAdjacents(charPosition);

        for (Point adjacentSpot : adjacents) {
            if (ChestLooper.compareLists(adjacentSpot, chests) != null) {
                return ChestLooper.compareLists(adjacentSpot, chests);
            }
        }
        return null;
    }
}
