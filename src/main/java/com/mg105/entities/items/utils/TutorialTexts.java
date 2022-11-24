package com.mg105.entities.items.utils;

import java.util.Arrays;
import java.util.List;

public class TutorialTexts {
    public static final String moved = "moved";
    public static final String attacked = "attacked";
    public static final String usedItem = "usedItem";

    public static final int tutorialTextX = 150;
    public static final int tutorialTextY = 550;

    public static final int TEXT_DURATION1 = 5;
    public static final int TEXT_DURATION2 = 10;

    public static final String textFont = "Verdana";
    public static final int textSize = 18;

    public static final List<String> PHASES
        = Arrays.asList("", "story", "tell move", "tell attack", "tell use item", "exit room");

    public static final List<String> PHASES_TEXT = Arrays.asList("",
        TutorialTexts.STORY, TutorialTexts.TELL_MOVE, TutorialTexts.TELL_ATTACK, TutorialTexts.TELL_USE_ITEM, TutorialTexts.EXIT_ROOM);

    public static final String STORY = "Battle your way to the top of the mountain!\n" +
        "Inside various rooms, you will encounter enemies and find treasures.";

    public static final String TELL_MOVE = "Move your character with the arrow keys.";

    public static final String TELL_ATTACK = "Use the attack button to attack the enemy.";

    public static final String TELL_USE_ITEM = "Pick up health potions and use them to restore hp.";

    public static final String EXIT_ROOM = "Good luck on your journey!";

}
