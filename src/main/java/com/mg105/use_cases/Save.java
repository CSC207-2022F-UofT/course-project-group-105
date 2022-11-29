package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.utils.StatConstants;
import org.jetbrains.annotations.NotNull;

/**
 * This classes only job is to save data about the state of the game in some sort of "database"
 * <p>
 * Most likely this class will be used as composition in other use_case classes
 */

public class Save {

    private GameState state;
    private PartyDataInterface partyAccess;

    public Save(@NotNull GameState state, @NotNull PartyDataInterface partyAccess) {
        this.state = state;
        this.partyAccess = partyAccess;
    }

    // In the future (if we had more time) we would save more data and would use a facade
    // like pattern in order to cleanly save
    // every type of data appropriatley (i.e. save state of inventory, the amount of battles won, number of losses
    // and more)

    // Essentially there would be one multiple other dataInterfaces and objects and this save class would
    // call them to save certain parts of the data

    /**
     * Save the current stats of the party members to a "database"
     */
    public void savePartyBattleStats() {
        for (BattleCharacter player : this.state.getParty()) {
            String name = player.getName();
            partyAccess.changeCharacterStat(name, StatConstants.MAX_HP, player.getMaxHp());
            partyAccess.changeCharacterStat(name, StatConstants.DAMAGE, player.getDmg());
            partyAccess.changeCharacterStat(name, StatConstants.SPEED, player.getSpeed());
        }
    }
}
