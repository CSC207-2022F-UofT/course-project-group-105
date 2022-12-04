package com.mg105.use_cases;

import org.junit.jupiter.api.Test;
import java.util.List;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RoomLayoutTest {
    @Test
    void getChests() {
        List<Point> chests = new ArrayList<>();
        chests.add(new Point(1, 2));
        RoomLayout layout = new RoomLayout(chests, new ArrayList<>(), new ArrayList<>(), new Point());
        assertEquals(chests, layout.getChests());
    }

    @Test
    void getOpponents() {
        List<Point> opponents = new ArrayList<>();
        opponents.add(new Point(1, 2));
        RoomLayout layout = new RoomLayout(new ArrayList<>(), opponents, new ArrayList<>(), new Point());
        assertEquals(opponents, layout.getOpponents());
    }

    @Test
    void getDoorways() {
        List<Point> doorways = new ArrayList<>();
        doorways.add(new Point(1, 2));
        RoomLayout layout = new RoomLayout(new ArrayList<>(), new ArrayList<>(), doorways, new Point());
        assertEquals(doorways, layout.getDoorways());
    }

    @Test
    void getPlayer() {
        Point player = new Point(1, 2);
        RoomLayout layout = new RoomLayout(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), player);
        assertEquals(player, layout.getPlayer());
    }
}
