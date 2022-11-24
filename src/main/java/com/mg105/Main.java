package com.mg105;

import com.mg105.controllers.TutorialTextController;
import com.mg105.entities.*;
import com.mg105.interface_adapters.InputInterpreter;
import com.mg105.interface_adapters.MapGeneratorInterpreter;
import com.mg105.interface_adapters.RoomInterpreter;
import com.mg105.interface_adapters.Toggler;
import com.mg105.use_cases.CharacterMover;
import com.mg105.use_cases.MapGenerator;
import com.mg105.use_cases.RoomGetter;
import com.mg105.user_interface.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The main class that sets up the clean architecture mountain group 105 game!
 */
public class Main extends Application {
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

        TutorialTextController textChanger = new TutorialTextController(false);

        CharacterMover characterMover = new CharacterMover(state, mapDrawer);
        InputInterpreter inputInterpreter = new InputInterpreter(characterMover, sceneController, textChanger);
        InputListener inputListener = new InputListener(inputInterpreter);
        primaryStage.addEventFilter(KeyEvent.KEY_TYPED, inputListener);

        sceneController.toggle(Toggler.ToggleableComponent.MAIN_MENU);
        primaryStage.setTitle("Mountain Group 105");
        primaryStage.setResizable(false);
        primaryStage.show();

        // make new window for tutorial
        if (state.isCurrentRoomFirstRoom()) {
            TutorialTextWindow tutorialWindow = new TutorialTextWindow(textChanger);
            Stage tutorialStage = new Stage();
            tutorialStage.setX(660);
            tutorialStage.setY(780);
            tutorialWindow.start(tutorialStage);
        }

    }

}
