package com.mg105.use_cases.save;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.utils.StatConstants;
import org.jetbrains.annotations.NotNull;

/**
 * This classes only job is to save data about the party in some sort of "database"
 * <p>
 * Most likely this class will be used as composition in other use_case classes
 */

public class PartySaver implements Save {

    private final GameState state;
    private final PartyDataInterface partyAccess;

    public PartySaver(@NotNull GameState state, @NotNull PartyDataInterface partyAccess) {
        this.state = state;
        this.partyAccess = partyAccess;
    }

    /**
     * Save the current stats of the party members to a "database"
     */

    @Override
    public void save() {
        for (BattleCharacter player : this.state.getParty()) {
            String name = player.getName();
            this.partyAccess.changeCharacterStat(name, StatConstants.MAX_HP, player.getMaxHp());
            this.partyAccess.changeCharacterStat(name, StatConstants.DAMAGE, player.getDmg());
            this.partyAccess.changeCharacterStat(name, StatConstants.SPEED, player.getSpeed());
        }
    }
}