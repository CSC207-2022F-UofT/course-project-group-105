package com.mg105.entities;

import java.util.List;

public class ChestVerifier {

    public ChestVerifier() {
    }

    /**
     * Checks if a chest is adjacent to the player character.
     *
     * @return the TreasureChest if chest is found. Otherwise, return null.
     */
    public TreasureChest verifyChest() {

        // there are issues here with static and nonstatic methods and I don't fully understand lmao

        RoomPosition charPosition = GameState.walkingCharacter.charPosition;
        Room room = GameState.walkingCharacter.currentRoom;
        List<TreasureChest> chests = room.getChests();
        List<RoomPosition> adjacents = PositionChecker.getAdjacents(charPosition);

        for (RoomPosition adjacentSpot : adjacents) {
            if (ChestLooper.compareLists(adjacentSpot, chests) != null) {
                return ChestLooper.compareLists(adjacentSpot, chests);
            }
        }
        return null;
    }
}
