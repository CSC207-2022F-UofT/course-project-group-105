package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Move;
import com.mg105.outputds.BattleCharacterDetails;
import com.mg105.outputds.MoveDetails;
import com.mg105.utils.PartyConstants;
import org.jetbrains.annotations.NotNull;

/**
 * This class should be called once when the game is first starting. Its only job is to create the party members
 * for the game
 */

public class PartyCreator {
    private final GameState STATE;
    private final PartyDataInterface PARTY_ACCESS;

    public PartyCreator(@NotNull GameState state, @NotNull PartyDataInterface partyAccess) {
        this.STATE = state;
        this.PARTY_ACCESS = partyAccess;
    }

    /**
     * Sets the correct party with the correct members and attributes in the state based on the values in a "database"
     */

    public void constructParty() {
        BattleCharacterDetails[] partyDetails = PARTY_ACCESS.getPartyBattleDetails();

        BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];

        for (int i = 0; i < partyDetails.length; i++) {
            party[i] = createPartyMember(partyDetails[i]);
        }

        STATE.setParty(party);

    }

    private BattleCharacter createPartyMember(@NotNull BattleCharacterDetails details) {
        Move first = createMove(details.getMoveDetails()[0]);
        Move second = createMove(details.getMoveDetails()[1]);

        return new BattleCharacter(details.getMaxHp(), details.getName(), details.getDmg(), details.getSpeed(),
            details.isOpponent(), first, second);
    }

    private Move createMove(@NotNull MoveDetails details) {
        return new Move(details.getHealthChange(), details.getDamageChange(), details.getName(), details.isFriendly());

    }
}
