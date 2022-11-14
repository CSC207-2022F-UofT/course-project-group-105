import com.mg105.entities.*;
import com.mg105.entities.items.HealthPotion;
import com.mg105.entities.items.UpgradeToken;
import com.mg105.utils.ItemConstants;
import org.junit.jupiter.api.Test;

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
        assertEquals(false, chest.isOpened());
        chest.open();
        assertEquals(true, chest.isOpened());
    }

    @Test
    void alreadyOpenChestTest() {
        TreasureChest chest = new TreasureChest(new HealthPotion(), coordinates1);
        chest.open();
        assertEquals(true, chest.isOpened());
        assertEquals(false, chest.open());
    }
}























