package com.mg105.user_interface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox is a component that creates a new window and display a users a message.
 * This message is usually related to if an action the user performed was successful or not.
 */
public class AlertBox {


    /**
     * Displays a modal that must be resolved before switches to a different window
     *
     * @param msg the message to display`
     */

    public void display(String msg) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert");
        window.setHeight(400);
        window.setWidth(400);
        window.setResizable(false);
        Label label = new Label();
        label.setText(msg);
        Button close = new Button("Close");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, Color.LIGHTBLUE);
        window.setScene(scene);
        window.showAndWait();
    }
}
