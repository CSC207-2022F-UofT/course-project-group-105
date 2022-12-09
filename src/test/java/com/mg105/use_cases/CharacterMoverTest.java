package com.mg105.use_cases;

import com.mg105.entities.*;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.use_cases.map.CharacterMover;
import com.mg105.use_cases.map.CharacterMoverInterface;
import com.mg105.use_cases.map.RoomUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharacterMoverTest {
    private GameState state;
    private CharacterMoverInterface mover;

    @BeforeEach
    void setup() {
        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());

        List<Doorway> lastRoomDoorways = new ArrayList<>();
        Room lastRoom = new Room(new ArrayList<>(), new ArrayList<>(), lastRoomDoorways);

        List<TreasureChest> chests = new ArrayList<>();
        chests.add(new TreasureChest(new UpgradeToken(), new Point(1, 1)));
        chests.add(new TreasureChest(new UpgradeToken(), new Point(1, 2)));
        chests.get(0).open();
        List<OpponentSet> opponents = new ArrayList<>();
        opponents.add(new OpponentSet(new Point(1, 3), new ArrayList<>()));

        List<Doorway> doorways = new ArrayList<>();
        doorways.add(new Doorway(new Point(4, 0), lastRoom));

        Room firstRoom = new Room(chests, opponents, doorways);
        lastRoomDoorways.add(new Doorway(new Point(3, 0), firstRoom));

        state = new GameState(inventory, party, walkingCharacter);
        state.setMap(firstRoom, lastRoom);
        mover = new CharacterMover(state, new RoomUpdater());
    }

    @Test
    void walkingLeft() {
        state.getWalkingCharacter().setCharPosition(new Point(4, 4));
        mover.generateMapMoveBy(new Point(-1, 0));
        assertEquals(new Point(3, 4), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void walkingRight() {
        state.getWalkingCharacter().setCharPosition(new Point(4, 4));
        mover.generateMapMoveBy(new Point(1, 0));
        assertEquals(new Point(5, 4), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void walkingUp() {
        state.getWalkingCharacter().setCharPosition(new Point(4, 4));
        mover.generateMapMoveBy(new Point(0, -1));
        assertEquals(new Point(4, 3), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void walkingDown() {
        state.getWalkingCharacter().setCharPosition(new Point(4, 4));
        mover.generateMapMoveBy(new Point(0, 1));
        assertEquals(new Point(4, 5), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingWallLeft() {
        state.getWalkingCharacter().setCharPosition(new Point(1, 5));
        mover.generateMapMoveBy(new Point(-1, 0));
        assertEquals(new Point(1, 5), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingWallRight() {
        state.getWalkingCharacter().setCharPosition(new Point(6, 5));
        mover.generateMapMoveBy(new Point(1, 0));
        assertEquals(new Point(6, 5), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingWallTop() {
        state.getWalkingCharacter().setCharPosition(new Point(5, 1));
        mover.generateMapMoveBy(new Point(0, -1));
        assertEquals(new Point(5, 1), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingWallBottom() {
        state.getWalkingCharacter().setCharPosition(new Point(5, 6));
        mover.generateMapMoveBy(new Point(0, 1));
        assertEquals(new Point(5, 6), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingEdgeLeft() {
        state.getWalkingCharacter().setCharPosition(new Point(0, 5));
        mover.generateMapMoveBy(new Point(-1, 0));
        assertEquals(new Point(0, 5), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingEdgeRight() {
        state.getWalkingCharacter().setCharPosition(new Point(7, 5));
        mover.generateMapMoveBy(new Point(1, 0));
        assertEquals(new Point(7, 5), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingEdgeTop() {
        state.getWalkingCharacter().setCharPosition(new Point(5, 0));
        mover.generateMapMoveBy(new Point(0, -1));
        assertEquals(new Point(5, 0), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingEdgeBottom() {
        state.getWalkingCharacter().setCharPosition(new Point(5, 7));
        mover.generateMapMoveBy(new Point(0, 1));
        assertEquals(new Point(5, 7), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingChest() {
        state.getWalkingCharacter().setCharPosition(new Point(2, 1));
        mover.generateMapMoveBy(new Point(-1, 0));
        assertEquals(new Point(2, 1), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void hittingOpponentSet() {
        state.getWalkingCharacter().setCharPosition(new Point(2, 3));
        mover.generateMapMoveBy(new Point(-1, 0));
        assertEquals(new Point(2, 3), state.getWalkingCharacter().getCharPosition());
    }

    @Test
    void switchingRooms() {
        state.getWalkingCharacter().setCharPosition(new Point(4, 1));
        mover.generateMapMoveBy(new Point(0, -1));
        assertEquals(new Point(3, 0), state.getWalkingCharacter().getCharPosition());
        assertTrue(state.isCurrentRoomLastRoom());
    }

    @Test
    void switchingRoomsImmediate() {
        state.getWalkingCharacter().setCharPosition(new Point(4, 0));
        mover.generateMapMoveBy(new Point(0, -1));
        assertEquals(new Point(3, 0), state.getWalkingCharacter().getCharPosition());
        assertTrue(state.isCurrentRoomLastRoom());
        // The edge case for immediately returning to a room
    }
}
