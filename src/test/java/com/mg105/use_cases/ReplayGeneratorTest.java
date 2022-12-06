package com.mg105.use_cases;

import com.mg105.use_cases.map.ReplayGenerator;
import org.junit.jupiter.api.Test;
import com.mg105.entities.*;
import com.mg105.entities.items.UpgradeToken;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ReplayGeneratorTest {
    @Test
    void attributeInheritance() {

        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());
        GameState state = new GameState(inventory, party, walkingCharacter);
        ReplayGenerator replay = new ReplayGenerator(state);
        replay.replay();

        assertEquals(5, p1.getHp());
    }

    @Test
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
        master.replay();
        assertEquals(0, inventory.numberOfItems());
    }

    @Test
    void replay() {
        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());
        GameState state = new GameState(inventory, party, walkingCharacter);
        ReplayGenerator replay = new ReplayGenerator(state);
        replay.replay();

        assertEquals(5, p1.getHp());
        assertEquals(0, inventory.numberOfItems());
    }

    @Test
    void reset() {
        CountingResetable resetable1 = new CountingResetable();
        CountingResetable resetable2 = new CountingResetable();

        Move m1 = new Move(5, 0, "a", true);
        Move m2 = new Move(0, 1, "b", true);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, true, m1, m2);
        BattleCharacter[] party = {p1};
        Inventory inventory = new Inventory();
        WalkingCharacter walkingCharacter = new WalkingCharacter(new Point());
        GameState state = new GameState(inventory, party, walkingCharacter);
        ReplayGenerator replay = new ReplayGenerator(state, resetable1, resetable2);
        replay.replay();

        assertEquals(1, resetable1.getCount());
        assertEquals(1, resetable2.getCount());
    }

    private static class CountingResetable implements Resetable {
        private int count = 0;

        @Override
        public void reset() {
            count += 1;
        }

        public int getCount() {
            return count;
        }
    }
}
