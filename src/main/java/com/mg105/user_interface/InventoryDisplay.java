package com.mg105.user_interface;

import com.mg105.interface_adapters.inventory.InventoryController;
import com.mg105.interface_adapters.inventory.InventoryViewInterface;
import com.mg105.utils.PartyConstants;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * A user interface class that displays the state of the inventory to the user and allows them to
 * perform actions that relate to the inventory and its items.
 */

public class InventoryDisplay implements InventoryViewInterface, Toggleable {

    private Boolean isVisible = false;
    private VBox itemsDisplay;
    private final HashMap<String, HBox> itemNameToInfo = new HashMap<>();
    private final InventoryController controller;
    private String characterSelected = PartyConstants.ALL_PARTY_MEMBER_NAMES[0];

    /**
     * Creates a new instance of inventory display
     * @param controller an object that interprets user inputs to make changes about the inventory
     */

    public InventoryDisplay(InventoryController controller) {
        this.controller = controller;
    }

    /**
     * Returns the character selected by the user
     * @return the character selected by the user
     */
    private String getCharacterSelected() {
        return this.characterSelected;
    }

    /**
     * Returns the dropdown where user can select a party member
     * @return the dropdown where user can select a party member
     */
    private @NotNull ComboBox<String> buildCharacterDropdown() {
        ComboBox<String> partySelector = new ComboBox<>();
        partySelector.getItems().addAll(PartyConstants.ALL_PARTY_MEMBER_NAMES);
        partySelector.setOnAction(e -> this.characterSelected = partySelector.getSelectionModel().getSelectedItem());
        partySelector.setValue(characterSelected);
        return partySelector;
    }

    /**
     * Returns the component that really has all the details necessary for the scene
     * @return the component that really has all the details necessary for the scene
     */
    private @NotNull VBox buildLayout() {
        this.itemsDisplay = new VBox(10);
        this.itemsDisplay.getChildren().add(new Label("Inventory"));
        controller.getInventoryDetails();
        return new VBox(5, this.itemsDisplay, buildCharacterDropdown());
    }

    /**
     * Returns the scene that displays the information for the Inventory
     *
     * @return the scene that displays the information for the Inventory.
     */

    @Override
    public @NotNull Scene getScene() {
        return new Scene(buildLayout(), 800, 800, Color.LIGHTBLUE);
    }

    /**
     * Changes the state of the InventoryDisplay based on if the inventory display is shown in the ui
     *
     * @param isVisible true if the Toggleable is now visible, false otherwise.  If false the Toggleable is expected
     *                  to do nothing on ANY user inputs.
     */
    @Override
    public void toggle(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Removes an item from the inventory ui.
     * Precondition there must not be an items of the type name in the inventory
     *
     * @param name the name of the item to be removed
     */
    @Override
    public void removeItemView(String name) {
        HBox itemInfo = this.itemNameToInfo.get(name);
        if (itemInfo == null) {
            return;
        }
        itemsDisplay.getChildren().remove(itemInfo);
    }

    /**
     * Opens a window displaying a message to the user
     *
     * @param msg the message to alert the user with
     */
    @Override
    public void alert(String msg) {
        AlertBox alert = new AlertBox();
        alert.display(msg);
    }

    /**
     * Adds the details of the item to the display
     *
     * @param name        the name of the item to add
     * @param description the description of the item to add
     * @param isUsable    true iff the item can be used by a user
     * @param quantity    the number of items of this type in the Inventory
     */
    @Override
    public void addItemView(String name, String description, boolean isUsable, String quantity) {
        if (!this.isVisible) {
            return;
        }
        Label nameLabel = new Label(name);
        Label descriptionLabel = new Label(description);
        Label quantityLabel = new Label(quantity);
        Button removeItem = new Button("Remove Item");
        removeItem.setOnAction(e ->
            controller.removeItem(name));
        HBox info = new HBox(10);

        if (isUsable) {
            info.getChildren().addAll(nameLabel, descriptionLabel, quantityLabel, getUseItemButton(name), removeItem);
        } else {
            info.getChildren().addAll(nameLabel, descriptionLabel, quantityLabel, removeItem);
        }

        itemsDisplay.getChildren().add(info);
        itemNameToInfo.put(name, info);
    }

    /**
     * Changes the details of an already displayed item and displays the changes
     *
     * @param name        the name of the item to change details about
     * @param description the description of the item
     * @param isUsable    true iff the item is usable
     * @param quantity    the number of items of this type in the Inventory
     */
    @Override
    public void changeItemView(String name, String description, boolean isUsable, String quantity) {

        HBox currentInfo = itemNameToInfo.get(name);
        if (currentInfo != null) {
            itemNameToInfo.remove(name);
            itemsDisplay.getChildren().remove(currentInfo);
        }

        addItemView(name, description, isUsable, quantity);
    }

    /**
     * Creates a use item button
     * @param name of item to create use item button
     * @return the use item button
     */
    private Button getUseItemButton(String name) {
        Button useItem = new Button("Use Item");
        useItem.setOnAction(e ->
            controller.useItem(name, getCharacterSelected()));
        return useItem;
    }
}
