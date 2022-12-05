package com.mg105.use_cases.set_up.state_setter;

import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.access.PartyDataAccess;
import com.mg105.data_control.creator.MoveDataCreator;
import com.mg105.data_control.creator.PartyDataCreator;
import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.WalkingCharacter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.mg105.utils.MoveConstants.*;
import static com.mg105.utils.PartyConstants.ALL_PARTY_MEMBER_NAMES;
import static org.junit.jupiter.api.Assertions.*;

class GameStateSetterTest {

    // PartyCreator and PartyDataAccess and MoveDataAccess are all tested through this as well!

    // This test only works for the "inital" state of the user party and moves
    // This is not because the class is depedent on the initial state but because of the
    // hard coded expected values

    private static final GameState state = new GameState(new Inventory(), new WalkingCharacter(new Point()));


    private static final StateSetter[] setters  = {new PartyCreator(new PartyDataAccess(new MoveDataAccess()))};

    private static final GameStateSetter setter = new GameStateSetter(setters);

    @BeforeAll
    static void setData(){
        PartyDataCreator pd = new PartyDataCreator();
        pd.createDataStorage();
        MoveDataCreator m = new MoveDataCreator();
        m.createDataStorage();
        setter.setState(state);
    }

    @Test
    void PartyMember1Correct() {

        BattleCharacter p = state.getPartyMember(ALL_PARTY_MEMBER_NAMES[0]);
        assertEquals(30, p.getMaxHp());
        assertEquals(4, p.getDmg());
        assertEquals(5, p.getSpeed());
        assertFalse(p.isOpponent());
        assertEquals(SLOW_SWING, p.getMoveOne().getName());
        assertEquals(NULLIFY, p.getMoveTwo().getName());
    }

    @Test
    void PartyMember2Correct() {

        BattleCharacter p = state.getPartyMember(ALL_PARTY_MEMBER_NAMES[1]);
        assertEquals(20, p.getMaxHp());
        assertEquals(5, p.getDmg());
        assertEquals(8, p.getSpeed());
        assertFalse(p.isOpponent());
        assertEquals(STRONG_SWING, p.getMoveOne().getName());
        assertEquals(WEAK_HEAL, p.getMoveTwo().getName());
    }

    @Test
    void PartyMember3Correct() {

        BattleCharacter p = state.getPartyMember(ALL_PARTY_MEMBER_NAMES[2]);
        assertEquals(25, p.getMaxHp());
        assertEquals(4, p.getDmg());
        assertEquals(6, p.getSpeed());
        assertFalse(p.isOpponent());
        assertEquals(STRONG_HEAL, p.getMoveOne().getName());
        assertEquals(REINFORCE, p.getMoveTwo().getName());
    }

    @Test
    void PartyMember4Correct() {

        BattleCharacter p = state.getPartyMember(ALL_PARTY_MEMBER_NAMES[3]);
        assertEquals(15, p.getMaxHp());
        assertEquals(9, p.getDmg());
        assertEquals(10, p.getSpeed());
        assertFalse(p.isOpponent());
        assertEquals(SURPRISE_ATTACK, p.getMoveOne().getName());
        assertEquals(SABOTAGE, p.getMoveTwo().getName());
    }
}
