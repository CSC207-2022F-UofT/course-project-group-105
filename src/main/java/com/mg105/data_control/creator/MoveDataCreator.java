package com.mg105.data_control.creator;

import org.jetbrains.annotations.NotNull;

import static com.mg105.utils.DataAccessConstants.*;
import static com.mg105.utils.MoveConstants.*;

/**
 * This class is responsible for creating the storage system pertaining to move data
 */

public class MoveDataCreator extends DataStorageCreator {
    /**
     * Create a new MoveDataCreator
     */
    public MoveDataCreator() {
        super(MOVE_DATA_PATH, getInitialData());

    }

    /**
     * Gets the initial data.
     *
     * @return the initial data.
     */
    private static @NotNull String[][] getInitialData() {

        String[] header = {"name", "moveChange", "damageChange", "isFriendly"};
        String[] m1 = {SLOW_SWING, "-3", "0", IS_FALSE};
        String[] m2 = {NULLIFY, "0", "-3", IS_FALSE};
        String[] m3 = {STRONG_SWING, "-4", "0", IS_FALSE};
        String[] m4 = {STRONG_HEAL, "6", "0", IS_TRUE};
        String[] m5 = {WEAK_HEAL, "3", "0", IS_TRUE};
        String[] m6 = {REINFORCE, "2", "1", IS_TRUE};
        String[] m7 = {SURPRISE_ATTACK, "-5", "0", IS_FALSE};
        String[] m8 = {SABOTAGE, "-2", "-2", IS_FALSE};
        return new String[][]{header, m1, m2, m3, m4, m5, m6, m7, m8};


    }
}
