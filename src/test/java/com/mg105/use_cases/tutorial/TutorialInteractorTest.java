package com.mg105.use_cases.tutorial;

import com.mg105.use_cases.PlayerGetsTutorial;
import com.mg105.user_interface.TutorialTextDisplay;
import com.mg105.utils.TutorialTexts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TutorialInteractorTest {
    @Test
    void testAdvancePhase() {
        PlayerGetsTutorial tutorialPlayer
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0);

        assertEquals(tutorialPlayer.currentPhase(), 0);
        tutorialPlayer.nextPhase();
        assertEquals(tutorialPlayer.currentPhase(), 1);
        tutorialPlayer.nextPhase();
        assertEquals(tutorialPlayer.currentPhase(), 2);
        tutorialPlayer.nextPhase();
        assertEquals(tutorialPlayer.currentPhase(), 3);
    }

    @Test
    void testTutorialComplete() {
        PlayerGetsTutorial tutorialPlayer1
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0);
        PlayerGetsTutorial tutorialPlayer2
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0);
        tutorialPlayer2.setActionPerformed(TutorialTexts.MOVED);
        tutorialPlayer2.setActionPerformed(TutorialTexts.ATTACKED);
        tutorialPlayer2.setActionPerformed(TutorialTexts.USED_ITEM);

        assertFalse(tutorialPlayer1.getActionPerformed(TutorialTexts.MOVED));
        assertFalse(tutorialPlayer1.getActionPerformed(TutorialTexts.ATTACKED));
        assertFalse(tutorialPlayer1.getActionPerformed(TutorialTexts.USED_ITEM));
        assertFalse(tutorialPlayer1.isComplete());

        assertTrue(tutorialPlayer2.getActionPerformed(TutorialTexts.MOVED));
        assertTrue(tutorialPlayer2.getActionPerformed(TutorialTexts.ATTACKED));
        assertTrue(tutorialPlayer2.getActionPerformed(TutorialTexts.USED_ITEM));
        assertTrue(tutorialPlayer2.isComplete());
    }

    @Test
    void testTutorialBottomText() {
        TutorialTextDisplay tutorialDisplay = new TutorialTextDisplay();
        int phase_num = tutorialDisplay.getController().getTutorial().currentPhase();
        String phase = tutorialDisplay.getController().getTutorial().allPhases().get(phase_num);
        String tutorialText = tutorialDisplay.showBottomText(phase);
        String expected = "";

        TutorialTextDisplay tutorialDisplay2 = new TutorialTextDisplay();
        tutorialDisplay2.getController().nextPhase();
        int phase_num2 = tutorialDisplay2.getController().getTutorial().currentPhase();
        String phase2 = tutorialDisplay2.getController().getTutorial().allPhases().get(phase_num2);
        String tutorialText2 = tutorialDisplay2.showBottomText(phase2);

        TutorialTextDisplay tutorialDisplay3 = new TutorialTextDisplay();
        tutorialDisplay3.getController().nextPhase();
        tutorialDisplay3.getController().nextPhase();
        int phase_num3 = tutorialDisplay3.getController().getTutorial().currentPhase();
        String phase3 = tutorialDisplay3.getController().getTutorial().allPhases().get(phase_num3);
        String tutorialText3 = tutorialDisplay3.showBottomText(phase3);

        TutorialTextDisplay tutorialDisplay4 = new TutorialTextDisplay();
        tutorialDisplay4.getController().nextPhase();
        tutorialDisplay4.getController().nextPhase();
        tutorialDisplay4.getController().nextPhase();
        int phase_num4 = tutorialDisplay4.getController().getTutorial().currentPhase();
        String phase4 = tutorialDisplay4.getController().getTutorial().allPhases().get(phase_num4);
        String tutorialText4 = tutorialDisplay4.showBottomText(phase4);

        TutorialTextDisplay tutorialDisplay5 = new TutorialTextDisplay();
        tutorialDisplay5.getController().nextPhase();
        tutorialDisplay5.getController().nextPhase();
        tutorialDisplay5.getController().nextPhase();
        tutorialDisplay5.getController().nextPhase();
        int phase_num5 = tutorialDisplay5.getController().getTutorial().currentPhase();
        String phase5 = tutorialDisplay5.getController().getTutorial().allPhases().get(phase_num5);
        String tutorialText5 = tutorialDisplay5.showBottomText(phase5);

        assertEquals(tutorialText, expected);
        assertEquals(tutorialText2, TutorialTexts.STORY);
        assertEquals(tutorialText3, TutorialTexts.TELL_MOVE);
        assertEquals(tutorialText4, TutorialTexts.TELL_ATTACK);
        assertEquals(tutorialText5, TutorialTexts.TELL_USE_ITEM);
    }
}
