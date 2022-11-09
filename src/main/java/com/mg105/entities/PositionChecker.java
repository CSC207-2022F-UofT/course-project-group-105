package com.mg105.entities;

import java.util.ArrayList;
import java.util.List;

public class PositionChecker {

    public PositionChecker() {
    }

    /**
     * Creates a list of four adjacent coordinates on the map next to the WalkingCharacter.
     * @return the list of adjacent coordinates.
     */
    public static List<RoomPosition> getAdjacents(RoomPosition coordinates) {
        RoomPosition charPosition = coordinates;
        int charX = charPosition.x;
        int charY = charPosition.y;
        ArrayList<RoomPosition> adjacents = new ArrayList<RoomPosition>();
        RoomPosition adjacentOne = new RoomPosition((charX - 1), charY);
        RoomPosition adjacentTwo = new RoomPosition((charX + 1), charY);
        RoomPosition adjacentThree = new RoomPosition(charX, (charY - 1));
        RoomPosition adjacentFour = new RoomPosition(charX, (charY + 1));
        adjacents.add(adjacentOne);
        adjacents.add(adjacentTwo);
        adjacents.add(adjacentThree);
        adjacents.add(adjacentFour);
        return adjacents;
    }
}
