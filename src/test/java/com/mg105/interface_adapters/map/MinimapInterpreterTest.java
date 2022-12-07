package com.mg105.interface_adapters.map;

import com.mg105.use_cases.map.RoomGetterInterface;
import com.mg105.use_cases.outputds.RoomLayout;
import com.mg105.utils.MapConstants;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimapInterpreterTest {
    @Test
    void getInitialPosition() {
        MinimapInterpreter interpreter = new MinimapInterpreter(new FakeRoomGetter());
        assertEquals(new Point(), interpreter.getCurrentPosition());
    }

    @Test
    void getInitialMinimap() {
        MinimapInterpreter interpreter = new MinimapInterpreter(new FakeRoomGetter());
        MinimapRoomState[][] minimap = interpreter.getMapSoFar();
        assertEquals(1, minimap.length);
        assertEquals(1, minimap[0].length);
        assertEquals(MinimapRoomState.EXPLORED, minimap[0][0]);
    }

    @Test
    void exploreRoomTop() {
        FakeRoomGetter getter = new FakeRoomGetter();
        MinimapInterpreter interpreter = new MinimapInterpreter(getter);
        interpreter.propertyChange(null);
        getter.setCurrentPosition(new Point(5, 0));
        interpreter.propertyChange(null);
        MinimapRoomState[][] minimap = interpreter.getMapSoFar();
        assertEquals(3, minimap.length);
        assertEquals(3, minimap[0].length);
        MinimapRoomState[][] expected = new MinimapRoomState[][]{
            {null, MinimapRoomState.EXPLORED, null},
            {MinimapRoomState.UNEXPLORED, MinimapRoomState.EXPLORED, MinimapRoomState.UNEXPLORED},
            {null, MinimapRoomState.UNEXPLORED, null}
        };
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertEquals(expected[y][x], minimap[y][x]);
            }
        }
    }

    @Test
    void exploreRoomBottom() {
        FakeRoomGetter getter = new FakeRoomGetter();
        MinimapInterpreter interpreter = new MinimapInterpreter(getter);
        interpreter.propertyChange(null);
        getter.setCurrentPosition(new Point(5, MapConstants.ROOM_SIZE - 1));
        interpreter.propertyChange(null);
        MinimapRoomState[][] minimap = interpreter.getMapSoFar();
        assertEquals(3, minimap.length);
        assertEquals(3, minimap[0].length);
        MinimapRoomState[][] expected = new MinimapRoomState[][]{
            {null, MinimapRoomState.UNEXPLORED, null},
            {MinimapRoomState.UNEXPLORED, MinimapRoomState.EXPLORED, MinimapRoomState.UNEXPLORED},
            {null, MinimapRoomState.EXPLORED, null}
        };
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertEquals(expected[y][x], minimap[y][x]);
            }
        }
    }

    @Test
    void exploreRoomLeft() {
        FakeRoomGetter getter = new FakeRoomGetter();
        MinimapInterpreter interpreter = new MinimapInterpreter(getter);
        interpreter.propertyChange(null);
        getter.setCurrentPosition(new Point(0, 5));
        interpreter.propertyChange(null);
        MinimapRoomState[][] minimap = interpreter.getMapSoFar();
        assertEquals(3, minimap.length);
        assertEquals(3, minimap[0].length);
        MinimapRoomState[][] expected = new MinimapRoomState[][]{
            {null, MinimapRoomState.UNEXPLORED, null},
            {MinimapRoomState.EXPLORED, MinimapRoomState.EXPLORED, MinimapRoomState.UNEXPLORED},
            {null, MinimapRoomState.UNEXPLORED, null}
        };
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertEquals(expected[y][x], minimap[y][x]);
            }
        }
    }

    @Test
    void exploreRoomRight() {
        FakeRoomGetter getter = new FakeRoomGetter();
        MinimapInterpreter interpreter = new MinimapInterpreter(getter);
        interpreter.propertyChange(null);
        getter.setCurrentPosition(new Point(MapConstants.ROOM_SIZE - 1, 5));
        interpreter.propertyChange(null);
        MinimapRoomState[][] minimap = interpreter.getMapSoFar();
        assertEquals(3, minimap.length);
        assertEquals(3, minimap[0].length);
        MinimapRoomState[][] expected = new MinimapRoomState[][]{
            {null, MinimapRoomState.UNEXPLORED, null},
            {MinimapRoomState.UNEXPLORED, MinimapRoomState.EXPLORED, MinimapRoomState.EXPLORED},
            {null, MinimapRoomState.UNEXPLORED, null}
        };
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                assertEquals(expected[y][x], minimap[y][x]);
            }
        }
    }

    private static class FakeRoomGetter implements RoomGetterInterface {
        private Point currentPosition = new Point(4, 4);

        public void setCurrentPosition(Point currentPosition) {
            this.currentPosition = currentPosition;
        }

        @Override
        public @NotNull RoomLayout getCurrentRoomLayout() {
            List<Point> doorways = new ArrayList<>();
            doorways.add(new Point(0, 2));
            doorways.add(new Point(1, 0));
            doorways.add(new Point(MapConstants.ROOM_SIZE-1, 3));
            doorways.add(new Point(1, MapConstants.ROOM_SIZE-1));

            return new RoomLayout(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), doorways, currentPosition);
        }

        @Override
        public @NotNull String getWalkingSprite() {
            return "/sprites/A.png";
        }

        @Override
        public boolean isFinalRoom() {
            return false;
        }
    }
}
