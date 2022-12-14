package com.mg105.user_interface;

import com.mg105.interface_adapters.WalkVisController;
import com.mg105.utils.PartyConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

/**
 * This class uses JavaFX and is displayed when the user wants to change the walking character sprite.
 */
public class WalkingMenu implements EventHandler<ActionEvent>, Toggleable {
    private final WalkVisController controller;
    private final RadioButton radA;
    private final RadioButton radB;
    private final RadioButton radC;
    private final RadioButton radD;
    private final Button select;
    private final Scene scene;

    /**
     * Creates a new WalkingMenu with reference to a controller class.
     * Sets up UI elements.
     *
     * @param controller the WalkVisController to be referred to.
     */
    public WalkingMenu(WalkVisController controller) {
        this.controller = controller;

        StackPane layout = new StackPane();

        ToggleGroup group = new ToggleGroup();

        VBox buttons = new VBox();

        radA = new RadioButton(PartyConstants.ALL_PARTY_MEMBER_NAMES[0]);
        radA.setToggleGroup(group);
        radA.setSelected(true);

        radB = new RadioButton(PartyConstants.ALL_PARTY_MEMBER_NAMES[1]);
        radB.setToggleGroup(group);
        radB.setSelected(false);

        radC = new RadioButton(PartyConstants.ALL_PARTY_MEMBER_NAMES[2]);
        radC.setToggleGroup(group);
        radC.setSelected(false);

        radD = new RadioButton(PartyConstants.ALL_PARTY_MEMBER_NAMES[3]);
        radD.setToggleGroup(group);
        radD.setSelected(false);

        select = new Button("Confirm");
        select.setOnAction(this);

        Label exitLbl = new Label("Press space bar to exit.");

        buttons.getChildren().addAll(radA, radB, radC, radD, select, exitLbl);
        buttons.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(buttons);

        scene = new Scene(layout, 300, 300);
    }

    /**
     * Handles button events.
     * Pressing the select button updates the WalkingCharacter to represent the desired walking character sprite.
     *
     * @param event the event which occurred.
     */
    @Override
    public void handle(ActionEvent event) {
        Object source = event.getSource();
        if (source.equals(select)) {
            if (radA.isSelected()) {
                controller.changePlayerSprite("A");
            } else if (radB.isSelected()) {
                controller.changePlayerSprite("B");
            } else if (radC.isSelected()) {
                controller.changePlayerSprite("C");
            } else if (radD.isSelected()) {
                controller.changePlayerSprite("D");
            }
        }
    }

    /**
     * Get the scene of this toggleable object.  It is this scene that will be displayed.
     *
     * @return the scene to be displayed.
     */
    @Override
    public @NotNull Scene getScene() {
        return this.scene;
    }

    /**
     * Set the visibility of this component.
     *
     * @param isVisible true if the Toggleable is now visible, false otherwise.  If false the Toggleable is expected
     *                  to do nothing on ANY user inputs.
     */
    @Override
    public void toggle(boolean isVisible) {
        //The menu should ideally remain the same.
    }
}
