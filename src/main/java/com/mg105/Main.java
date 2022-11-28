package com.mg105;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.Move;
import com.mg105.interface_adapters.InputInterpreter;
import com.mg105.interface_adapters.MapGeneratorInterpreter;
import com.mg105.interface_adapters.RoomInterpreter;
import com.mg105.interface_adapters.Toggler;
import com.mg105.use_cases.CharacterMover;
import com.mg105.use_cases.MapGenerator;
import com.mg105.use_cases.RoomGetter;
import com.mg105.user_interface.*;
import com.mg105.utils.PartyConstants;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * The main class that sets up the clean architecture mountain group 105 game!
 */
public class Main extends Application {
    private final TutorialTextDisplay tutorialDisplay = new TutorialTextDisplay();
    private Label bottomText;

    /**
     * The main method.  See Main.start().
     *
     * @param args the commandline arguments.  They are passed to JavaFX.
     */
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
        // Set up should probably be moved to private method(s) or separate class?
        Inventory inventory = new Inventory();
        BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];
        for(int i = 0; i < PartyConstants.ALL_PARTY_MEMBER_NAMES.length; i++){
            party[i] = new BattleCharacter(1, PartyConstants.ALL_PARTY_MEMBER_NAMES[i],
                1,1, new Move(1, 1), new Move(1, 1));
        }
        GameState state = new GameState(inventory, party);

        Map<Toggler.ToggleableComponent, Toggleable> drawableComponents = new HashMap<>();
        // We fill this map in later because of the ordering of parameters
        SceneController sceneController = new SceneController(
            primaryStage,
            drawableComponents,
            Toggler.ToggleableComponent.MAP
        );

        MapGenerator mapGenerator = new MapGenerator(state);
        MapGeneratorInterpreter mapGeneratorInterpreter = new MapGeneratorInterpreter(mapGenerator);
        MapGeneratorButton generateMapButton = new MapGeneratorButton(mapGeneratorInterpreter, sceneController);
        MainMenu mainMenu = new MainMenu(generateMapButton);

        RoomGetter roomGetter = new RoomGetter(state);
        RoomInterpreter roomInterpreter = new RoomInterpreter(roomGetter);
        MapDrawer mapDrawer = new MapDrawer(roomInterpreter);

        drawableComponents.put(Toggler.ToggleableComponent.MAIN_MENU, mainMenu);
        drawableComponents.put(Toggler.ToggleableComponent.MAP, mapDrawer);

        CharacterMover characterMover = new CharacterMover(state, mapDrawer);
        InputInterpreter inputInterpreter = new InputInterpreter(characterMover, sceneController);
        InputListener inputListener = new InputListener(inputInterpreter);
        primaryStage.addEventFilter(KeyEvent.KEY_TYPED, inputListener);

        sceneController.toggle(Toggler.ToggleableComponent.MAIN_MENU);
        primaryStage.setTitle("Mountain Group 105");
        primaryStage.setResizable(false);
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
