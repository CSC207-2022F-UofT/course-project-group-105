package com.mg105.use_cases;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing a method to retrieve adjacent Points to a given Point.
 */
class AdjacentGetter {

    /**
     * Creates a list of four adjacent coordinates on the map next to the WalkingCharacter.
     *
     * @param coord the current WalkingCharacter position.
     * @return the list of adjacent coordinates.
     */
    public static List<Point> getAdjacents(Point coord) {
        int xCoord = coord.x;
        int yCoord = coord.y;
        ArrayList<Point> adjacents = new ArrayList<>();
        Point adjOne = new Point((xCoord - 1), yCoord);
        Point adjTwo = new Point((xCoord + 1), yCoord);
        Point adjThree = new Point(xCoord, (yCoord - 1));
        Point adjFour = new Point(xCoord, (yCoord + 1));
        adjacents.add(adjOne);
        adjacents.add(adjTwo);
        adjacents.add(adjThree);
        adjacents.add(adjFour);
        return adjacents;
    }
}
