package com.mg105.user_interface;

import com.mg105.interfaceadapter.BattlePresenter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BattleMenu extends Application {

    private BattlePresenter presenter;
    public BattleMenu(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Battle");

        Button nextRound = new Button("Next Round");

        StackPane layout = new StackPane();
    }

    public void updateCharacter(String character){

    }
}
