package com.mg105.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PositionChecker {

    public PositionChecker() {
    }

    /**
     * Creates a list of four adjacent coordinates on the map next to the WalkingCharacter.
     * @return the list of adjacent coordinates.
     */
    public static List<Point> getAdjacents(Point coordinates) {
        Point charPosition = coordinates;
        int charX = charPosition.x;
        int charY = charPosition.y;
        ArrayList<Point> adjacents = new ArrayList<Point>();
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
}
