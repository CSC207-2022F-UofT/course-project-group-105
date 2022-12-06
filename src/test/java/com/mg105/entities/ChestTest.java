package com.mg105.entities;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.MegaPotion;
import com.mg105.use_cases.map.RoomUpdater;
import com.mg105.use_cases.outputds.ItemDetails;
import com.mg105.use_cases.ChestInteractor;
import com.mg105.use_cases.inventory.InventoryInteractor;
import com.mg105.use_cases.inventory.InventoryPresenterInterface;
import com.mg105.utils.PartyConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    private static final Point coordinates1 = new Point(0, 1);
    private static final Point coordinates2 = new Point(3, 3);
    private static final Point coordinates3 = new Point(2, 3);

    private static final Point coordinates4 = new Point(2, 2);
    private static final WalkingCharacter character = new WalkingCharacter(coordinates3);
    private static final Inventory inventory = new Inventory();
    private static final BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];
    private static final GameState game = new GameState(inventory, party, character);
    private static final InventoryPresenterInterface pres = new InventoryPresenterInterface() {
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
    private static final InventoryInteractor inventoryInteractor = new InventoryInteractor(game, pres);
    private static final ChestInteractor chestInteractor = new ChestInteractor(game, inventoryInteractor, new RoomUpdater());
    private static final ArrayList<TreasureChest> chestList1 = new ArrayList<>();
    private static final ArrayList<TreasureChest> chestList2 = new ArrayList<>();
    private static final ArrayList<OpponentSet> opponents = new ArrayList<>();
    private static final ArrayList<Doorway> doors = new ArrayList<>();
    private static final Inventory in = game.getInventory();

    @BeforeEach
    void setUp() {
        chestList1.clear();
        chestList2.clear();
    }

    @Test
    void testCompareListsYesChest() {
        TreasureChest testChest = new TreasureChest(new MegaPotion(), coordinates2);
        chestList1.add(testChest);
        Point coord = new Point(3, 3);
        assertEquals(testChest, chestInteractor.compareLists(coord, chestList1));
    }

    @Test
    void testCompareListsNoChest() {
        TreasureChest testChest = new TreasureChest(new HealthPotion(), coordinates1);
        chestList1.add(testChest);
        Point coord = new Point(3, 3);
        assertNull(chestInteractor.compareLists(coord, chestList1));
    }

    @Test
    void verifyChestYes() {
        TreasureChest testChest = new TreasureChest(new MegaPotion(), coordinates4);
        chestList1.add(testChest);
        Room firstRoom = new Room(chestList1, opponents, doors);
        Room lastRoom = new Room(chestList2, opponents, doors);
        game.setMap(firstRoom, lastRoom);

        assertEquals(testChest, chestInteractor.verifyChest());
    }

    @Test
    void verifyChestNo() {
        TreasureChest testChest = new TreasureChest(new MegaPotion(), coordinates1);
        chestList1.add(testChest);
        Room firstRoom = new Room(chestList1, opponents, doors);
        Room lastRoom = new Room(chestList2, opponents, doors);
        game.setMap(firstRoom, lastRoom);

        assertNull(chestInteractor.verifyChest());
    }

    @Test
    void openChestTest() {
        TreasureChest chest1 = new TreasureChest(new HealthPotion(), coordinates1);
        assertEquals(coordinates1, chest1.getPosition());
        assertFalse(chest1.isOpened());
        chest1.open();
        assertTrue(chest1.isOpened());
        in.removeItem("Health Potion");
    }

    @Test
    void alreadyOpenChestTest() {
        TreasureChest chest1 = new TreasureChest(new HealthPotion(), coordinates1);
        chest1.open();
        assertTrue(chest1.isOpened());
        assertThrows(AssertionError.class, chest1::open);
        in.removeItem("Health Potion");
    }

    @Test
    void noChest() {
        TreasureChest testChest1 = new TreasureChest(new HealthPotion(), coordinates1);
        chestList1.add(testChest1);
        Room firstRoom = new Room(chestList1, opponents, doors);
        Room lastRoom = new Room(chestList2, opponents, doors);
        game.setMap(firstRoom, lastRoom);

        chestInteractor.getChestItem();
        assertEquals(0, in.numberOfItems());
        assertFalse(testChest1.isOpened());
    }

    @Test
    void yesChest() {
        TreasureChest testChest2 = new TreasureChest(new HealthPotion(), coordinates2);
        chestList1.add(testChest2);
        Room firstRoom = new Room(chestList1, opponents, doors);
        Room lastRoom = new Room(chestList2, opponents, doors);
        game.setMap(firstRoom, lastRoom);

        chestInteractor.getChestItem();
        assertEquals(1, in.numberOfItems());
        assertTrue(in.has("Health Potion"));
        assertTrue(testChest2.isOpened());
        in.removeItem("Health Potion");
    }
}

