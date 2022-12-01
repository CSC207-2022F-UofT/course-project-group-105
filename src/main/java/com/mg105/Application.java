package com.mg105;

import com.mg105.controllers.TutorialTextController;
import com.mg105.entities.*;
import com.mg105.interface_adapters.InputInterpreter;
import com.mg105.interface_adapters.MapGeneratorInterpreter;
import com.mg105.interface_adapters.RoomInterpreter;
import com.mg105.interface_adapters.Toggler;
import com.mg105.outputds.ItemDetails;
import com.mg105.presenter_interfaces.InventoryPresenterInterface;
import com.mg105.interface_adapters.inventory.InventoryController;
import com.mg105.interface_adapters.inventory.InventoryPresenter;
import com.mg105.use_cases.CharacterMover;
import com.mg105.use_cases.ChestInteractor;
import com.mg105.use_cases.Inventory.InventoryInteractor;
import com.mg105.use_cases.Inventory.InventoryInteractor;
import com.mg105.use_cases.MapGenerator;
import com.mg105.use_cases.RoomGetter;
import com.mg105.use_cases.RoomUpdater;
import com.mg105.user_interface.*;
import com.mg105.utils.TutorialTexts;
import com.mg105.user_interface.inventory.InventoryDisplay;
import com.mg105.utils.PartyConstants;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Effectively, the main class that sets up the clean architecture mountain group 105 game!
 */
public class Application extends javafx.application.Application {

    /**
     * Note that while this isn't our main method explicitly, we (probably) need this to effectively be our main method
     * for scoping rules.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {

        // Set up the initial use cases

        BattleCharacter a = new BattleCharacter(30, PartyConstants.ALL_PARTY_MEMBER_NAMES[0], 4, 5,
            false, new Move(-3, 0, "Slow swing", false),
            new Move(0, -1, "Nullify", false));

        BattleCharacter b = new BattleCharacter(20, PartyConstants.ALL_PARTY_MEMBER_NAMES[1], 6, 8,
            false, new Move(-4, 0, "Strong swing", false),
            new Move(3, 0, "Weak heal", true));

        BattleCharacter c = new BattleCharacter(25, PartyConstants.ALL_PARTY_MEMBER_NAMES[2], 3, 6,
            false, new Move(6, 0, "Strong heal", true),
            new Move(2, 2, "Reinforce", true));

        BattleCharacter d = new BattleCharacter(15, PartyConstants.ALL_PARTY_MEMBER_NAMES[3], 9, 10,
            false, new Move(-5, 0, "Surprise attack", false),
            new Move(-2, -2, "Sabotage", false));

        BattleCharacter[] party = {a, b, c, d};
        Inventory inventory = new Inventory();

        GameState state = new GameState(inventory, party, new WalkingCharacter(new Point(1, 1)));

        // InventoryDisplay set up
        InventoryPresenter inventoryPresenter = new InventoryPresenter();
        InventoryDisplay inventoryDisplay = new InventoryDisplay(new InventoryController(
            new InventoryInteractor(state, inventoryPresenter)));
        inventoryPresenter.setView(inventoryDisplay);

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

        /////Tutorial scene////
        TutorialTextController textChanger = new TutorialTextController(false);
        TutorialTextDisplay textDisplay = new TutorialTextDisplay();
        TutorialTextWindow tutorialDisplay = new TutorialTextWindow(textChanger, textDisplay);
        drawableComponents.put(Toggler.ToggleableComponent.TUTORIAL, tutorialDisplay);
        //////////////////////

        RoomUpdater roomUpdater = new RoomUpdater();
        roomUpdater.addObserver(mapDrawer);

        CharacterMover characterMover = new CharacterMover(state, roomUpdater);
        InputInterpreter inputInterpreter = new InputInterpreter(characterMover, sceneController, textChanger, chestInteractor);

        InventoryPresenterInterface res = new InventoryPresenterInterface() {
            @Override
            public void addItemView(boolean isSuccessful, ItemDetails itemDetails) {

            }

            @Override
            public void removeItemView(boolean isSuccessful, ItemDetails itemDetails) {

            }

            @Override
            public void useItemView(boolean isSuccessful, String characterName, ItemDetails itemDetails) {

            }

            @Override
            public void inventoryDetailsView(ItemDetails[] allItemsDetails) {

            }
        };
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, res);
        ChestInteractor chestInteractor = new ChestInteractor(state, inventoryInteractor);
        InputInterpreter inputInterpreter = new InputInterpreter(characterMover, sceneController, textChanger, chestInteractor);
        InputListener inputListener = new InputListener(inputInterpreter);
        primaryStage.addEventFilter(KeyEvent.KEY_TYPED, inputListener);

        sceneController.toggle(Toggler.ToggleableComponent.MAIN_MENU);
        primaryStage.setTitle("Mountain Group 105");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
