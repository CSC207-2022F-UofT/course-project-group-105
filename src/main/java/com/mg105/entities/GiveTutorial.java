package com.mg105.entities;


import com.mg105.utils.TutorialTexts;

public class GiveTutorial {

    private boolean moved = false;
    private boolean attacked = false;
    private boolean usedItem = false;

    public GiveTutorial(boolean moved, boolean attacked, boolean usedItem) {
        this.moved = moved;
        this.attacked = attacked;
        this.usedItem = usedItem;
    }

    /**
     * Set moved, attacked, usedItem to true if they have been performed by player
     * <p>
     * action should be a valid action
     *
     * @param action the action that has been performed
     */
    public void ActionPerformedSetter(String action) {
        if (action.equalsIgnoreCase(TutorialTexts.moved)) {
            this.moved = true;
        } else if (action.equalsIgnoreCase(TutorialTexts.attacked)) {
            this.attacked = true;
        } else if (action.equalsIgnoreCase(TutorialTexts.usedItem)) {
            this.usedItem = true;
        }

    }

    /**
     * Get if player has moved, attacked, and usedItem.
     * <p>
     * action should be a valid action
     *
     * @param action the action that is checked
     * @return whether the player has performed each action
     */
    public boolean ActionPerformedGetter(String action) {
        if (action.equalsIgnoreCase(TutorialTexts.moved)) {
            return this.moved;
        } else if (action.equalsIgnoreCase(TutorialTexts.attacked)) {
            return this.attacked;
        } else if (action.equalsIgnoreCase(TutorialTexts.usedItem)) {
            return this.usedItem;
        } else {
            return false;
        }
    }

}
