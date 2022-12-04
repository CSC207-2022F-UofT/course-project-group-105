package com.mg105.entities;

import com.mg105.utils.TutorialTexts;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GiveTutorialTest {
    @Test
    void testActionSetterGetter() {
        GiveTutorial newTutorial = new GiveTutorial(false, false, false);
        newTutorial.actionPerformedSetter(TutorialTexts.MOVED);
        GiveTutorial newTutorial2 = new GiveTutorial(false, false, false);
        newTutorial2.actionPerformedSetter(TutorialTexts.ATTACKED);
        GiveTutorial newTutorial3 = new GiveTutorial(false, false, false);
        newTutorial3.actionPerformedSetter(TutorialTexts.USED_ITEM);

        assertTrue(newTutorial.actionPerformedGetter(TutorialTexts.MOVED));
        assertFalse(newTutorial.actionPerformedGetter(TutorialTexts.ATTACKED));
        assertFalse(newTutorial.actionPerformedGetter(TutorialTexts.USED_ITEM));

        assertFalse(newTutorial2.actionPerformedGetter(TutorialTexts.MOVED));
        assertTrue(newTutorial2.actionPerformedGetter(TutorialTexts.ATTACKED));
        assertFalse(newTutorial2.actionPerformedGetter(TutorialTexts.USED_ITEM));

        assertFalse(newTutorial3.actionPerformedGetter(TutorialTexts.MOVED));
        assertFalse(newTutorial3.actionPerformedGetter(TutorialTexts.ATTACKED));
        assertTrue(newTutorial3.actionPerformedGetter(TutorialTexts.USED_ITEM));
    }
}
