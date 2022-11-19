package com.mg105.user_interface.inventory_display;

import com.mg105.user_interface.AlertBox;
import com.mg105.view_interfaces.InventoryView;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

// just for demonstration purposes (needs a lot more changes before it can actually be displayed)
public class InventoryDisplay implements InventoryView {

    // Controller Attribute

    private final static String[] partyConstants = {"A", "B", "C"};

    private String memberSelected = "A";

    private final VBox itemsDisplay = new VBox(10);

    private final HashMap<String, HBox> itemNameToInfo = new HashMap<>();
    private final Stage window = new Stage();

    // Should have function that gets details of the inventory
    public void display() {
        // Makes this modal and blocks user from performing events on other modals
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Inventory");
        // Should probably be constants
        window.setWidth(200);
        window.setHeight(200);

        window.setResizable(false);

        Scene scene = new Scene(buildLayout(), 200, 200, Color.LIGHTBLUE);
        window.setScene(scene);

        // Forces user to close this window before viewing other windows
        window.showAndWait();

    }

    private @NotNull VBox buildLayout() {
        VBox layout = new VBox(5, itemsDisplay, partySelectorCreator());
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        layout.getChildren().add(closeButton);
        return layout;
    }

    public void removeItemView(String name) {
        HBox itemInfo = itemNameToInfo.get(name);
        if (itemInfo == null) {
            return;
        }
        itemsDisplay.getChildren().remove(itemInfo);
    }

    public void alert(String msg) {
        AlertBox alert = new AlertBox();
        alert.display(msg);
    }

    public void addItemView(String name, String description, boolean isUsable, String quantity) {
        Label a = new Label(name);
        Label b = new Label(quantity);
        HBox info = new HBox(10);
        itemsDisplay.getChildren().add(info);
        itemNameToInfo.put(name, info);
    }

    public void changeItemView(String name, String description, boolean isUsable, String quantity) {
        HBox itemInfo = itemNameToInfo.get(name);
        if (itemInfo != null) {
            itemsDisplay.getChildren().remove(itemInfo);
        }
        Label a = new Label(name);
        Label b = new Label(quantity);
        HBox info = new HBox(10);
        itemNameToInfo.put(name, info);
        itemsDisplay.getChildren().add(info);
    }

    private ComboBox<String> partySelectorCreator() {
        ComboBox<String> partySelector = new ComboBox<>();
        partySelector.getItems().addAll(partyConstants);
        partySelector.setOnAction(e -> {
            String selectedItem = partySelector.getSelectionModel().getSelectedItem();
            this.memberSelected = selectedItem;
        });
        return partySelector;
    }
}
