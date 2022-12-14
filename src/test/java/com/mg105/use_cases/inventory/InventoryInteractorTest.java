package com.mg105.use_cases.inventory;

import com.mg105.entities.*;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.use_cases.outputds.ItemDetails;
import com.mg105.utils.ItemConstants;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.mg105.utils.PartyConstants.ALL_PARTY_MEMBER_NAMES;
import static org.junit.jupiter.api.Assertions.*;

class InventoryInteractorTest {

    // Some implementation of the interface (it's not really important for the tests since it really only
    // testing if the interactor mutates the entities properly)

    final InventoryPresenterInterface res = new InventoryPresenterInterface() {
        @Override
        public void addItem(boolean isSuccessful, ItemDetails itemDetails) {

        }

        @Override
        public void removeItem(boolean isSuccessful, ItemDetails itemDetails) {

        }

        @Override
        public void useItem(boolean isSuccessful, String characterName, ItemDetails itemDetails) {

        }

        @Override
        public void inventoryDetails(ItemDetails[] allItemsDetails) {

        }
    };
    final BattleCharacter b1 = new BattleCharacter(1, ALL_PARTY_MEMBER_NAMES[0], 2, 3, false,
        new Move(0, 0, "m1", false),
        new Move(0, 0, "m2", false));
    final BattleCharacter b2 = new BattleCharacter(1, ALL_PARTY_MEMBER_NAMES[1], 2, 3, false,
        new Move(0, 0, "m1", false),
        new Move(0, 0, "m2", false));
    final BattleCharacter b3 = new BattleCharacter(1, ALL_PARTY_MEMBER_NAMES[2], 2, 3, false,
        new Move(0, 0, "m1", false),
        new Move(0, 0, "m2", false));

    final BattleCharacter[] party = {b1, b2, b3};
    final WalkingCharacter walkingCharacter = new WalkingCharacter((new Point(0, 0)));

    @Test
    void addItemEmptyInventoryExists() {
        Inventory inventory = new Inventory();
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
        inventoryInteractor.addItem(ItemConstants.UPGRADE_TOKEN_NAME);
        assertTrue(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));


    }

    @Test
    void addItemInventoryNotExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertFalse(inventory.has("Master ball"));
        inventoryInteractor.addItem("Master ball");
        assertFalse(inventory.has("Master ball"));

    }

    @Test
    void addItemInventoryExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertEquals(1, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));
        inventoryInteractor.addItem(ItemConstants.UPGRADE_TOKEN_NAME);
        assertEquals(2, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));

    }

    @Test
    void addItemInventoryLimitExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());

        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertEquals(6, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));
        inventoryInteractor.addItem(ItemConstants.HEALTH_POTION_NAME);
        assertEquals(6, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));
    }

    @Test
    void removeItemInventoryNotExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertFalse(inventory.has("Master ball"));
        inventoryInteractor.removeItem("Master ball");
        assertFalse(inventory.has("Master ball"));

    }

    @Test
    void removeItemInventoryExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertEquals(1, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));
        inventoryInteractor.removeItem(ItemConstants.UPGRADE_TOKEN_NAME);
        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));

    }

    @Test
    void removeItemInventoryLimitExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());

        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertEquals(6, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));
        inventoryInteractor.removeItem(ItemConstants.HEALTH_POTION_NAME);
        assertEquals(5, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));
    }

    @Test
    void useItemInventoryNotExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        inventoryInteractor.useItem(ItemConstants.UPGRADE_TOKEN_NAME, "GHI");

        for (BattleCharacter p : party) {
            assertEquals(1, p.getMaxHp());
            assertEquals(2, p.getDmg());
            assertEquals(3, p.getSpeed());
        }

        assertEquals(1, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));

    }

    @Test
    void useItemInventoryExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);


        assertEquals(1, inventory.numberOfItems(ItemConstants.UPGRADE_TOKEN_NAME));
        inventoryInteractor.useItem(ItemConstants.UPGRADE_TOKEN_NAME, ALL_PARTY_MEMBER_NAMES[0]);

        assertEquals(2, party[0].getMaxHp());
        assertEquals(3, party[0].getDmg());
        assertEquals(4, party[0].getSpeed());

        assertFalse(inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));


        // Resetting Party member A's stats

        party[0].modifySpeed(-1);
        party[0].modifyMaxHp(-1);
        party[0].modifyDamage(-1);

    }

    @Test
    void useItemInventoryLimitExists() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());

        party[1].modifyHealth(-1);

        assertEquals(0, party[1].getHp());

        GameState state = new GameState(inventory, party, walkingCharacter);
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);

        assertEquals(6, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));
        inventoryInteractor.useItem(ItemConstants.HEALTH_POTION_NAME, ALL_PARTY_MEMBER_NAMES[1]);

        assertEquals(5, inventory.numberOfItems(ItemConstants.HEALTH_POTION_NAME));
        assertEquals(1, party[1].getHp());
    }
}
