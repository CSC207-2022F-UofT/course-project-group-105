package com.mg105.UI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Group root = new Group();
        Scene scene = new Scene(root, 600, 600, Color.LIGHTBLUE );
        Stage stage = new Stage();

        Text text = new Text();
        text.setText("Is it working?");
        text.setX(50);
        text.setY(50);

        root.getChildren().add(text);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
