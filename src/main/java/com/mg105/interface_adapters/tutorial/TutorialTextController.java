package com.mg105.interface_adapters.tutorial;

import com.mg105.use_cases.PlayerGetsTutorial;
import com.mg105.utils.TutorialTexts;

import java.util.List;

/**
 * A controller that converts phase number into displayed text
 */
public class TutorialTextController {

    private final PlayerGetsTutorial tutorial = new PlayerGetsTutorial(TutorialTexts.PHASES, 0);
    private boolean changeText;
    private boolean showControls = false;

    /**
     * A constructor for the tutorial controller
     *
     * @param changeText whether to initially change the text.
     */
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
    public void nextPhase() {
        this.tutorial.nextPhase();
    }

    /**
     * Make text start changing
     */
    public void setChangeText() {
        this.changeText = !this.changeText;
    }

    /**
     * Check if tutorial phases should advance
     *
     * @return if text should start changing
     */
    public boolean changeText() {
        return this.changeText;
    }

    /**
     * Check if player should be shown controls again
     *
     * @return whether player should be shown the control texts
     */
    public boolean getShowControls() {
        return this.showControls;
    }

    /**
     * Tell player the controls again
     *
     * @param show the text on the screen when true
     */
    public void setShowControls(boolean show) {
        this.showControls = show;
    }

    /**
     * Get an instance of the PlayerGetsTutorial use case
     *
     * @return the tutorial instance
     */
    public PlayerGetsTutorial getTutorial() {
        return this.tutorial;
    }

    /**
     * Get if the tutorial is complete, changes text if it is
     *
     * @return if the tutorial is complete
     */
    public boolean isComplete() {
        return tutorial.isComplete();
    }

    /**
     * Returns if the action has been performed
     *
     * @param action that is checked
     * @return if the specified action has been performed
     */
    public boolean getActionPerformed(String action) {
        return !tutorial.getActionPerformed(action);
    }

    /**
     * Get names of all phases of tutorial
     *
     * @return the list of all tutorial phases
     */
    public List<String> allPhases() {
        return tutorial.allPhases();
    }

}

