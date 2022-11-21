import com.mg105.entities.*;
import com.mg105.entities.items.HealthPotion;
import com.mg105.outputds.ItemDetails;
import com.mg105.presenterinterfaces.InventoryPresenterInterface;
import com.mg105.use_case.Inventory.InventoryInteractor;
import com.mg105.utils.PartyConstants;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    Point coordinates1 = new Point(0, 1);
    Point coordinates2 = new Point(3, 3);
    Point coordinates3 = new Point(2, 3);
    WalkingCharacter character = new WalkingCharacter(coordinates3);
    Inventory inventory = new Inventory();
    BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];
    GameState game = new GameState(inventory, party, character);
    InventoryPresenterInterface res = new InventoryPresenterInterface() {
        @Override
        public void addItemView(boolean isSuccessful, ItemDetails itemDetails) {

        }

        @Override
        public void removeItemView(boolean isSuccessful, ItemDetails itemDetails) {

        }

        @Override
        public void useItemView(boolean isSuccessful, String characterName, ItemDetails itemDetails) {

        }

        @Override
        public void inventoryDetailsView(ItemDetails[] allItemsDetails) {

        }
    };
    InventoryInteractor interactor = new InventoryInteractor(game, res);
    ChestInteractor chestInteractor = new ChestInteractor(game, interactor);

    @Test
    void openChestTest() {
        TreasureChest chest1 = new TreasureChest(new HealthPotion(), coordinates1);
        assertEquals(coordinates1, chest1.getPosition());
        assertFalse(chest1.isOpened());
        chest1.open();
        assertTrue(chest1.isOpened());
    }

    @Test
    void alreadyOpenChestTest() {
        TreasureChest chest1 = new TreasureChest(new HealthPotion(), coordinates1);
        chest1.open();
        assertTrue(chest1.isOpened());
        assertThrows(AssertionError.class, chest1::open);
    }

    @Test
    void noChest(){
        TreasureChest testChest1 = new TreasureChest(new HealthPotion(), coordinates1);
        ArrayList<TreasureChest> chestList1 = new ArrayList<>();
        ArrayList<TreasureChest> chestList2 = new ArrayList<>();
        chestList1.add(testChest1);
        ArrayList<OpponentSet> opponents = new ArrayList<>();
        ArrayList<Doorway> doors = new ArrayList<>();
        Room firstRoom = new Room(chestList1, opponents, doors);
        Room lastRoom = new Room(chestList2, opponents, doors);
        game.setMap(firstRoom, lastRoom);

        chestInteractor.getChestItem();
        Inventory in = game.getInventory();
        assertEquals(0, in.numberOfItems());
        assertFalse(testChest1.isOpened());
    }

    @Test
    void yesChest(){
        TreasureChest testChest2 = new TreasureChest(new HealthPotion(), coordinates2);
        ArrayList<TreasureChest> chestList1 = new ArrayList<>();
        ArrayList<TreasureChest> chestList2 = new ArrayList<>();
        chestList1.add(testChest2);
        ArrayList<OpponentSet> opponents = new ArrayList<>();
        ArrayList<Doorway> doors = new ArrayList<>();
        Room firstRoom = new Room(chestList1, opponents, doors);
        Room lastRoom = new Room(chestList2, opponents, doors);
        game.setMap(firstRoom, lastRoom);

        chestInteractor.getChestItem();
        Inventory in = game.getInventory();
        assertEquals(1, in.numberOfItems());
        assertTrue(in.has("Health Potion"));
        assertTrue(testChest2.isOpened());
    }
}























