package com.mg105.data_access;

import com.mg105.outputds.MoveDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveDataAccessTest {

    @Test
    void getMoveDetailsFirst() {
        MoveDataAccess m = new MoveDataAccess();
        MoveDetails d = m.getMoveDetails("Slow swing");
        assertEquals("Slow swing", d.getName());
        assertEquals(-3, d.getHealthChange());
        assertEquals(0, d.getDamageChange());
        assertFalse(d.isFriendly());
    }

    @Test
    void getMoveDetailsMiddle() {

        MoveDataAccess m = new MoveDataAccess();
        MoveDetails d = m.getMoveDetails("Reinforce");
        assertEquals("Reinforce", d.getName());
        assertEquals(2, d.getHealthChange());
        assertEquals(2, d.getDamageChange());
        assertTrue(d.isFriendly());
    }

    @Test
    void getMoveDetailsLast() {

        MoveDataAccess m = new MoveDataAccess();
        MoveDetails d = m.getMoveDetails("Sabotage");
        assertEquals("Sabotage", d.getName());
        assertEquals(-2, d.getHealthChange());
        assertEquals(-2, d.getDamageChange());
        assertFalse(d.isFriendly());
    }
}
