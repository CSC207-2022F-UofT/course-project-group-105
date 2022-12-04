package com.mg105.use_cases.save;

import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.access.PartyDataAccess;
import com.mg105.data_control.creator.MoveDataCreator;
import com.mg105.data_control.creator.PartyDataCreator;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.WalkingCharacter;
import com.mg105.use_cases.outputds.BattleCharacterDetails;
import com.mg105.use_cases.set_up.state_setter.GameStateSetter;
import com.mg105.use_cases.set_up.state_setter.PartyCreator;
import com.mg105.use_cases.set_up.state_setter.StateSetter;
import com.mg105.utils.StatConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static com.mg105.utils.PartyConstants.ALL_PARTY_MEMBER_NAMES;
import static org.junit.jupiter.api.Assertions.*;

class SaverTest {

    // PartyCreator and PartyDataAccess and MoveDataAccess are all tested through this as well!

    // This test only works for the "inital" state of the user party and moves
    // This is not because the class is depedent on the initial state but because of the
    // hard coded expected values

    private static final GameState state = new GameState(new Inventory(), new WalkingCharacter(new Point()));

    private static final PartyDataAccess pda = new PartyDataAccess(new MoveDataAccess());
    private static final StateSetter[] setters  = {new PartyCreator(pda)};

    private static final GameStateSetter setter = new GameStateSetter(setters);

    private static final Save[] savers = {new PartySaver(state, pda)};

    private static final Saver saver = new Saver(savers);

    @BeforeAll
    static void setData(){
        PartyDataCreator pd = new PartyDataCreator();
        pd.createDataStorage();
        MoveDataCreator m = new MoveDataCreator();
        m.createDataStorage();
        setter.setState(state);
    }

    @Test
    void saveHPChange() {
        state.getPartyMember(ALL_PARTY_MEMBER_NAMES[0]).modifyMaxHp(2);

        saver.save();

        BattleCharacterDetails[] details = pda.getPartyBattleDetails();

        assertEquals(32, details[0].getMaxHp());

        pda.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[0], StatConstants.MAX_HP, 30);

        details = pda.getPartyBattleDetails();


        assertEquals(30, details[0].getMaxHp());

        state.getPartyMember(ALL_PARTY_MEMBER_NAMES[0]).modifyMaxHp(-2);
    }

    @Test
    void saveDMGChange() {
        state.getPartyMember(ALL_PARTY_MEMBER_NAMES[1]).modifyDamage(-2);

        saver.save();

        BattleCharacterDetails[] details = pda.getPartyBattleDetails();

        assertEquals(4, details[1].getDmg());

        pda.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[1], StatConstants.DAMAGE, 6);

        details = pda.getPartyBattleDetails();

        assertEquals(6, details[1].getDmg());

        state.getPartyMember(ALL_PARTY_MEMBER_NAMES[1]).modifyDamage(2);
    }

    @Test
    void saveSpeedChange() {
        state.getPartyMember(ALL_PARTY_MEMBER_NAMES[3]).modifySpeed(10);

        saver.save();

        BattleCharacterDetails[] details = pda.getPartyBattleDetails();

        assertEquals(20, details[3].getSpeed());

        pda.changeCharacterStat(ALL_PARTY_MEMBER_NAMES[3], StatConstants.SPEED, 10);

        details = pda.getPartyBattleDetails();

        assertEquals(10, details[3].getSpeed());

        state.getPartyMember(ALL_PARTY_MEMBER_NAMES[3]).modifySpeed(-10);
    }

}
