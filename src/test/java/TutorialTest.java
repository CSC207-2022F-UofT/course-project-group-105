import com.mg105.entities.GiveTutorial;
import com.mg105.use_cases.PlayerGetsTutorial;
import com.mg105.user_interface.TutorialTextDisplay;
import com.mg105.utils.TutorialTexts;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TutorialTest {
    @Test
    void testActionGetterSetter() {
        GiveTutorial newTutorial = new GiveTutorial(false, false, false);
        newTutorial.ActionPerformedSetter(TutorialTexts.attacked);
        assertFalse(newTutorial.ActionPerformedGetter(TutorialTexts.moved));
        assertTrue(newTutorial.ActionPerformedGetter(TutorialTexts.attacked));
        assertFalse(newTutorial.ActionPerformedGetter(TutorialTexts.usedItem));
    }

    @Test
    void testAdvancePhase() {
        PlayerGetsTutorial tutorialPlayer
            = new PlayerGetsTutorial(TutorialTexts.PHASES, 0,
            new GiveTutorial(false, false, false));
        List<String> expectedPhases = Arrays.asList("", "story", "tell move", "tell attack", "tell use item", "exit room");

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
        tutorialPlayer2.setActionPerformed(TutorialTexts.moved);
        tutorialPlayer2.setActionPerformed(TutorialTexts.attacked);

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
