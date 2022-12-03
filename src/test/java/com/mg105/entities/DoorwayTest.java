package com.mg105.entities;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DoorwayTest {
    @Test
    void getPosition() {
        Point position = new Point(1, 1);
        Doorway door = new Doorway(position, new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        assertEquals(position, door.getPosition());
    }

    @Test
    void getNextRoom() {
        Room nextRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Doorway door = new Doorway(new Point(), nextRoom);
        assertEquals(nextRoom, door.getNextRoom());
    }
}
