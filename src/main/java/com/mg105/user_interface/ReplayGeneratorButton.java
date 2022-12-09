package com.mg105.user_interface;

import com.mg105.interface_adapters.ReplayGeneratorInterpreter;
import com.mg105.interface_adapters.Toggler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.jetbrains.annotations.NotNull;


/**
 * ReplayGeneratorButton acts as the event handler for the actual JavaFx button that will replay the game.
 */
public class ReplayGeneratorButton implements EventHandler<javafx.event.ActionEvent> {
    private final @NotNull ReplayGeneratorInterpreter interpreter;
    private final @NotNull Toggler toggler;
    private final @NotNull Toggler.ToggleableComponent componentToToggle;

    /**
     * Create a new ReplayGeneratorButton.
     *
     * @param interpreter       the interpreter for the replay generator button.
     * @param toggler           the toggler used to close the user interface once pressed.
     * @param componentToToggle the component that needs to be toggled
     */
    public ReplayGeneratorButton(@NotNull ReplayGeneratorInterpreter interpreter, @NotNull Toggler toggler, Toggler.@NotNull ToggleableComponent componentToToggle) {
        this.interpreter = interpreter;
        this.toggler = toggler;
        this.componentToToggle = componentToToggle;
    }

    /**
     * this method is called when the button is pressed. It passes control to the appropriate interpreter.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        interpreter.replayGenerateMap();
        toggler.toggle(componentToToggle);
    }

}
