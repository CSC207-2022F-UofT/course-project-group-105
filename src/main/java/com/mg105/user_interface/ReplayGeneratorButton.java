package com.mg105.user_interface;

import com.mg105.interface_adapters.ReplayGeneratorInterpreter;
import javafx.event.ActionEvent;
import org.jetbrains.annotations.NotNull;
import com.mg105.interface_adapters.Toggler;
import javafx.event.EventHandler;


public class ReplayGeneratorButton implements EventHandler<javafx.event.ActionEvent> {
    private final @NotNull ReplayGeneratorInterpreter interpreter;
    private final @NotNull Toggler toggler;

    public ReplayGeneratorButton(@NotNull ReplayGeneratorInterpreter interpreter, @NotNull Toggler toggler) {
        this.interpreter = interpreter;
        this.toggler = toggler;
    }

    @Override
    public void handle(ActionEvent event) {
        interpreter.replayGenerateMap();
        toggler.toggle(Toggler.ToggleableComponent.LOSE_MENU);
    }

}
