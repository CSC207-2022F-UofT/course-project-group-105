package com.mg105.use_cases.set_up.state_setter;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Move;
import com.mg105.use_cases.outputds.BattleCharacterDetails;
import com.mg105.use_cases.outputds.MoveDetails;
import com.mg105.use_cases.save.PartyDataInterface;
import com.mg105.utils.PartyConstants;
import org.jetbrains.annotations.NotNull;

/**
 * This class should be called once when the game is first starting. Its only job is to create the party members
 * for the game
 */

public class PartyCreator implements StateSetter {
    @NotNull private final PartyDataInterface party_access;

    /**
     * Creates a new instance of PartyCreator
     * @param partyAccess an object that is used to access information of the party from the data storage system
     */
    public PartyCreator(@NotNull PartyDataInterface partyAccess) {

        this.party_access = partyAccess;
    }

    /**
     * Sets the correct party with the correct members and attributes in the state based on the values in a "database"
     */

    @Override
    public void setState(GameState state) {
        BattleCharacterDetails[] partyDetails = party_access.getPartyBattleDetails();

        BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];

        for (int i = 0; i < partyDetails.length; i++) {
            party[i] = createPartyMember(partyDetails[i]);
        }

        state.setParty(party);

    }

    private @NotNull BattleCharacter createPartyMember(@NotNull BattleCharacterDetails details) {
        Move first = createMove(details.getMoveDetails()[0]);
        Move second = createMove(details.getMoveDetails()[1]);

        return new BattleCharacter(details.getMaxHp(), details.getName(), details.getDmg(), details.getSpeed(),
            details.isOpponent(), first, second);
    }

    private @NotNull Move createMove(@NotNull MoveDetails details) {
        return new Move(details.getHealthChange(), details.getDamageChange(), details.getName(), details.isFriendly());

    }
}
