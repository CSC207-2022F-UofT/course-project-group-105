package com.mg105.data_control;

import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.creator.MoveDataCreator;
import com.mg105.use_cases.outputds.MoveDetails;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.mg105.utils.MoveConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class MoveDataAccessTest {

    // This test only works for the "initial" state of the user party and moves
    // This is not because the class is dependent on the initial state but because of the
    // hard coded expected values

    @BeforeAll
    static void setData() {
        MoveDataCreator c = new MoveDataCreator();
        c.createDataStorage();
    }

    @Test
    void getMoveDetailsFirst() {
        MoveDataAccess m = new MoveDataAccess();
        MoveDetails d = m.getMoveDetails(SLOW_SWING);
        assertEquals(SLOW_SWING, d.getName());
        assertEquals(-3, d.getHealthChange());
        assertEquals(0, d.getDamageChange());
        assertFalse(d.isFriendly());
    }

    @Test
    void getMoveDetailsMiddle() {

        MoveDataAccess m = new MoveDataAccess();
        MoveDetails d = m.getMoveDetails(REINFORCE);
        assertEquals(REINFORCE, d.getName());
        assertEquals(2, d.getHealthChange());
        assertEquals(1, d.getDamageChange());
        assertTrue(d.isFriendly());
    }

    @Test
    void getMoveDetailsLast() {

        MoveDataAccess m = new MoveDataAccess();
        MoveDetails d = m.getMoveDetails(SABOTAGE);
        assertEquals(SABOTAGE, d.getName());
        assertEquals(-2, d.getHealthChange());
        assertEquals(-2, d.getDamageChange());
        assertFalse(d.isFriendly());
    }
}
