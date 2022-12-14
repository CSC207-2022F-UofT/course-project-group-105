package com.mg105.entities;

import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.ItemConstants;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    // Inventory limit is 10 for all tests

    @Test
    void numberOfItemsEmpty() {
        Inventory inventory = new Inventory();
        assertEquals(0, inventory.numberOfItems());
    }

    @Test
    void inLimit() {
        Inventory inventory = new Inventory();
        assertEquals(10, inventory.limit());
    }

    @Test
    void isFullEmptyInventory() {
        Inventory inventory = new Inventory();
        assertFalse(inventory.isFull());
    }

    @Test
    void numberOfItemsOne() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        assertEquals(1, inventory.numberOfItems());
    }

    @Test
    void isFullOneItem() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        assertFalse(inventory.isFull());
    }

    @Test
    void numberOfItemsMultipleItemsByNotOverLimit() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        assertEquals(6, inventory.numberOfItems());
    }

    @Test
    void isFullMultipleItemsByNotOverLimit() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        assertFalse(inventory.isFull());
    }

    @Test
    void numberOfItemsAtLimit() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        assertEquals(10, inventory.numberOfItems());
    }


    @Test
    void isInventoryFullAtLimit() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        assertTrue(inventory.isFull());
    }

    @Test
    void addItemEmptyInventory() {

        Inventory inventory = new Inventory();
        assertEquals(0, inventory.numberOfItems());
        inventory.addItem(new UpgradeToken());
        assertEquals(1, inventory.numberOfItems());
    }

    @Test
    void addItemInventoryFull() {

        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        assertTrue(inventory.addItem(new HealthPotion()));

        assertFalse(inventory.addItem(new UpgradeToken()));

    }

    @Test
    void removeItemEmptyInventory() {

        Inventory inventory = new Inventory();

        assertFalse(inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));


    }

    @Test
    void removeItemOneItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());

        assertEquals(1, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);

        assertEquals(0, inventory.numberOfItems());

    }

    @Test
    void removeItemTwoDifferentItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());

        assertEquals(2, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);

        assertEquals(1, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.HEALTH_POTION_NAME);

        assertEquals(0, inventory.numberOfItems());

        assertFalse(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

        assertFalse(inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));


    }


    @Test
    void removeItemMultipleSameTypeItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());

        assertEquals(4, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);

        assertEquals(3, inventory.numberOfItems());

        assertFalse(inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));

        assertEquals(3, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);

        assertEquals(2, inventory.numberOfItems());

    }

    @Test
    void removeItemMultipleDifferentTypeItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());

        assertEquals(4, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);

        assertEquals(3, inventory.numberOfItems());

        inventory.removeItem(ItemConstants.HEALTH_POTION_NAME);


        assertFalse(inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));

        assertEquals(2, inventory.numberOfItems());

    }

    @Test
    void numberOfItemsByTypeEmpty() {

        Inventory inventory = new Inventory();

        assertEquals(0, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));
    }

    @Test
    void numberOfItemsByTypeOneItem() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());

        assertEquals(1, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));

        assertEquals(0, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));

    }

    @Test
    void numberOfItemsMultipleDifferent() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());

        assertEquals(2, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));

        assertEquals(1, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));

    }

    @Test
    void inInventoryEmpty() {

        Inventory inventory = new Inventory();

        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
    }

    @Test
    void inInventoryOne() {

        Inventory inventory = new Inventory();
        inventory.addItem(new HealthPotion());


        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(inventory.has(ItemConstants.HEALTH_POTION_NAME));

    }

    @Test
    void inInventoryMultipleSameType() {

        Inventory inventory = new Inventory();
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());


        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(inventory.has(ItemConstants.HEALTH_POTION_NAME));

    }

    @Test
    void inInventoryMultipleDifferentType() {

        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());


        assertTrue(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(inventory.has(ItemConstants.HEALTH_POTION_NAME));

    }

    @Test
    void inInventoryMultipleAfterDeletion() {

        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());


        assertTrue(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(inventory.has(ItemConstants.HEALTH_POTION_NAME));
        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);
        inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);
        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));


    }


    @Test
    void useItemNotInInventory() {

        BattleCharacter character = new BattleCharacter(1, "John", 1, 1, false,
            new Move(1, 1, "m1", true),
            new Move(1, 1, "m2", true));

        Inventory inventory = new Inventory();

        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());

        BattleCharacter[] cs = {character};

        GameState state = new GameState(inventory, cs, new WalkingCharacter(new Point()));

        assertFalse(inventory.useItem(state, ItemConstants.UPGRADE_TOKEN_NAME, "John"));

    }

    @Test
    void useItemSingle() {

        BattleCharacter character = new BattleCharacter(1, "John", 2, 3, false,
            new Move(1, 1, "m1", true),
            new Move(1, 1, "m2", true));

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());

        BattleCharacter[] cs = {character};

        GameState state = new GameState(inventory, cs, new WalkingCharacter(new Point()));

        inventory.useItem(state, ItemConstants.UPGRADE_TOKEN_NAME, "John");

        assertEquals(0, inventory.numberOfItems());

        assertEquals(2, character.getMaxHp());
        assertEquals(3, character.getDmg());
        assertEquals(4, character.getSpeed());

    }


    @Test
    void useItemHasUsableItems() {

        BattleCharacter character = new BattleCharacter(100, "John", 1, 1, false,
            new Move(1, 1, "m1", true),
            new Move(1, 1, "m2", true));

        character.modifyHealth(-10);

        Inventory inventory = new Inventory();

        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());

        BattleCharacter[] cs = {character};

        GameState state = new GameState(inventory, cs, new WalkingCharacter(new Point()));

        inventory.useItem(state, ItemConstants.HEALTH_POTION_NAME, "John");

        assertEquals(1, inventory.numberOfItems());

        assertEquals(100, character.getHp());

    }
}
