package com.mg105.entities;


public class GiveTutorial {

    private boolean moved = false;
    private boolean attacked = false;
    private boolean usedItem = false;

    /**
     * Set moved, attacked, usedItem to true if they have been performed by player
     *
     * action should be a valid action
     *
     * @param action  the action that has been performed
     */
    public void ActionPerformedSetter(String action){
        if (action.equalsIgnoreCase("moved")){
            this.moved = true;
        } else if (action.equalsIgnoreCase("attacked")){
            this.attacked = true;
        } else if (action.equalsIgnoreCase("useditem")){
            this.usedItem = true;
        }

    }

    /**
     * Set moved, attacked, usedItem to true if they have been performed by player
     *
     * action should be a valid action
     *
     * @param action  the action that is checked
     *
     * @return whether the player has performed each action
     */
    public boolean ActionPerformedGetter(String action){
        if (action.equalsIgnoreCase("moved")){
            return this.moved;
        } else if (action.equalsIgnoreCase("attacked")){
            return this.attacked;
        } else if (action.equalsIgnoreCase("useditem")){
            return this.usedItem;
        }
        else {
            return false;
        }
    }

}
