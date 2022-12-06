package com.mg105.use_cases.map;

import com.mg105.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class MapGeneratorTest {
    private GameState state;
    private MapGenerator generator;

    @BeforeEach
    void setup() {
        state = new GameState(new Inventory(), new WalkingCharacter(new Point()));
        generator = new MapGenerator(state);
    }

    @Test
    void fillsInRooms() {
        generator.generateMap();
        assertNotNull(state.getCurrentRoom());
    }

    @Test
    void roomsAreConnected() {
        generator.generateMap();

        Queue<Room> toVisit = new LinkedList<>();
        Set<Room> visited = new HashSet<>();

        toVisit.add(state.getCurrentRoom());
        visited.add(state.getCurrentRoom());

        while (!toVisit.isEmpty()) {
            Room current = toVisit.remove();

            state.setCurrentRoom(current);
            if (state.isCurrentRoomLastRoom()) {
                return;
            }

            for (Doorway doorway : current.getDoorways()) {
                Room next = doorway.getNextRoom();
                if (!visited.contains(next)) {
                    visited.add(next);
                    toVisit.add(next);
                }
            }
        }

        fail();
    }
}
