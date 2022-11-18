package com.mg105.user_interface;

import com.mg105.controllers.TutorialTextController;
import com.mg105.utils.TutorialTexts;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.awt.*;


public class TutorialTextDisplay {
    public TutorialTextController tutorialControl = new TutorialTextController();

    public TutorialTextDisplay() {
    }

    /**
     * Check if specific action has been performed
     *
     * @param displayedText the String representing the current phase
     *
     * @return the actual text displayed to the user
     */
    public String showBottomText(String displayedText) {
        String tutorialText;
        int phase_idx = tutorialControl.tutorial.allPhases().indexOf(displayedText);
        tutorialText = TutorialTexts.PHASES_TEXT.get(phase_idx);
        return tutorialText;

    }

    /**
     * Return a label that will show at the bottom of the screen.
     *
     * @return the label
     */
    public Label tutorialLabel() {
        Label bottomText = new Label();
        bottomText.setFont(Font.font(TutorialTexts.textSize));
        return bottomText;
    }
}


