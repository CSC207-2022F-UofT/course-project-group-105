package com.mg105.use_cases;

import com.mg105.entities.*;
import com.mg105.entities.items.UpgradeToken;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ReplayGeneratorTest {
    @org.junit.jupiter.api.Test
    void attributeInheritance() {

        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());
        GameState state = new GameState(inventory, party, walkingCharacter);
        ReplayGenerator replay = new ReplayGenerator(state);

        assertEquals(replay.attributeInheritance().get(0), p1);
    }

    @org.junit.jupiter.api.Test
    void inventoryClean() {
        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());
        GameState state = new GameState(inventory, party, walkingCharacter);
        inventory.addItem(new UpgradeToken());
        ReplayGenerator master = new ReplayGenerator(state);
        master.inventoryClean();
        assertEquals(0, inventory.numberOfItems());
    }

    @org.junit.jupiter.api.Test
    void replay() {
    }
}
