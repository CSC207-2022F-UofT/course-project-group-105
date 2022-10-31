import com.mg105.entities.Inventory;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    // Inventory limit is 10 for all tests

    @Test
    void numberOfItemsEmpty() {
        Inventory inventory = new Inventory();
        assertEquals(0, inventory.numberOfItems());
    }


    @Test
    void isInventoryFullEmptyInventory() {
        Inventory inventory = new Inventory();
        assertFalse(inventory.isInventoryFull());
    }

    @Test
    void numberOfItemsOne() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        assertEquals(1, inventory.numberOfItems());
    }

    @Test
    void isInventoryFullOneItem() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        assertFalse(inventory.isInventoryFull());
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
    void isInventoryFullMultipleItemsByNotOverLimit() {
        Inventory inventory = new Inventory();
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        assertFalse(inventory.isInventoryFull());
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
        assertTrue(inventory.isInventoryFull());
    }

    @Test
    void addItemEmptyInventory() {

        Inventory inventory = new Inventory();
        assertTrue(inventory.numberOfItems() == 0);
        inventory.addItem(new UpgradeToken());
        assertTrue(inventory.numberOfItems() == 1);
    }

//    @Test(expected=RuntimeException.class)
//    void addItemInventoryFull() {
//
//        Inventory inventory = new Inventory();
//        inventory.addItem(new UpgradeToken());
//        inventory.addItem(new HealthPotion());
//        inventory.addItem(new UpgradeToken());
//        inventory.addItem(new HealthPotion());
//        inventory.addItem(new UpgradeToken());
//        inventory.addItem(new UpgradeToken());
//        inventory.addItem(new UpgradeToken());
//        inventory.addItem(new HealthPotion());
//        inventory.addItem(new UpgradeToken());
//        inventory.addItem(new HealthPotion());
//
//        // Should throw RunTimeError
//        inventory.addItem(new UpgradeToken());
//
//    }

    @Test
    void removeItem() {
    }

    @Test
    void numberOfItemsByType() {
    }

    @Test
    void inInventory() {
    }
}
