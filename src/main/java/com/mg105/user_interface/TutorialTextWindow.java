package com.mg105.user_interface;

import com.mg105.controllers.TutorialTextController;
import com.mg105.utils.TutorialTexts;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TutorialTextWindow extends Application {
    TutorialTextDisplay tutorialDisplay = new TutorialTextDisplay();
    Label bottomText = new Label();
    Label helpText = new Label();
    TutorialTextController textController;

    Pane tutorialPane = new Pane();

    public TutorialTextWindow(TutorialTextController textController) {
        this.textController = textController;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage tutorialStage) {
        bottomText = tutorialDisplay.tutorialLabel();
        bottomText.setFont(Font.font(TutorialTexts.textFont1, TutorialTexts.textSize));

        helpText = tutorialDisplay.tutorialLabel();
        helpText.setFont(Font.font(TutorialTexts.textFont2, TutorialTexts.textSize));
        helpText.setBackground(new Background(new BackgroundFill(Color.rgb(255, 215, 0, 1),
            new CornerRadii(30.0), new Insets(-40.0))));

        tutorialPane.getChildren().add(bottomText);
        tutorialPane.getChildren().add(helpText);

        Scene tutorialScene = new Scene(tutorialPane, TutorialTexts.helperPaneX, TutorialTexts.helperPaneY);

        AnimationTimer timer = new TutorialTimer();
        timer.start();

        tutorialStage.setScene(tutorialScene);
        tutorialStage.show();
    }

    private class TutorialTimer extends AnimationTimer {
        private long prevTime = 0;
        private double helpTimer = TutorialTexts.helpTime;

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

            // 4e9 is 4 seconds
            if (timeChange > TutorialTexts.TEXT_DURATION1 * 1e9 & textController.changeText()) {
                prevTime = now;
                tutorialDisplay.getController().nextPhase();
                String tutorialText = tutorialDisplay.getController().bottomText();
                bottomText.setText(tutorialDisplay.showBottomText(tutorialText));

            }

            if (textController.getShowControls()){
                helpText.setText(TutorialTexts.CONTROLS);
                helpTimer --;
                if (helpTimer < 1){
                    textController.setShowControls(false);
                    helpTimer = TutorialTexts.helpTime;
                }
            }
            else {
                helpText.setText("");
            }

        }
    }
}





