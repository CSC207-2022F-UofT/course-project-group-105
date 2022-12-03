package com.mg105.entities;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DoorwayTest {
    @Test
    void getPosition() {
        Point position = new Point(1, 1);
        Room nextRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Doorway door = new Doorway(position, nextRoom);

        assertEquals(position, door.getPosition());
    }

    @Test
    void getNextRoom() {
        Point position = new Point(1, 1);
        Room nextRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Doorway door = new Doorway(position, nextRoom);

        assertEquals(nextRoom, door.getNextRoom());
    }
}
