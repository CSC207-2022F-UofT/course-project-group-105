package com.mg105.user_interface;

import com.mg105.interface_adapters.InputInterpreter;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;

/**
 * InputListener takes keyboard inputs and passes them on to an appropriate interpreter.
 *
 * This class is given to JavaFX and is only called by it.
 */
public class InputListener implements EventHandler<KeyEvent> {
    private final @NotNull InputInterpreter interpreter;

    /**
     * Create an InputListener.
     *
     * @param interpreter the interpreter responsible for interpreting the keyboard inputs.
     */
    public InputListener(@NotNull InputInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Pass the keyevent onto the input interpreter.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(KeyEvent event) {
        interpreter.interpret(event.getCharacter());
    }
}
