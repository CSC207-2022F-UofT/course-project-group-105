package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Inventory;
import com.mg105.entities.Move;
import com.mg105.entities.items.UpgradeToken;

import static org.junit.jupiter.api.Assertions.*;

class ReplayGeneratorTest {
    @org.junit.jupiter.api.Test
    void attributeInheritance() {
        Move m1 = new Move(5, 0);
        Move m2 = new Move(0, 1);
        BattleCharacter p1 = new BattleCharacter(5, "p1", 5, 5, m1, m2);
        ReplayGenerator master = new ReplayGenerator();
        BattleCharacter p2 = master.attributeInheritance(p1);
        assertEquals(p1.getHp(), p2.getHp());
        assertEquals(p1.getDmg(), p2.getDmg());
        assertEquals(p1.getName(), p2.getName());
        assertEquals(p1.getSpeed(), p2.getSpeed());
        assertEquals(p1.getMaxHp(), p1.getMaxHp());
        assertEquals(p1.getMoveOne(), p2.getMoveOne());
        assertEquals(p1.getMoveTwo(), p2.getMoveTwo());
    }

    @org.junit.jupiter.api.Test
    void inventoryClean() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        ReplayGenerator master = new ReplayGenerator();
        master.inventoryClean(inventory);
        assertEquals(0, inventory.numberOfItems());
    }

    @org.junit.jupiter.api.Test
    void replay() {
    }
}
