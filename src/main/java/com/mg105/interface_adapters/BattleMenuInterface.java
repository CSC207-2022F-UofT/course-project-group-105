package com.mg105.interface_adapters;

public interface BattleMenuInterface {

    /**
     * Sets the names of the player and opponent characters participating in the active battle.
     *
     * @param playerNames array of name Strings representing player characters.
     * @param opponentNames array of name Strings representing opponents.
     */
    void setNames(String[] playerNames, String[] opponentNames);

    /**
     * Updates the display corresponding to the given affected character.
     *
     * @param character the character who needs to be updated on the screen.
     */
    void updateCharacter(String character);
}
