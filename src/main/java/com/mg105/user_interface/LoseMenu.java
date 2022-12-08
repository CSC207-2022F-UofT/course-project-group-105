package com.mg105.user_interface;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

/**
 * LoseMenu is displayed when the player loses the game.
 */
public class LoseMenu implements Toggleable {
    private final @NotNull Scene scene;

    /**
     * Create a new LoseMenu
     *
     * @param replayButton the button that performs the replay action
     */
    public LoseMenu(@NotNull ReplayGeneratorButton replayButton) {
        Button generateMapButton = new Button("Replay The Game");
        //Clean Inventory
        generateMapButton.setOnAction(replayButton);
        @NotNull Pane layout = new StackPane();
        layout.getChildren().add(generateMapButton);
        scene = new Scene(layout, 600, 600);
    }

    @Override
    public @NotNull Scene getScene() {
        return scene;
    }

    @Override
    public void toggle(boolean isVisible) {
        // Does nothing
    }
}
