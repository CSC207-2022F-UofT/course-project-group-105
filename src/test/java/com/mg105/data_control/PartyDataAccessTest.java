package com.mg105.data_control;

import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.access.PartyDataAccess;
import com.mg105.data_control.creator.MoveDataCreator;
import com.mg105.data_control.creator.PartyDataCreator;
import com.mg105.use_cases.outputds.BattleCharacterDetails;
import com.mg105.utils.StatConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.mg105.utils.MoveConstants.*;
import static com.mg105.utils.PartyConstants.ALL_PARTY_MEMBER_NAMES;

class PartyDataAccessTest {

    // This test only works for the "initial" state of the user party and moves
    // This is not because the class is dependent on the initial state but because of the
    // hard coded expected values

    private static PartyDataAccess p;
    private static BattleCharacterDetails[] details;

    @BeforeAll
    public static void getDetails() {
        PartyDataCreator pd = new PartyDataCreator();
        pd.createDataStorage();
        MoveDataCreator m = new MoveDataCreator();
        m.createDataStorage();

        p = new PartyDataAccess(new MoveDataAccess());
        details = p.getPartyBattleDetails();

    }

    @Test
    void changeMaxHP() {

        p.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[1], StatConstants.MAX_HP, 3);
        Assertions.assertEquals(3, p.getPartyBattleDetails()[1].getMaxHp());
        p.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[1], StatConstants.MAX_HP, 20);
        Assertions.assertEquals(20, p.getPartyBattleDetails()[1].getMaxHp());
    }

    @Test
    void changeSpeed() {

        p.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[3], StatConstants.SPEED, 20);
        Assertions.assertEquals(20, p.getPartyBattleDetails()[3].getSpeed());
        p.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[3], StatConstants.SPEED, 10);
        Assertions.assertEquals(10, p.getPartyBattleDetails()[3].getSpeed());
    }

    @Test
    void changDMG() {

        p.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[0], StatConstants.DAMAGE, 3);
        Assertions.assertEquals(3, p.getPartyBattleDetails()[0].getDmg());
        p.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[0], StatConstants.DAMAGE, 4);
        Assertions.assertEquals(4, p.getPartyBattleDetails()[0].getDmg());
    }

    @Test
    void partyNames() {

        Assertions.assertEquals(ALL_PARTY_MEMBER_NAMES[0], details[0].getName());
        Assertions.assertEquals(ALL_PARTY_MEMBER_NAMES[1], details[1].getName());
        Assertions.assertEquals(ALL_PARTY_MEMBER_NAMES[2], details[2].getName());
        Assertions.assertEquals(ALL_PARTY_MEMBER_NAMES[3], details[3].getName());

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
        Assertions.assertEquals(5, details[1].getDmg());
        Assertions.assertEquals(4, details[2].getDmg());
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

        Assertions.assertEquals(SLOW_SWING, details[0].getMoveDetails()[0].getName());
        Assertions.assertEquals(NULLIFY, details[0].getMoveDetails()[1].getName());

        Assertions.assertEquals(STRONG_SWING, details[1].getMoveDetails()[0].getName());
        Assertions.assertEquals(WEAK_HEAL, details[1].getMoveDetails()[1].getName());

        Assertions.assertEquals(STRONG_HEAL, details[2].getMoveDetails()[0].getName());
        Assertions.assertEquals(REINFORCE, details[2].getMoveDetails()[1].getName());

        Assertions.assertEquals(SURPRISE_ATTACK, details[3].getMoveDetails()[0].getName());
        Assertions.assertEquals(SABOTAGE, details[3].getMoveDetails()[1].getName());

        // I am sure the rest of move stuff is correct because of the MoveDataAccess tests
    }
}
