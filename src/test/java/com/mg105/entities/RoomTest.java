package com.mg105.entities;

import com.mg105.entities.items.MegaPotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTest {
    private List<TreasureChest> chests;
    private List<OpponentSet> opponents;
    private List<Doorway> doorways;
    private Room room;

    @BeforeEach
    void setup() {
        chests = new ArrayList<>();
        chests.add(new TreasureChest(new MegaPotion(), new Point()));

        opponents = new ArrayList<>();

        doorways = new ArrayList<>();
        doorways.add(new Doorway(new Point(), new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>())));

        // We create copies to test for modification
        room = new Room(
            new ArrayList<>(chests),
            new ArrayList<>(opponents),
            new ArrayList<>(doorways)
        );
    }

    @Test
    void getChests() {
        assertEquals(chests, room.getChests());
    }

    @Test
    void getDoorways() {
        assertEquals(doorways, room.getDoorways());
    }

    @Test
    void getOpponents() {
        assertEquals(opponents, room.getOpponents());
    }
}
