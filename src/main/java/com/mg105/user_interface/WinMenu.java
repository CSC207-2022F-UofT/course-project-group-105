package com.mg105.user_interface;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

public class WinMenu implements Toggleable {
    private final @NotNull Scene scene;
    private final @NotNull Pane layout;

    public WinMenu(@NotNull ReplayGeneratorButton ReplayButton) {
        Button generateMapButton = new Button("You Won! Replay The Game");
        //Clean Inventory
        generateMapButton.setOnAction(ReplayButton);
        layout = new StackPane();
        layout.getChildren().add(generateMapButton);
        scene = new Scene(layout, 600, 600);
    }

    @Override
    public @NotNull Scene getScene() {
        return scene;
    }

    @Override
    public void toggle(boolean isVisible) {
        // Does nothing, for now
    }
}
