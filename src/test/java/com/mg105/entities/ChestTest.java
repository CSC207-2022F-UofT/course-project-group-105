package com.mg105.entities;
import com.mg105.entities.items.HealthPotion;
import com.mg105.outputds.ItemDetails;
import com.mg105.use_cases.ChestInteractor;
import com.mg105.use_cases.Inventory.*;
import com.mg105.utils.PartyConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    private static Point coordinates1 = new Point(0, 1);
    private static Point coordinates2 = new Point(3, 3);
    private static Point coordinates3 = new Point(2, 3);
    private static WalkingCharacter character = new WalkingCharacter(coordinates3);
    private static Inventory inventory = new Inventory();
    private static BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];
    private static GameState game = new GameState(inventory, party, character);
    private static InventoryPresenterInterface pres = new InventoryPresenterInterface() {
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
    private static InventoryInteractor inventoryInteractor = new InventoryInteractor(game, pres);
    private static ChestInteractor chestInteractor = new ChestInteractor(game, inventoryInteractor);
    private static ArrayList<TreasureChest> chestList1 = new ArrayList<>();
    private static ArrayList<TreasureChest> chestList2 = new ArrayList<>();
    private static ArrayList<OpponentSet> opponents = new ArrayList<>();
    private static ArrayList<Doorway> doors = new ArrayList<>();
    private static Inventory in = game.getInventory();

    @BeforeEach
    void setUp() {
        chestList1.clear();
        chestList2.clear();
        opponents.clear();
        doors.clear();
    }

    @Test
    void openChestTest() {
        TreasureChest chest1 = new TreasureChest(new HealthPotion(), coordinates1);
        assertEquals(coordinates1, chest1.getPosition());
        assertFalse(chest1.isOpened());
        chest1.open();
        assertTrue(chest1.isOpened());
        in.removeItem("HealthPotion");
    }

    @Test
    void alreadyOpenChestTest() {
        TreasureChest chest1 = new TreasureChest(new HealthPotion(), coordinates1);
        chest1.open();
        assertTrue(chest1.isOpened());
        assertThrows(AssertionError.class, chest1::open);
        in.removeItem("HealthPotion");
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
        in.removeItem("HealthPotion");
    }
}

