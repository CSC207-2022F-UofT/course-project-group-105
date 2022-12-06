package com.mg105.user_interface;

import com.mg105.utils.TutorialTexts;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

/**
 * A menu that is shown to the player after they have reached the final room
 */
public class WinMenu implements Toggleable {
    private final @NotNull Scene scene;

    /**
     * Shows a button that congratulates you and lets you replay the game
     *
     * @param ReplayButton the button responsible for replaying the game
     */
    public WinMenu(@NotNull ReplayGeneratorButton ReplayButton) {
        Label winLbl = new Label();
        winLbl.setText("Congratulations on reaching the final room!!");
        winLbl.setFont(Font.font(TutorialTexts.TEXT_SIZE_LARGE));
        StackPane.setAlignment(winLbl, Pos.TOP_CENTER);

        Button generateMapButton = new Button("You Won! Replay The Game");
        generateMapButton.setOnAction(ReplayButton);

        @NotNull Pane layout = new StackPane();
        layout.getChildren().add(generateMapButton);
        layout.getChildren().add(winLbl);
        scene = new Scene(layout, 600, 600);
    }

    /**
     * Get scene of the WinMenu
     *
     * @return the scene that WinMenu is in
     */
    @Override
    public @NotNull Scene getScene() {
        return scene;
    }

    /**
     * Toggle the WinMenu
     *
     * @param isVisible whether the menu is visible
     */
    @Override
    public void toggle(boolean isVisible) {
        // Does nothing, for now
    }
}
