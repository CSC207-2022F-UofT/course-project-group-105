import com.mg105.entities.Inventory;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.ItemConstants;
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
        assertTrue(inventory.numberOfItems() == 0);
        inventory.addItem(new UpgradeToken());
        assertTrue(inventory.numberOfItems() == 1);
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

        assertTrue(!inventory.addItem(new UpgradeToken()));

    }

    @Test
    void removeItemEmptyInventory() {

        Inventory inventory = new Inventory();

        assertTrue(!inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

    }

    @Test
    void removeItemOneItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());

        assertEquals(1, inventory.numberOfItems());

        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

        assertEquals(0, inventory.numberOfItems());

    }

    @Test
    void removeItemTwoDifferentItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());
        inventory.addItem(new HealthPotion());

        assertEquals(2, inventory.numberOfItems());

        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

        assertEquals(1, inventory.numberOfItems());

        assertTrue(inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));

        assertEquals(0, inventory.numberOfItems());

        assertTrue(!inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

        assertTrue(!inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));

    }


    @Test
    void removeItemMultipleSameTypeItemInventory() {

        Inventory inventory = new Inventory();

        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());
        inventory.addItem(new UpgradeToken());

        assertEquals(4, inventory.numberOfItems());

        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

        assertEquals(3, inventory.numberOfItems());

        assertTrue(!inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));

        assertEquals(3, inventory.numberOfItems());

        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

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

        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));

        assertEquals(3, inventory.numberOfItems());

        assertTrue(inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));


        assertTrue(!inventory.removeItem(ItemConstants.HEALTH_POTION_NAME));

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

        assertTrue(!inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
    }

    @Test
    void inInventoryOne() {

        Inventory inventory = new Inventory();
        inventory.addItem(new HealthPotion());


        assertTrue(!inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(inventory.has(ItemConstants.HEALTH_POTION_NAME));

    }

    @Test
    void inInventoryMultipleSameType() {

        Inventory inventory = new Inventory();
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());
        inventory.addItem(new HealthPotion());


        assertTrue(!inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));
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
        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(inventory.removeItem(ItemConstants.UPGRADE_TOKEN_NAME));
        assertTrue(!inventory.has(ItemConstants.UPGRADE_TOKEN_NAME));


    }
}
