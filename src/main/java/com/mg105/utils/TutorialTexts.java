package com.mg105.utils;

import java.util.Arrays;
import java.util.List;

public class TutorialTexts {
    public static final String MOVED = "moved";
    public static final String ATTACKED = "attacked";
    public static final String USED_ITEM = "usedItem";

    public static final int HELPER_PANE_X = 420;
    public static final int HELPER_PANE_Y = 100;


    public static final int TEXT_DURATION1 = 3;
    public static final int TEXT_SIZE = 14;
    public static final int HELP_TIME = 250;

    public static final List<String> PHASES
        = Arrays.asList("...", "story", "tell move", "tell attack", "tell use item", "exit room", "hotkeys");

    public static final List<String> PHASES_TEXT = Arrays.asList("",
        TutorialTexts.STORY, TutorialTexts.TELL_MOVE, TutorialTexts.TELL_ATTACK, TutorialTexts.TELL_USE_ITEM, TutorialTexts.EXIT_ROOM, TutorialTexts.HOTKEYS);

    public static final String STORY = """
        Welcome to mountain climber. You must battle your way
        to the top of the mountain. Inside various rooms
        you will encounter enemies and find treasures.""";

    public static final String TELL_MOVE = "Move your character with the WASD keys.";

    public static final String TELL_ATTACK = "Use the battle button [f] to attack the enemies.";

    public static final String TELL_USE_ITEM = "Open treasure chests using the [e] key..";

    public static final String EXIT_ROOM = "Good luck on your journey!";

    public static final String CONTROLS = """
                   Hotkeys:  Press K for help. Press WASD to return to game.\s
        Game Controls:  Use WASD to move. Use battle key [f] to  \s
                         initiate battles. Open chests using [e].""";

    public static final String HOTKEYS = "Hint: Press [WASD] to return to the game. Press K for help!";

    public static final String DID_NOT_MOVE = """
                    You haven't completed the tutorial yet ...

                    You should return to the game and move you character with [WASD]!
                    (Pressing WASD in this window will return you to the game.)
                    """;

    public static final String DID_NOT_OPEN_CHEST = """
                    You haven't completed the tutorial yet ...

                    You should go open a chest! Do this using the [e] key.
                    (Pressing WASD in this window will return you to the game.)
                    """;

    public static final String DID_NOT_BATTLE = """
                    You haven't completed the tutorial yet ...

                    You should go fight a battle! Use [f] key to initiate a battle.
                    (Pressing WASD in this window will return you to the game.)
                    """;
}
