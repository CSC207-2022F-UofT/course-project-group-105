package com.mg105;

import com.mg105.entities.*;
import com.mg105.interface_adapters.MapGeneratorInterpreter;
import com.mg105.use_cases.MapGenerator;
import com.mg105.user_interface.MapGeneratorButton;
import com.mg105.utils.PartyConstants;
import com.mg105.user_interface.TutorialTextDisplay;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;

/**
 * The main class that sets up the clean architecture mountain group 105 game!
 */
public class Main extends Application {
    /**
     * The main method.  See Main.start().
     *
     * @param args the commandline arguments.  They are passed to JavaFX.
     */
    private final TutorialTextDisplay tutorialDisplay = new TutorialTextDisplay();
    private Label bottomText;

    public static void main(String[] args) {
        Main.launch(args);
    }

    /**
     * Note that while this isn't our main method explicitly, we (probably) need this to effectively be our main method
     * for scoping rules.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Set up the initial entities
        // Set up should probably be moved to private method(s) or separate class?
        Inventory inventory = new Inventory();

        BattleCharacter a = new BattleCharacter(30, "A", 4, 5, false,
            new Move(-3, 0, "Slow swing", false),
            new Move(0, -1, "Nullify", false));

        BattleCharacter b = new BattleCharacter(20, "B", 6, 8, false,
            new Move(-4, 0, "Strong swing", false),
            new Move(3, 0, "Weak heal", true));

        BattleCharacter c = new BattleCharacter(25, "C", 3, 6, false,
            new Move(6, 0, "Strong heal", true),
            new Move(2, 2, "Reinforce", true));

        BattleCharacter d = new BattleCharacter(15, "D", 9, 10, false,
            new Move(-5, 0, "Surprise attack", false),
            new Move(-2, -2, "Sabotage", false));

        BattleCharacter[] party = {a, b, c, d};
        GameState state = new GameState(inventory, party, new WalkingCharacter(new Point(0, 0)));

        // Set up the initial use cases
        MapGenerator mapGenerator = new MapGenerator(state);

        // Set up the initial interface adapters
        MapGeneratorInterpreter mapGeneratorInterpreter = new MapGeneratorInterpreter(mapGenerator);

        // ... and finally we start the user interface
        Button generateMapButton = new Button("Generate Map");
        generateMapButton.setOnAction(new MapGeneratorButton(mapGeneratorInterpreter));

        StackPane mainMenuLayout = new StackPane();
        mainMenuLayout.getChildren().add(generateMapButton);

        Scene mainMenuScene = new Scene(mainMenuLayout, 600, 600);


        ////////// TUTORIAL CODE BEGINS /////////////////////////////////

        // set up text that will show at bottom of the screen. Might change to different scene in the future.
        bottomText = tutorialDisplay.tutorialLabel();
        StackPane.setAlignment(bottomText, Pos.BOTTOM_CENTER);
        mainMenuLayout.getChildren().add(bottomText);

        // update the text with a timer. The timer is a private class in Main because it needs to access & change private attributes of it.
        AnimationTimer timer = new TutorialTimer();
        timer.start();

        ///////// TUTORIAL CODE ENDS ////////////////////////////////////


        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Mountain Group 105");
        primaryStage.show();
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
            if (timeChange > 4e9) {
                prevTime = now;
                tutorialDisplay.getController().nextPhase();
                int phase_num = tutorialDisplay.getController().getTutorial().currentPhase();
                String tutorialText = tutorialDisplay.getController().getTutorial().allPhases().get(phase_num);
                bottomText.setText(tutorialDisplay.showBottomText(tutorialText));

            }

        }
    }
}
