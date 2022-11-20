package com.mg105.entities;

/**
 * Move is a class which contains the combat properties and name of a specific move used by a specific character.
 */
public class Move {
    private final int healthChange;
    private final int damageChange;
    private final boolean isFriendly;

    private final String name;

    public Move(int healthDamage, int damageChange, String name, boolean isFriendly) {
        this.healthChange = healthDamage;
        this.damageChange = damageChange;
        this.name = name;
        this.isFriendly = isFriendly;
    }

    /**
     * Returns the amount the current health of the target would change if this move is used on them.
     *
     * @return the health change of this Move.
     */
    public int getHealthChange() {
        return this.healthChange;
    }

    //public String getName()

    /**
     * Returns the amount the current damage stat of the target would change if this move is used on them.
     *
     * @return the damage change of this Move.
     */
    public int getDamageChange() {
        return this.damageChange;
    }

    /**
     * Returns the name of this move.
     *
     * @return the name of this Move as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether this move is to be used on a character on the same team as the caster or not.
     *
     * @return whether this Move should target a BattleCharacter on the same team (true) or on the other team (false).
     */
    public boolean isFriendly() {
        return isFriendly;
    }
}
