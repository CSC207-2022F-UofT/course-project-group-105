package com.mg105.user_interface;

import com.mg105.controllers.TutorialTextController;
import com.mg105.interface_adapters.InputInterpreter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.text.BreakIterator;

public class TutorialTextWindow extends Application {
    TutorialTextDisplay tutorialDisplay = new TutorialTextDisplay();
    Label bottomText = new Label();
    TutorialTextController textController;

    public TutorialTextWindow(TutorialTextController textController){
        this.textController = textController;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage tutorialStage) {
        Group tutorialWindow = new Group();
        Pane tutorialPane = new Pane();

        bottomText = tutorialDisplay.tutorialLabel();
        bottomText.setFont(Font.font("Courier", 16));
        tutorialPane.getChildren().add(bottomText);

        Scene tutorialScene = new Scene(tutorialPane,550, 70);

        AnimationTimer timer = new TutorialTimer();
        timer.start();

        tutorialStage.setScene(tutorialScene);
        tutorialStage.show();
    }
    private class TutorialTimer extends AnimationTimer {
        private long prevTime = 0;

        /**
         * This method needs to be overridden by extending classes. It is going to
         * be called in every frame while the {@code AnimationTimer} is active.
         *
         * @param now The timestamp of the current frame given in nanoseconds. This
         *            value will be the same for all {@code AnimationTimers} called
         *            during one frame.
         */
        @Override
        public void handle(long now) {
            long timeChange = now - prevTime;

            // 5e9 is 5 seconds
            if (timeChange > 4e9 & textController.changeText()) {
                prevTime = now;
                tutorialDisplay.getController().nextPhase();
                String tutorialText = tutorialDisplay.getController().bottomText();
                bottomText.setText(tutorialDisplay.showBottomText(tutorialText));

            }

        }
    }

}


