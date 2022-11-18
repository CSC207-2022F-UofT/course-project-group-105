package com.mg105.user_interface;

import com.mg105.interface_adapters.MapGeneratorInterpreter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.jetbrains.annotations.NotNull;

/**
 * MapGeneratorButton acts as the event handler for the actual JavaFX button that will generate a map.
 */
public class MapGeneratorButton implements EventHandler<ActionEvent> {
    private final @NotNull MapGeneratorInterpreter interpreter;

    /**
     * Create a new MapGeneratorButton.
     *
     * @param interpreter the interpreter for the map generator button
     */
    public MapGeneratorButton(@NotNull MapGeneratorInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * This method is called when the button is pressed. It passes control to the appropriate interpreter.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        interpreter.generateMap();
    }
}
