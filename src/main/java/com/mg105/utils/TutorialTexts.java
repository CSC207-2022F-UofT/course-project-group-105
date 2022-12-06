package com.mg105.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Constants for texts related to the tutorial
 */
public class TutorialTexts {
    /**
     * Constant string for move phase
     */
    public static final String MOVED = "moved";
    /**
     * Constant string for attack phase
     */
    public static final String ATTACKED = "attacked";
    /**
     * Constant string for using item phase
     */
    public static final String USED_ITEM = "usedItem";
    /**
     * Constant int for x size of helper pane
     */
    public static final int HELPER_PANE_X = 420;

    /**
     * Constant int for y size of helper pane
     */
    public static final int HELPER_PANE_Y = 100;

    /**
     * Constant int for text duration per phase
     */
    public static final int TEXT_DURATION1 = 3;

    /**
     * Constant int for size of the regular text
     */
    public static final int TEXT_SIZE = 14;

    /**
     * Constant int for size of the larger  text
     */
    public static final int TEXT_SIZE_LARGE = 20;

    /**
     * Constant int for text duration of helper text (not in seconds)
     */
    public static final int HELP_TIME = 250;

    /**
     * Constant array listing all the phases
     */
    public static final List<String> PHASES
        = Arrays.asList("...", "story", "tell move", "tell attack", "tell use item", "exit room", "hotkeys");

    /**
     * Constant array containing the actual text shown to the player
     */
    public static final List<String> PHASES_TEXT = Arrays.asList("",
        TutorialTexts.STORY, TutorialTexts.TELL_MOVE, TutorialTexts.TELL_ATTACK, TutorialTexts.TELL_USE_ITEM, TutorialTexts.EXIT_ROOM, TutorialTexts.HOTKEYS);

    /**
     * Constant string for story text
     */
    public static final String STORY = """
        Welcome to mountain climber. You must battle your way
        to the top of the mountain. Inside various rooms
        you will encounter enemies and find treasures.""";

    /**
     * Constant string for move text
     */
    public static final String TELL_MOVE = "Move your character with the WASD keys.";

    /**
     * Constant string for attack text
     */
    public static final String TELL_ATTACK = "Use the battle button [f] to attack the enemies.";

    /**
     * Constant string for open chest text
     */
    public static final String TELL_USE_ITEM = "Open treasure chests using the [e] key..";

    /**
     * Constant string for good luck text
     */
    public static final String EXIT_ROOM = "Good luck on your journey!";

    /**
     * Constant string for telling player the game controls
     */
    public static final String CONTROLS = """
                   Hotkeys:  Press K for help. Press WASD to return to game.\s
        Game Controls:  Use WASD to move. Use battle key [f] to  \s
                         initiate battles. Open chests using [e].""";

    /**
     * Constant string for telling the player the hotkeys
     */
    public static final String HOTKEYS = "Hint: Press [WASD] to return to the game. Press K for help!";

    /**
     * Constant string reminding player how to move
     */
    public static final String DID_NOT_MOVE = """
        You haven't completed the tutorial yet ...

        You should return to the game and move you character with [WASD]!
        (Pressing WASD in this window will return you to the game.)
        """;

    /**
     * Constant string for reminding player to open chest
     */
    public static final String DID_NOT_OPEN_CHEST = """
        You haven't completed the tutorial yet ...

        You should go open a chest! Do this using the [e] key.
        (Pressing WASD in this window will return you to the game.)
        """;

    /**
     * Constant string for reminding player to initiate a battle
     */
    public static final String DID_NOT_BATTLE = """
        You haven't completed the tutorial yet ...

        You should go fight a battle! Use [f] key to initiate a battle.
        (Pressing WASD in this window will return you to the game.)
        """;
}
