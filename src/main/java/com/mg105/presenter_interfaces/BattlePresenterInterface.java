package com.mg105.presenter_interfaces;

public interface BattlePresenterInterface {

    /**
     * Set the characters in the view.
     * @param partyNames the name strings of the party characters.
     * @param opponentNames the name strings of the opponent characters.
     */
    void setViewNames(String[] partyNames, String[] opponentNames);

    /**
     * Call on the BattleMenuInterface to update the displayed information for the given character.
     * @param targetName the name String of the character whose information displayed on the view needs to be updated.
     */
    void updateViewCharacter(String targetName);
}
