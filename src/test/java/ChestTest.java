import com.mg105.entities.*;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.presenterinterfaces.InventoryPresenterInterface;
import com.mg105.usecase.Inventory.InventoryInteractor;
import com.mg105.utils.ItemConstants;
import com.mg105.utils.PartyConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.awt.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    Point coordinates1 = new Point(0, 1);
    Point coordinates2 = new Point(3, 3);
    Point coordinates3 = new Point(2, 3);
    WalkingCharacter character = new WalkingCharacter(coordinates3);
    Inventory inventory = new Inventory();
    BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];
    GameState game = new GameState(inventory, party, character);
    ChestInteractor chestInteractor = new ChestInteractor(game);

    @Test
    void openChestTest() {
        TreasureChest chest = new TreasureChest(new HealthPotion(), coordinates1);
        assertEquals(coordinates1, chest.getPosition());
        assertFalse(chest.isOpened());
        chest.open();
        assertTrue(chest.isOpened());
    }

    @Test
    void alreadyOpenChestTest() {
        TreasureChest chest = new TreasureChest(new HealthPotion(), coordinates1);
        chest.open();
        assertTrue(chest.isOpened());
        assertThrows(AssertionError.class, chest::open);
    }
    
    @Test
    void noChest(){
        TreasureChest chest = new TreasureChest(new HealthPotion(), coordinates1);
        chestInteractor.getChestItem();
        Inventory in = game.getInventory();
        assertEquals(0, in.numberOfItems());
        assertFalse(chest.isOpened());
    }
    
    @Test
    void yesChest(){
        TreasureChest chest = new TreasureChest(new HealthPotion(), coordinates1);
        chestInteractor.getChestItem();
        Inventory in = game.getInventory();
        assertEquals(1, in.numberOfItems());
        assertTrue(in.has("Health Potion"));
        assertTrue(chest.isOpened());
    }
}























