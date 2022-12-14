package com.mg105.data_control.creator;

import org.jetbrains.annotations.NotNull;

import static com.mg105.utils.DataAccessConstants.IS_FALSE;
import static com.mg105.utils.DataAccessConstants.PARTY_DATA_PATH;
import static com.mg105.utils.MoveConstants.*;
import static com.mg105.utils.PartyConstants.ALL_PARTY_MEMBER_NAMES;
import static com.mg105.utils.StatConstants.*;

/**
 * This class is responsibility is to create the data storage system pertain to the party (specifically)
 * each party member's battle attributes
 */

public class PartyDataCreator extends DataStorageCreator {

    /**
     * Creates a new instance of party data creator
     */
    public PartyDataCreator() {
        super(PARTY_DATA_PATH, getInitialData());

    }

    /**
     * Returns the data about the party in the initial state of the game (aka a "new game file")
     *
     * @return the data about the party in the initial state of the game
     */
    private static @NotNull String[][] getInitialData() {

        String[] header = {"name", MAX_HP, DAMAGE, SPEED, "isOpponent", "moveName", "moveName"};
        String[] p1 = {ALL_PARTY_MEMBER_NAMES[0], "30", "4", "5", IS_FALSE, SLOW_SWING, NULLIFY};
        String[] p2 = {ALL_PARTY_MEMBER_NAMES[1], "20", "5", "8", IS_FALSE, STRONG_SWING, WEAK_HEAL};
        String[] p3 = {ALL_PARTY_MEMBER_NAMES[2], "25", "4", "6", IS_FALSE, STRONG_HEAL, REINFORCE};
        String[] p4 = {ALL_PARTY_MEMBER_NAMES[3], "15", "9", "10", IS_FALSE, SURPRISE_ATTACK, SABOTAGE};
        return new String[][]{header, p1, p2, p3, p4};


    }
}
