package com.mg105.user_interface;

import com.mg105.interface_adapters.InputInterpreter;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;

/**
 * TODO
 */
public class InputListener implements EventHandler<KeyEvent> {
    private final @NotNull InputInterpreter interpreter;

    /**
     * TODO
     *
     * @param interpreter
     */
    public InputListener(@NotNull InputInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * TODO
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(KeyEvent event) {
        interpreter.interpret(event.getCharacter());
    }
}
