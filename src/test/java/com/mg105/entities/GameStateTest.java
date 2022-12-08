package com.mg105.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {
    private Room firstRoom;
    private Room lastRoom;
    private WalkingCharacter walkingCharacter;
    private GameState state;

    @BeforeEach
    void setup() {
        firstRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        lastRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        walkingCharacter = new WalkingCharacter(new Point());
        state = new GameState(new Inventory(), walkingCharacter);
    }

    @Test
    void lastRoomFalse() {
        state.setMap(firstRoom, lastRoom);
        assertFalse(state.isCurrentRoomLastRoom());
    }

    @Test
    void lastRoomTrue() {
        state.setMap(firstRoom, lastRoom);
        state.setCurrentRoom(lastRoom);
        assertTrue(state.isCurrentRoomLastRoom());
    }

    @Test
    void getWalkingCharacter() {
        assertEquals(walkingCharacter, state.getWalkingCharacter());
    }

    @Test
    void getParty() {
        assertEquals(0, state.getParty().size());
    }

    @Test
    void removeCurrEncounter() {
        state.setCurrEncounter(new Battle(new ArrayList<>(), new ArrayList<>()));
        assertTrue(state.hasCurrEncounter());
        state.removeCurrEncounter();
        assertFalse(state.hasCurrEncounter());
    }
}
