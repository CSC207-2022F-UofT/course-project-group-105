package com.mg105.use_cases.outputds;

import org.jetbrains.annotations.NotNull;

/**
 * A struct like class that represents the details of a move
 */

public class MoveDetails {

    @NotNull
    private final String name;
    private final int healthChange;
    private final int damageChange;
    private final boolean isFriendly;

    /**
     * Creates a new instance of MoveDetails
     *
     * @param name         of the move
     * @param healthChange of the move
     * @param damageChange of the move
     * @param isFriendly   true iff the move is friendly
     */
    public MoveDetails(@NotNull String name, int healthChange, int damageChange,
                       boolean isFriendly) {
        this.name = name;
        this.healthChange = healthChange;
        this.damageChange = damageChange;
        this.isFriendly = isFriendly;

    }

    /**
     * returns name of move
     *
     * @return name of move
     */
    public @NotNull String getName() {
        return this.name;
    }

    /**
     * Returns move change stat of move
     *
     * @return move change stat of move
     */

    public int getHealthChange() {
        return this.healthChange;
    }

    /**
     * Returns damage change stat of move
     *
     * @return damage change stat of move
     */

    public int getDamageChange() {
        return this.damageChange;
    }

    /**
     * Return isFriendly stat of move
     *
     * @return isFriendly stat of move
     */

    public boolean isFriendly() {
        return this.isFriendly;
    }
}
