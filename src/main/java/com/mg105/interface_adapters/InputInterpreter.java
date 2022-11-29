package com.mg105.interface_adapters;

import com.mg105.controllers.TutorialTextController;
import com.mg105.use_cases.CharacterMover;
import com.mg105.user_interface.SceneController;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * InputInterpreter takes in keyboard inputs and distributes them to their appropriate use cases.
 */
public class InputInterpreter {
    private final @NotNull CharacterMover mover;
    private final @NotNull Toggler toggler;

    private final @NotNull TutorialTextController textChanger;

    /**
     * Create a new InputInterpreter that translates keyboard inputs to appropriate function invocations.
     *
     * @param mover the character mover.
     */
    public InputInterpreter(@NotNull CharacterMover mover, @NotNull Toggler toggler,
                            @NotNull TutorialTextController textChanger) {
        this.mover = mover;
        this.toggler = toggler;
        this.textChanger = textChanger;
    }

    /**
     * Interpret key being pressed as an action.
     *
     * @param key the key being pressed as a string.
     */
    public void interpret(String key) {
        switch (toggler.getCurrentComponent()) {
            case MAP -> {
                switch (key) {
                    case "w" -> mover.generateMapMoveBy(new Point(0, -1));
                    case "a" -> mover.generateMapMoveBy(new Point(-1, 0));
                    case "s" -> mover.generateMapMoveBy(new Point(0, 1));
                    case "d" -> mover.generateMapMoveBy(new Point(1, 0));

                    case "k" -> {
                        toggler.toggle(Toggler.ToggleableComponent.TUTORIAL);
                        textChanger.setShowControls(true);

                    }
                    case "t" -> {
                        toggler.toggle(Toggler.ToggleableComponent.TUTORIAL);
                        textChanger.setChangeText();
                    }
                }
            }
            case TUTORIAL -> {
                switch (key) {
                    case "w", "a", "s", "d" -> {
                        toggler.toggle(Toggler.ToggleableComponent.TUTORIAL);
                        textChanger.setChangeText();
                    }
                    case "k" -> textChanger.setShowControls(true);
                }
            }
        }

    }
}
