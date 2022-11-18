import com.mg105.entities.*;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.ItemConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.awt.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ChestTest {

    Point coordinates1 = new Point(0, 1);
    Point coordinates2 = new Point(3, 3);

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
        assertThrows(AssertionError.class, () -> chest.open());
    }
}























