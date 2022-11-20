package com.mg105.interface_adapters;

import com.mg105.use_cases.CharacterMover;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * TODO
 */
public class InputInterpreter {
    private final @NotNull CharacterMover mover;

    /**
     * TODO
     *
     * @param mover
     */
    public InputInterpreter(@NotNull CharacterMover mover) {
        this.mover = mover;
    }

    /**
     * TODO
     *
     * @param key
     */
    public void interpret(String key) {
        switch (key) {
            case "w":
                mover.generateMapMoveBy(new Point(0, -1));
                break;

            case "a":
                mover.generateMapMoveBy(new Point(-1, 0));
                break;

            case "s":
                mover.generateMapMoveBy(new Point(0, 1));
                break;

            case "d":
                mover.generateMapMoveBy(new Point(1, 0));
                break;
        }
    }
}
