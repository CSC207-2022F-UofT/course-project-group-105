package com.mg105.user_interface;

import com.mg105.interface_adapters.tutorial.TutorialTextController;
import com.mg105.utils.TutorialTexts;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Returns the actual text that is displayed to the player
 */
public class TutorialTextDisplay {
    private final TutorialTextController tutorialControl = new TutorialTextController(false);

    /**
     * Constructor for a new TutorialTextDisplay ui
     */
    public TutorialTextDisplay() {
    }

    /**
     * Check if specific action has been performed
     *
     * @param displayedText the String representing the current phase
     * @return the actual text displayed to the user
     */
    public String showBottomText(String displayedText) {
        String tutorialText;
        int phase_idx = tutorialControl.allPhases().indexOf(displayedText);
        tutorialText = TutorialTexts.PHASES_TEXT.get(phase_idx);
        return tutorialText;

    }

    /**
     * String for reminding user of controls
     *
     * @return the actual text displayed to the user
     */
    public String showControlsText() {
        return TutorialTexts.CONTROLS;

    }

    /**
     * Return a label that will show at the bottom of the screen.
     *
     * @return the label
     */
    public Label tutorialLabel() {
        Label bottomText = new Label();
        bottomText.setFont(Font.font(TutorialTexts.TEXT_SIZE));
        return bottomText;
    }

    /**
     * Get a tutorial controller which is used to switch text
     *
     * @return instance of the controller
     */
    public TutorialTextController getController() {
        return this.tutorialControl;
    }
}


