package com.mg105.entities;

public class GiveTutorial {

    private boolean Moved = false;
    private boolean Attacked = false;
    private boolean UsedItem = false;

    public void ActionPerformedSetter(String action){
        if (action.equalsIgnoreCase("moved")){
            this.Moved = true;
        } else if (action.equalsIgnoreCase("attacked")){
            this.Attacked = true;
        } else if (action.equalsIgnoreCase("useditem")){
            this.UsedItem = true;
        }

    }

    public boolean ActionPerformedGetter(String action){
        if (action.equalsIgnoreCase("moved")){
            return this.Moved;
        } else if (action.equalsIgnoreCase("attacked")){
            return this.Attacked;
        } else if (action.equalsIgnoreCase("useditem")){
            return this.UsedItem;
        }
        else {
            return false;
        }
    }

}
