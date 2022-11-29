package com.mg105.data_access;

import com.mg105.outputds.BattleCharacterDetails;
import com.mg105.utils.StatConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PartyDataAccessTest {

    private static PartyDataAccess p;
    private static BattleCharacterDetails[] details;

    @BeforeAll
    public static void getDetails() {
        p = new PartyDataAccess(new MoveDataAccess());
        details = p.getPartyBattleDetails();

    }

    @Test
    void changeMaxHP() {

        p.changeCharacterStat("B", StatConstants.MAX_HP, 3);
        Assertions.assertEquals(3, p.getPartyBattleDetails()[1].getMaxHp());
        p.changeCharacterStat("B", StatConstants.MAX_HP, 20);
        Assertions.assertEquals(20, p.getPartyBattleDetails()[1].getMaxHp());
    }

    @Test
    void changeSpeed() {

        p.changeCharacterStat("D", StatConstants.SPEED, 3);
        Assertions.assertEquals(3, p.getPartyBattleDetails()[3].getSpeed());
        p.changeCharacterStat("D", StatConstants.SPEED, 10);
        Assertions.assertEquals(10, p.getPartyBattleDetails()[3].getSpeed());
    }

    @Test
    void changDMG() {

        p.changeCharacterStat("A", StatConstants.DAMAGE, 3);
        Assertions.assertEquals(3, p.getPartyBattleDetails()[0].getDmg());
        p.changeCharacterStat("A", StatConstants.DAMAGE, 4);
        Assertions.assertEquals(4, p.getPartyBattleDetails()[0].getDmg());
    }

    @Test
    void partyNames() {

        Assertions.assertEquals("A", details[0].getName());
        Assertions.assertEquals("B", details[1].getName());
        Assertions.assertEquals("C", details[2].getName());
        Assertions.assertEquals("D", details[3].getName());

    }

    @Test
    void partyMaxHps() {
        Assertions.assertEquals(30, details[0].getMaxHp());
        Assertions.assertEquals(20, details[1].getMaxHp());
        Assertions.assertEquals(25, details[2].getMaxHp());
        Assertions.assertEquals(15, details[3].getMaxHp());
    }

    @Test
    void partyDmgs() {
        Assertions.assertEquals(4, details[0].getDmg());
        Assertions.assertEquals(6, details[1].getDmg());
        Assertions.assertEquals(3, details[2].getDmg());
        Assertions.assertEquals(9, details[3].getDmg());
    }

    @Test
    void partySpeeds() {

        Assertions.assertEquals(5, details[0].getSpeed());
        Assertions.assertEquals(8, details[1].getSpeed());
        Assertions.assertEquals(6, details[2].getSpeed());
        Assertions.assertEquals(10, details[3].getSpeed());
    }

    @Test
    void partyNotOpponent() {
        Assertions.assertFalse(details[0].isOpponent());
        Assertions.assertFalse(details[1].isOpponent());
        Assertions.assertFalse(details[2].isOpponent());
        Assertions.assertFalse(details[3].isOpponent());
    }

    @Test
    void correctMoves() {

        Assertions.assertEquals("Slow swing", details[0].getMoveDetails()[0].getName());
        Assertions.assertEquals("Nullify", details[0].getMoveDetails()[1].getName());

        Assertions.assertEquals("Strong swing", details[1].getMoveDetails()[0].getName());
        Assertions.assertEquals("Weak heal", details[1].getMoveDetails()[1].getName());

        Assertions.assertEquals("Strong heal", details[2].getMoveDetails()[0].getName());
        Assertions.assertEquals("Reinforce", details[2].getMoveDetails()[1].getName());

        Assertions.assertEquals("Surprise attack", details[3].getMoveDetails()[0].getName());
        Assertions.assertEquals("Sabotage", details[3].getMoveDetails()[1].getName());

        // I am sure the rest of move stuff is correct because of the MoveDataAccess tests


    }
}
