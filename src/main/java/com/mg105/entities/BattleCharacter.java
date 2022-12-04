package com.mg105.entities;

/**
 * BattleCharacter is a class which contains the stats and other battle related information corresponding to a
 * specific character.
 */
public class BattleCharacter implements Comparable<BattleCharacter> {
    private int hp;
    private int maxHp;
    private final String name;
    private int dmg;
    private int speed;

    private final boolean isOpponent;

    private final Move[] moves = new Move[2];

    //Don't know how to handle the sprite instance

    public BattleCharacter(int health, String name, int attack, int speed, boolean isOpponent, Move first,
                           Move second) {
        this.hp = health;
        this.maxHp = health;
        this.name = name;
        this.dmg = attack;
        this.speed = speed;
        this.isOpponent = isOpponent;
        this.moves[0] = first;
        this.moves[1] = second;
    }

    /**
     * Returns the current health of the character.
     *
     * @return the number of hit points the character currently has.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns the maximum health of the character.
     *
     * @return the maximum number of hit points the character has.
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Returns the name of the character.
     *
     * @return the name of the BattleCharacter as a String.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current damage stat of the character.
     *
     * @return the attack damage the character currently has.
     */
    public int getDmg() {
        return dmg;
    }

    /**
     * Returns the speed of the character.
     *
     * @return the speed of the BattleCharacter.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Returns whether the character is an opponent or not.
     *
     * @return whether the BattleCharacter is an opponent or not.
     */
    public boolean isOpponent() {
        return isOpponent;
    }

    /**
     * Returns the character's first move.
     *
     * @return the first Move of the BattleCharacter.
     */
    public Move getMoveOne() {
        return moves[0];
    }

    /**
     * Returns the character's second move.
     *
     * @return the second Move of the BattleCharacter.
     */
    public Move getMoveTwo() {
        return moves[1];
    }

    /**
     * Updates the character's current health by the given amount.
     * If the amount causes the character's health to go below 0, set it to 0.
     * If the amount heals the character beyond their max, set it to the max.
     *
     * @param healthChange the amount by which the BattleCharacter's hp should change.
     */
    public void modifyHealth(int healthChange) {
        this.hp += healthChange;

        // If character faints, reset hp to 0
        if (this.hp <= 0) {
            this.hp = 0;
        } else if (this.hp >= this.maxHp) {
            this.hp = this.maxHp;
        }
    }

    /**
     * Updates the character's maximum health by the given amount.
     * Also modifies the character's current health accordingly.
     *
     * @param maximumChange the amount by which the BattleCharacter's maximum hp should change.
     */
    public void modifyMaxHp(int maximumChange) {
        this.maxHp += maximumChange;
        this.hp += maximumChange;
    }

    /**
     * Updates the character's attack damage by the given amount.
     *
     * @param damageChange the amount by which the BattleCharacter's damage should change.
     */
    public void modifyDamage(int damageChange) {
        this.dmg += damageChange;
    }

    /**
     * Updates the character's speed by the given amount.
     * Only used outside active Battle.
     *
     * @param speedChange the amount by which the BattleCharacter's speed should change.
     */
    public void modifySpeed(int speedChange) {
        this.speed += speedChange;
    }

    /**
     * Allow BattleCharacters to be compared with each other based on their speed stat.
     */
    @Override
    public int compareTo(BattleCharacter other) {
        return Integer.compare(this.speed, other.speed);
    }
}
