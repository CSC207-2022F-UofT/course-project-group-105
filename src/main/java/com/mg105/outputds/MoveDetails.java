package com.mg105.outputds;

import org.jetbrains.annotations.NotNull;

/**
 * A struct like class that represents the details of a move
 */

public class MoveDetails {

    private final String NAME;
    private final int MOVE_CHANGE;
    private final int DAMAGE_CHANGE;
    private final Boolean IS_FRIENDLY;

    public MoveDetails(@NotNull String name, int moveChange, int damageChange,
                       @NotNull boolean isFriendly) {
        this.NAME = name;
        this.MOVE_CHANGE = moveChange;
        this.DAMAGE_CHANGE = damageChange;
        this.IS_FRIENDLY = isFriendly;

    }

    /**
     * returns name of move
     *
     * @return name of move
     */
    public @NotNull String getName() {
        return this.NAME;
    }

    /**
     * Returns move change stat of move
     *
     * @return move change stat of move
     */

    public int getMoveChange() {
        return this.MOVE_CHANGE;
    }

    /**
     * Returns damage change stat of move
     *
     * @return damage change stat of move
     */

    public int getDamageChange() {
        return this.DAMAGE_CHANGE;
    }

    /**
     * Return isFriendly stat of move
     *
     * @return isFriendly stat of move
     */

    public boolean getIsFriendly() {
        return this.IS_FRIENDLY;
    }
}
