package com.mg105.use_cases.map;

import com.mg105.entities.*;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.use_cases.outputds.RoomLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomGetterTest {
    private GameState state;
    private RoomGetterInterface getter;

    @BeforeEach
    void setup() {
        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());
        state = new GameState(inventory, party, walkingCharacter);
        getter = new RoomGetter(state);
    }

    @Test
    void getWalkingSprite() {
        assertNotEquals(null, RoomGetterTest.class.getResourceAsStream(getter.getWalkingSprite()));
        // Check the resources is plausible, in practice the only way to really be sure of this is to
        // look for the missing texture, texture when playing the game.
    }

    @Test
    void isFinalRoom() {
        Room firstRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        state.setMap(
            firstRoom,
            new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>())
        );
        state.setCurrentRoom(firstRoom);
        assertFalse(getter.isFinalRoom());
    }

    @Test
    void isNotFinalRoom() {
        Room lastRom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        state.setMap(
            new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()),
            lastRom
        );
        state.setCurrentRoom(lastRom);
        assertTrue(getter.isFinalRoom());
    }

    @Test
    void getLayout() {
        Room lastRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<TreasureChest> chests = new ArrayList<>();
        chests.add(new TreasureChest(new UpgradeToken(), new Point(1, 1)));
        chests.add(new TreasureChest(new UpgradeToken(), new Point(1, 2)));
        chests.get(0).open();

        List<OpponentSet> opponents = new ArrayList<>();
        opponents.add(new OpponentSet(new Point(2, 1), new ArrayList<>()));

        List<Doorway> doorways = new ArrayList<>();
        doorways.add(new Doorway(new Point(3, 1), lastRoom));

        Room firstRoom = new Room(chests, opponents, doorways);
        state.setMap(firstRoom, lastRoom);

        RoomLayout layout = getter.getCurrentRoomLayout();
        assertEquals(List.of(new Point[]{new Point(1, 1)}), layout.getOpenChests());
        assertEquals(List.of(new Point[]{new Point(1, 2)}), layout.getClosedChests());
        assertEquals(List.of(new Point[]{new Point(2, 1)}), layout.getOpponents());
        assertEquals(List.of(new Point[]{new Point(3, 1)}), layout.getDoorways());
    }
}
