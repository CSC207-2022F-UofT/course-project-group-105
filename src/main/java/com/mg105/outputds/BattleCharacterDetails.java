package com.mg105.outputds;

import org.jetbrains.annotations.NotNull;

/**
 * A struct like class that represents a BattleCharacter
 */

public class BattleCharacterDetails {

    private final String NAME;
    private final int MAX_HP;
    private final int DMG;
    private final int SPEED;

    private final boolean IS_OPPONENT;
    private final MoveDetails[] MOVE_DETAILS;

    public BattleCharacterDetails(@NotNull String name, int maxHp, int dmg, int speed, boolean isOpponent,
                                  @NotNull MoveDetails[] moveDetails) {
        this.NAME = name;
        this.MAX_HP = maxHp;
        this.DMG = dmg;
        this.IS_OPPONENT = isOpponent;
        this.SPEED = speed;
        this.MOVE_DETAILS = moveDetails;

    }

    /**
     * Returns name of character
     *
     * @return name of character
     */

    public @NotNull String getName() {
        return this.NAME;
    }

    /**
     * Returns damage stat
     *
     * @return damage stat
     */

    public int getDmg() {
        return this.DMG;
    }

    /**
     * Returns Max HP stat
     *
     * @return Max HP stat
     */

    public int getMaxHp() {
        return this.MAX_HP;
    }

    /**
     * Returns speed stat
     *
     * @return speed stat
     */

    public int getSpeed() {
        return this.SPEED;
    }

    /**
     * Returns if this battle character is an opponent
     *
     * @return false iff this battle character is a party member
     */
    public boolean isOpponent() {
        return this.IS_OPPONENT;
    }

    /**
     * Return an array of objects that each represent a usable move
     *
     * @return first move details
     * @see MoveDetails
     */

    public @NotNull MoveDetails[] getMoveDetails() {
        return this.MOVE_DETAILS;
    }
}
