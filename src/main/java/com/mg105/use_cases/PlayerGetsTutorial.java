package com.mg105.use_cases;

import com.mg105.entities.GiveTutorial;
import com.mg105.use_cases.utils.TutorialTexts;

import java.util.List;

public class PlayerGetsTutorial {
    private List<String> tutorialPhases;  // Go through multiple phases of tutorial in order
    private int currentPhase;

    private GiveTutorial tutorial;

    public PlayerGetsTutorial(List<String> tutorialPhases, int currentPhase, GiveTutorial tutorial) {
        this.tutorialPhases = tutorialPhases;
        this.currentPhase = currentPhase;
        this.tutorial = tutorial;
    }

    /**
     * Check if all required actions performed by player
     *
     * @return whether all actions are complete
     */
    public boolean isComplete() {
        return tutorial.ActionPerformedGetter(TutorialTexts.moved) & tutorial.ActionPerformedGetter(TutorialTexts.attacked) & tutorial.ActionPerformedGetter(TutorialTexts.usedItem);
    }

    /**
     * Get names of all phases of tutorial
     *
     * @return the list of all tutorial phases
     */
    public List<String> allPhases() {
        return this.tutorialPhases;
    }

    /**
     * Get current phase of tutorial, which is first index of phase list
     *
     * @return the current phase of tutorial
     */
    public int currentPhase() {
        return this.currentPhase;
    }

    /**
     * Advance current phase by 1
     */
    public void nextPhase() {
        if (currentPhase < TutorialTexts.PHASES.size() - 1) {
            this.currentPhase++;
        }
    }

    public void setActionPerformed(String action) {
        this.tutorial.ActionPerformedSetter(action);
    }

    /**
     * Check if specific action has been performed
     *
     * @param action get if it has been performed yet
     */
    public boolean getActionPerformed(String action) {
        return this.tutorial.ActionPerformedGetter(action);
    }
}

