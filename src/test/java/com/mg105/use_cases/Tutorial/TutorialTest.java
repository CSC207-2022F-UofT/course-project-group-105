package com.mg105.use_cases.Tutorial;

import com.mg105.entities.GiveTutorial;
import com.mg105.use_cases.PlayerGetsTutorial;
import com.mg105.user_interface.TutorialTextDisplay;
import com.mg105.utils.TutorialTexts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TutorialTest {
    @Test
    void testActionGetterSetter() {
        GiveTutorial newTutorial = new GiveTutorial(false, false, false);
        newTutorial.ActionPerformedSetter(TutorialTexts.ATTACKED);

        assertFalse(newTutorial.ActionPerformedGetter(TutorialTexts.MOVED));
        assertTrue(newTutorial.ActionPerformedGetter(TutorialTexts.ATTACKED));
        assertFalse(newTutorial.ActionPerformedGetter(TutorialTexts.USED_ITEM));
    }

    @Test
    void testAdvancePhase() {
        PlayerGetsTutorial tutorialPlayer
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0,
            new GiveTutorial(false, false, false));

        assertEquals(tutorialPlayer.currentPhase(), 0);
        tutorialPlayer.nextPhase();
        assertEquals(tutorialPlayer.currentPhase(), 1);

    }

    @Test
    void testTutorialComplete() {
        PlayerGetsTutorial tutorialPlayer1
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0,
            new GiveTutorial(false, false, false));
        PlayerGetsTutorial tutorialPlayer2
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0,
            new GiveTutorial(false, false, true));
        tutorialPlayer2.setActionPerformed(TutorialTexts.MOVED);
        tutorialPlayer2.setActionPerformed(TutorialTexts.ATTACKED);

        assertFalse(tutorialPlayer1.isComplete());
        assertTrue(tutorialPlayer2.isComplete());
    }

    @Test
    void testTutorialBottomText(){
        TutorialTextDisplay tutorialDisplay = new TutorialTextDisplay();
        tutorialDisplay.getController().nextPhase();
        tutorialDisplay.getController().nextPhase();
        int phase_num = tutorialDisplay.getController().getTutorial().currentPhase();
        String phase = tutorialDisplay.getController().getTutorial().allPhases().get(phase_num);
        String tutorialText = tutorialDisplay.showBottomText(phase);
        String expected = "Move your character with the arrow keys.";

        assertEquals(tutorialText, expected);
    }
}
