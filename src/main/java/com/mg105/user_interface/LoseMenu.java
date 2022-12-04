package com.mg105.user_interface;

import com.mg105.interface_adapters.MapGeneratorInterpreter;
import com.mg105.interface_adapters.inventory.InventoryController;
import com.mg105.use_cases.MapGenerator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

public class LoseMenu implements Toggleable {
    private final @NotNull Scene scene;
    private final @NotNull Pane layout;

    public LoseMenu(@NotNull ReplayGeneratorButton ReplayButton, InventoryController inventoryController) {
        Button generateMapButton = new Button("Replay The Game");
        //Clean Inventory
        inventoryController.removeAll();


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
