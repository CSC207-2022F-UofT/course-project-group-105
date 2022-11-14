import com.mg105.entities.GiveTutorial;
import com.mg105.use_cases.PlayerGetsTutorial;
import com.mg105.utils.TutorialTexts;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TutorialTest {
    @Test
    void testActionGetterSetter(){
        GiveTutorial newTutorial = new GiveTutorial(false, false, false);
        newTutorial.ActionPerformedSetter("attacked");
        assertFalse(newTutorial.ActionPerformedGetter("moved"));
        assertTrue(newTutorial.ActionPerformedGetter("attacked"));
        assertFalse(newTutorial.ActionPerformedGetter("moved"));
    }

    @Test
    void testGetPhases() {
        PlayerGetsTutorial tutorialPlayer
            = new PlayerGetsTutorial(false, TutorialTexts.PHASES,
            new GiveTutorial(false, false, false));
        List<String> expectedPhases = Arrays.asList("story", "game intro", "tell move", "tell attack", "tell use item", "exit room");

        assert(Objects.equals(tutorialPlayer.currentPhase(), expectedPhases.get(0)));
    }

    @Test
    void testTutorialComplete(){
        PlayerGetsTutorial tutorialPlayer1
            = new PlayerGetsTutorial(false, TutorialTexts.PHASES,
            new GiveTutorial(false, false, false));
        PlayerGetsTutorial tutorialPlayer2
            = new PlayerGetsTutorial(false, TutorialTexts.PHASES,
            new GiveTutorial(false, false, true));
        tutorialPlayer2.setActionPerformed("moved");
        tutorialPlayer2.setActionPerformed("attacked");

        assertFalse(tutorialPlayer1.isComplete());
        assertTrue(tutorialPlayer2.isComplete());
    }
}
