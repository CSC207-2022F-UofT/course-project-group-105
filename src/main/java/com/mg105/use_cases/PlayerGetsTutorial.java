package com.mg105.use_cases;

import com.mg105.entities.GiveTutorial;
import com.mg105.utils.TutorialTexts;

import java.util.List;

public class PlayerGetsTutorial {
    private Boolean completed = false;
    private List<String> tutorialPhases;  // Go through multiple phases of tutorial in order

    private GiveTutorial tutorial;

    public PlayerGetsTutorial(Boolean completed, List<String> tutorialPhases, GiveTutorial tutorial){
        this.completed = completed;
        this.tutorialPhases = tutorialPhases;
        this.tutorial = tutorial;
    }

    /**
     * Check if all required actions performed by player
     */
    public boolean isComplete(){
        return tutorial.ActionPerformedGetter("moved") & tutorial.ActionPerformedGetter("attacked")
            & tutorial.ActionPerformedGetter("usedItem");
    }

    /**
     * Get current phase of tutorial, which is first index of phase list
     */
    public String currentPhase(){
        return this.tutorialPhases.get(0);
    }

    /**
     * Action has been performed, so set it to true
     */
    public void setActionPerformed(String action){
        this.tutorial.ActionPerformedSetter(action);
    }

    /**
     * Check if specific action has been performed
     */
    public boolean getActionPerformed(String action){
        return this.tutorial.ActionPerformedGetter(action);
    }
}


