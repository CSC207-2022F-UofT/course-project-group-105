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

public class RoomInterpreterTest {
    @Test
    void getLayout() {
        RoomTileType[][] expected = new RoomTileType[][]{
            {RoomTileType.WALL, RoomTileType.EXIT, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL},
            {RoomTileType.WALL_WITH_FACE, RoomTileType.OPPONENT_SET, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.WALL},
            {RoomTileType.EXIT, RoomTileType.CHEST_OPEN, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.WALL_WITH_FACE},
            {RoomTileType.WALL, RoomTileType.CHEST, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.EXIT},
            {RoomTileType.WALL, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.WALL},
            {RoomTileType.WALL, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.WALL},
            {RoomTileType.WALL, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.FLOOR, RoomTileType.WALL},
            {RoomTileType.WALL_WITH_FACE, RoomTileType.EXIT, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE, RoomTileType.WALL_WITH_FACE},
        };
        RoomInterpreter interpreter = new RoomInterpreter(new FakeRoomGetter());
        RoomTileType[][] actual = interpreter.getCurrentRoom();

        for (int y = 0; y < MapConstants.ROOM_SIZE; y++) {
            for (int x = 0; x < MapConstants.ROOM_SIZE; x++) {
                assertEquals(expected[y][x], actual[y][x]);
            }
        }
    }

    @Test
    void getPlayer() {
        RoomInterpreter interpreter = new RoomInterpreter(new FakeRoomGetter());
        assertEquals(new Point(4, 4), interpreter.getPlayer());
    }

    @Test
    void getSprite() {
        RoomInterpreter interpreter = new RoomInterpreter(new FakeRoomGetter());
        assertEquals("/sprites/B.png", interpreter.getCharacterSprite());
    }

    private static class FakeRoomGetter implements RoomGetterInterface {
        @Override
        public @NotNull RoomLayout getCurrentRoomLayout() {
            List<Point> closedChests = new ArrayList<>();
            closedChests.add(new Point(1, 3));

            List<Point> openChests = new ArrayList<>();
            openChests.add(new Point(1, 2));

            List<Point> opponents = new ArrayList<>();
            opponents.add(new Point(1, 1));

            List<Point> doorways = new ArrayList<>();
            doorways.add(new Point(0, 2));
            doorways.add(new Point(1, 0));
            doorways.add(new Point(MapConstants.ROOM_SIZE-1, 3));
            doorways.add(new Point(1, MapConstants.ROOM_SIZE-1));

            return new RoomLayout(closedChests, openChests, opponents, doorways, new Point(4, 4));
        }

        @Override
        public @NotNull String getWalkingSprite() {
            return "/sprites/B.png";
        }

        @Override
        public boolean isFinalRoom() {
            return false;
        }
    }
}
