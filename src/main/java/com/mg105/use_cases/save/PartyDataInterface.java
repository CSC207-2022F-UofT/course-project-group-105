package com.mg105.use_cases.save;

import com.mg105.use_cases.outputds.BattleCharacterDetails;
import org.jetbrains.annotations.NotNull;

/**
 * This interface is implemented by a class that has the ability to save party members stats and
 * retrieve the parties data
 */

public interface PartyDataInterface {

    /**
     * Changes the value of the specified character in the "database"
     *
     * @param name  name of the character to change the stat of
     * @param stat  the name of the stat to change
     * @param value the value to change that stat to
     */
    void changeCharacterStat(@NotNull String name, @NotNull String stat, int value);

    /**
     * Returns an array of objects that each represent a single party members battle attributes
     *
     * @return an array of objects that each represent a single party members battle attributes
     * @see BattleCharacterDetails
     */
    @NotNull
    BattleCharacterDetails[] getPartyBattleDetails();
}
