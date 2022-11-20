package com.mg105.interface_adapters;

import com.mg105.use_cases.CharacterMover;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * InputInterpreter takes in keyboard inputs and distributes them to their appropriate use cases.
 */
public class InputInterpreter {
    private final @NotNull CharacterMover mover;

    /**
     * Create a new InputInterpreter that translates keyboard inputs to appropriate function invocations.
     *
     * @param mover the character mover.
     */
    public InputInterpreter(@NotNull CharacterMover mover) {
        this.mover = mover;
    }

    /**
     * Interpret key being pressed as an action.
     *
     * @param key the key being pressed as a string.
     */
    public void interpret(String key) {
        switch (key) {
            case "w" -> mover.generateMapMoveBy(new Point(0, -1));
            case "a" -> mover.generateMapMoveBy(new Point(-1, 0));
            case "s" -> mover.generateMapMoveBy(new Point(0, 1));
            case "d" -> mover.generateMapMoveBy(new Point(1, 0));
        }
    }
}
