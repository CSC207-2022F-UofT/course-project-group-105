package com.mg105.controllers;

import com.mg105.entities.GiveTutorial;
import com.mg105.use_cases.PlayerGetsTutorial;
import com.mg105.utils.TutorialTexts;

public class TutorialTextController {

    private final PlayerGetsTutorial tutorial = new PlayerGetsTutorial(TutorialTexts.PHASES, 0, new GiveTutorial(false, false, false));
    private boolean changeText;
    private boolean showControls = false;

    public TutorialTextController(boolean changeText) {
        this.changeText = changeText;
    }

    /**
     * Gets the current text that should be displayed
     *
     * @return what the text displayed at the bottom of the screen should be
     */
    public String bottomText() {
        return tutorial.allPhases().get(tutorial.currentPhase());
    }

    /**
     * Go to the next tutorial phase
     */
    public void nextPhase() { this.tutorial.nextPhase(); }

    /**
     * Return if text should start changing
     */
    public void setChangeText() { this.changeText = true; }

    /**
     * Return if text should start changing
     */
    public boolean changeText() { return this.changeText; }

    /**
     * Tell player the controls again if they forgot
     */
    public void setShowControls(boolean show) {
        this.showControls = show;
    }

    /**
     * Check if player should be shown controls again
     */
    public boolean getShowControls() { return this.showControls; }

    /**
     * Get an instance of the PlayerGetsTutorial use case
     *
     * @return the tutorial instance
     */
    public PlayerGetsTutorial getTutorial() {
        return this.tutorial;
    }

}

