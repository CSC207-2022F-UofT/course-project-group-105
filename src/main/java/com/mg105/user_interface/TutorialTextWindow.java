package com.mg105.user_interface;

import com.mg105.interface_adapters.tutorial.TutorialTextController;
import com.mg105.utils.TutorialTexts;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;

public class TutorialTextWindow implements Toggleable{
    TutorialTextDisplay tutorialDisplay;
    Pane tutorialPane = new Pane();
    Label bottomText = new Label();
    Label helpText;
    TutorialTextController textController;
    Scene tutorialScene = new Scene(tutorialPane, TutorialTexts.HELPER_PANE_X, TutorialTexts.HELPER_PANE_Y);

    /**
     * Window for the tutorial
     * @param textController the controller for the tutorial
     * @param tutorialDisplay the ui that displays the tutorial
     */
    public TutorialTextWindow(TutorialTextController textController, @NotNull TutorialTextDisplay tutorialDisplay) {
        this.textController = textController;
        this.tutorialDisplay = tutorialDisplay;

        bottomText.setFont(Font.font(TutorialTexts.TEXT_SIZE));

        helpText = tutorialDisplay.tutorialLabel();

        helpText.setFont(Font.font(TutorialTexts.TEXT_SIZE));
        helpText.setBackground(new Background(new BackgroundFill(Color.rgb(255, 215, 0, 1),
            new CornerRadii(80.0), new Insets(-100.0))));

        tutorialPane.getChildren().add(bottomText);
        tutorialPane.getChildren().add(helpText);

        AnimationTimer timer = new TutorialTimer();
        timer.start();
    }

    /**
     * Get the scene of this toggleable object.  It is this scene that will be displayed.
     *
     * @return the scene to be displayed.
     */
    @Override
    public @NotNull Scene getScene() {return tutorialScene;}

    /**
     * Set the visibility of this component.
     *
     * @param isVisible true if the Toggleable is now visible, false otherwise.  If false the Toggleable is expected
     *                  to do nothing on ANY user inputs.
     */
    @Override
    public void toggle(boolean isVisible) {
    }

    private class TutorialTimer extends AnimationTimer {
        private long prevTime = 0;
        private double helpTimer = TutorialTexts.HELP_TIME;

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

            // 1e9 is 1 second
            if (timeChange > TutorialTexts.TEXT_DURATION1 * 1e9 & textController.changeText()) {
                prevTime = now;
                tutorialDisplay.getController().nextPhase();
                String tutorialText = tutorialDisplay.getController().bottomText();
                bottomText.setText(tutorialDisplay.showBottomText(tutorialText));
            }

            if (textController.getShowControls() & textController.getTutorial().isComplete()){
                helpText.setText(tutorialDisplay.showControlsText());
                helpTimer --;
                if (helpTimer < 1){
                    textController.setShowControls(false);
                    helpTimer = TutorialTexts.HELP_TIME;
                }
            } else if (textController.getShowControls()) {
                if (!textController.getTutorial().getActionPerformed(TutorialTexts.MOVED)){
                        helpText.setText(TutorialTexts.DID_NOT_MOVE);
                    } else if (!textController.getTutorial().getActionPerformed(TutorialTexts.USED_ITEM)) {
                        helpText.setText(TutorialTexts.DID_NOT_OPEN_CHEST);
                }
                else {
                    helpText.setText(TutorialTexts.DID_NOT_BATTLE);
                }

                helpTimer --;
                if (helpTimer < 1){
                    textController.setShowControls(false);
                    helpTimer = TutorialTexts.HELP_TIME;
                }

            } else {
                helpText.setText("");
            }

        }
    }
}





