package com.mg105.user_interface;

import com.mg105.interface_adapters.ReplayGeneratorInterpreter;
import javafx.event.ActionEvent;
import org.jetbrains.annotations.NotNull;
import com.mg105.interface_adapters.Toggler;
import javafx.event.EventHandler;


/**
 * ReplayGeneratorButton acts as the event handler for the actual JavaFx button that will replay the game.
 */
public class ReplayGeneratorButton implements EventHandler<javafx.event.ActionEvent> {
    private final @NotNull ReplayGeneratorInterpreter interpreter;
    private final @NotNull Toggler toggler;

    /**
     * Create a new ReplayGeneratorButton.
     *
     * @param interpreter the interpreter for the replay generator button.
     * @param toggler the toggler used to close the user interface once pressed.
     */
    public ReplayGeneratorButton(@NotNull ReplayGeneratorInterpreter interpreter, @NotNull Toggler toggler) {
        this.interpreter = interpreter;
        this.toggler = toggler;
    }

    /**
     * this method is called when the button is pressed. It passes control to the appropriate interpreter.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event) {
        interpreter.replayGenerateMap();
        toggler.toggle(Toggler.ToggleableComponent.LOSE_MENU);
    }

}
