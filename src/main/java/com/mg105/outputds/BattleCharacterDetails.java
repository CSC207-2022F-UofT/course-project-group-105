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
    private final MoveDetails FIRST;
    private final MoveDetails SECOND;

    public BattleCharacterDetails(@NotNull String name, int maxHp, int dmg, int speed,
                                  @NotNull MoveDetails first, @NotNull MoveDetails second) {
        this.NAME = name;
        this.MAX_HP = maxHp;
        this.DMG = dmg;
        this.SPEED = speed;
        this.FIRST = first;
        this.SECOND = second;

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
     * Return first move details
     *
     * @return first move details
     * @see MoveDetails
     */

    public @NotNull MoveDetails getFirst() {
        return this.FIRST;
    }

    /**
     * Return second move details
     *
     * @return second move details
     * @see MoveDetails
     */

    public @NotNull MoveDetails getSecond() {
        return this.SECOND;
    }
}
