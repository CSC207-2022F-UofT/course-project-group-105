package com.mg105.use_cases;

import com.mg105.entities.*;
import com.mg105.entities.items.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapGenerator {
    private final @NotNull GameState state;

    /**
     * Creates a new MapGenerator that acts on a GameState.
     *
     * @param state the game state.
     */
    public MapGenerator(@NotNull GameState state) {
        this.state = state;
    }

    /**
     * Generates a map.
     */
    public void generateMap() {
        // NOTE: This is a bad, non-SOLID, non-Clean architecture function (eg. there are no helpers even though there
        // probably should, hardcoded constants, etc.)... and that's okay!  This implementation is only meant to be
        // "good enough" so that we have a map to use to work on other features.  Better map generation will come later.
        //
        // As such, this is the map generated: two rooms directly linked in a straight line. The first room contains a
        // single battle and a single chest.
        //
        // If you happen to need something special to test your specific use case, you should just modify this function.

        // Create the rooms
        Room firstRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Room lastRoom = new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Link them together
        firstRoom.getDoorways().add(new Doorway(new Point(4, 7), lastRoom));
        lastRoom.getDoorways().add(new Doorway(new Point(4, 0), firstRoom));

        // Add battles
        List<BattleCharacter> firstRoomBattle = new ArrayList<>();
        firstRoomBattle.add(new BattleCharacter(
            10, "Mr. Dr. Professor Chad", 10, 10, true,
            new Move(-10, -10, "Move1", false),
            new Move(1, 1, "Move2", false))
        );
        firstRoom.getOpponents().add(new OpponentSet(new Point(4, 6), firstRoomBattle));

        // Add chests
        firstRoom.getChests().add(new TreasureChest(new HealthPotion(), new Point(2, 2)));

        // Finally set the map
        state.setMap(firstRoom, lastRoom);
    }
}
