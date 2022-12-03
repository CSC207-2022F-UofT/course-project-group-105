package com.mg105.use_cases.outputds;

import org.jetbrains.annotations.NotNull;

/**
 * A struct like class that represents a BattleCharacter
 */

public class BattleCharacterDetails {

    @NotNull private final String name;
    private final int maxHp;
    private final int dmg;
    private final int speed;

    private final boolean isOpponent;
    @NotNull private final MoveDetails[] moveDetails;

    public BattleCharacterDetails(@NotNull String name, int maxHp, int dmg, int speed, boolean isOpponent,
                                  @NotNull MoveDetails[] moveDetails) {
        this.name = name;
        this.maxHp = maxHp;
        this.dmg = dmg;
        this.isOpponent = isOpponent;
        this.speed = speed;
        this.moveDetails = moveDetails;

    }

    /**
     * Returns name of character
     *
     * @return name of character
     */

    public @NotNull String getName() {
        return this.name;
    }

    /**
     * Returns damage stat
     *
     * @return damage stat
     */

    public int getDmg() {
        return this.dmg;
    }

    /**
     * Returns Max HP stat
     *
     * @return Max HP stat
     */

    public int getMaxHp() {
        return this.maxHp;
    }

    /**
     * Returns speed stat
     *
     * @return speed stat
     */

    public int getSpeed() {
        return this.speed;
    }

    /**
     * Returns if this battle character is an opponent
     *
     * @return false iff this battle character is a party member
     */
    public boolean isOpponent() {
        return this.isOpponent;
    }

    /**
     * Return an array of objects that each represent a usable move
     *
     * @return first move details
     * @see MoveDetails
     */

    public @NotNull MoveDetails[] getMoveDetails() {
        return this.moveDetails;
    }
}
