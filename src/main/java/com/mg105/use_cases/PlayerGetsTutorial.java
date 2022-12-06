package com.mg105.use_cases;

import com.mg105.entities.GiveTutorial;
import com.mg105.utils.TutorialTexts;

import java.util.List;

/**
 * Class for determining what phase of the tutorial the player is on, and changing the phase
 */
public class PlayerGetsTutorial {
    private final List<String> tutorialPhases;  // Go through multiple phases of tutorial in order
    private final GiveTutorial tutorial;
    private int currentPhase;


    /**
     * Constructor for PlayerGetsTutorial use case
     *
     * @param tutorialPhases a list of all possible phases in the tutorial
     * @param currentPhase   the integer representing what phase the player is on in the tutorial
     */
    public PlayerGetsTutorial(List<String> tutorialPhases, int currentPhase) {
        this.tutorialPhases = tutorialPhases;
        this.currentPhase = currentPhase;
        this.tutorial = new GiveTutorial(false, false, false);
    }

    /**
     * Check if all required actions performed by player
     *
     * @return whether all actions are complete
     */
    public boolean isComplete() {
        return tutorial.actionPerformedGetter(TutorialTexts.MOVED) & tutorial.actionPerformedGetter(TutorialTexts.ATTACKED) & tutorial.actionPerformedGetter(TutorialTexts.USED_ITEM);
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

    /**
     * Set the action to true if it has been performed
     *
     * @param action to set to performed
     */
    public void setActionPerformed(String action) {
        this.tutorial.actionPerformedSetter(action);
    }

    /**
     * Check if specific action has been performed
     *
     * @param action get if it has been performed yet
     * @return if the action has been performed
     */
    public boolean getActionPerformed(String action) {
        return this.tutorial.actionPerformedGetter(action);
    }
    
}

