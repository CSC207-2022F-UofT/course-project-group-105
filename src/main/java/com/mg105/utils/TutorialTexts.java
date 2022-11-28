package com.mg105.utils;

import java.util.Arrays;
import java.util.List;

public class TutorialTexts {
    public static final String MOVED = "moved";
    public static final String ATTACKED = "attacked";
    public static final String USED_ITEM = "usedItem";

    public static final int TUTORIAL_TEXT_X = 660;
    public static final int TUTORIAL_TEXT_Y = 780;

    public static final int HELPER_PANE_X = 420;
    public static final int HELPER_PANE_Y = 100;


    public static final int TEXT_DURATION1 = 4;
    public static final int TEXT_SIZE = 14;
    public static final int HELP_TIME = 200;

    public static final List<String> PHASES
        = Arrays.asList("...", "story", "tell move", "tell attack", "tell use item", "exit room", "...");

    public static final List<String> PHASES_TEXT = Arrays.asList("",
        TutorialTexts.STORY, TutorialTexts.TELL_MOVE, TutorialTexts.TELL_ATTACK, TutorialTexts.TELL_USE_ITEM, TutorialTexts.EXIT_ROOM, "");

    public static final String STORY = """
        Welcome to mountain climber. You must battle your way
        to the top of the mountain. Inside various rooms
        you will encounter enemies and find treasures.""";

    public static final String TELL_MOVE = "Move your character with the WASD keys.";

    public static final String TELL_ATTACK = "Use the attack button to attack the enemy.";

    public static final String TELL_USE_ITEM = "Pick up health potions and use them to restore hp.";

    public static final String EXIT_ROOM = "Good luck on your journey!";

    public static final String CONTROLS = """
                   Hotkeys:  Press K for help. Press esc to open menu.\s
        Game Controls:  Use WASD to move. Use attack key [..] to attack. \s
                         Pickup items using [..]. Use item key [..] to use potions.""";

}
