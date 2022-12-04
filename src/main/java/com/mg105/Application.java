package com.mg105;

import com.mg105.interface_adapters.battle.BattlePresenter;
import com.mg105.interface_adapters.tutorial.TutorialTextController;
import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.access.PartyDataAccess;
import com.mg105.data_control.creator.MoveDataCreator;
import com.mg105.data_control.creator.PartyDataCreator;
import com.mg105.entities.*;
import com.mg105.interface_adapters.*;
import com.mg105.interface_adapters.inventory.InventoryController;
import com.mg105.interface_adapters.inventory.InventoryPresenter;
import com.mg105.use_cases.battle.BattleInteractor;
import com.mg105.user_interface.*;
import com.mg105.use_cases.*;
import com.mg105.use_cases.inventory.InventoryInteractor;
import com.mg105.use_cases.save.PartySaver;
import com.mg105.use_cases.save.Save;
import com.mg105.use_cases.save.Saver;
import com.mg105.use_cases.set_up.data_system_creator.CreateDataStorage;
import com.mg105.use_cases.set_up.data_system_creator.DataStorageSystemCreator;
import com.mg105.use_cases.set_up.state_setter.GameStateSetter;
import com.mg105.use_cases.set_up.state_setter.PartyCreator;
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
        Inventory inventory = new Inventory();

        GameState state = new GameState(inventory, new WalkingCharacter(new Point(1, 1)));

        // Setting up database
        CreateDataStorage[] databaseCreators = {new MoveDataCreator(), new PartyDataCreator()};
        DataStorageSystemCreator databaseCreator = new DataStorageSystemCreator(databaseCreators);
        databaseCreator.create();

        // Setting the values from the database in game state
        PartyCreator[] partyCreator = {new PartyCreator(new PartyDataAccess(new MoveDataAccess()))};
        GameStateSetter setter = new GameStateSetter(partyCreator);
        setter.setState(state);

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

        // Minimap setup
        MinimapInterpreter minimapInterpreter = new MinimapInterpreter(roomGetter);
        MinimapDrawer minimapDrawer = new MinimapDrawer(minimapInterpreter);
        drawableComponents.put(Toggler.ToggleableComponent.MINIMAP, minimapDrawer);

        // InventoryDisplay set up
        InventoryPresenter inventoryPresenter = new InventoryPresenter();
        InventoryInteractor inventoryInteractor = new InventoryInteractor(state, inventoryPresenter);
        InventoryDisplay inventoryDisplay = new InventoryDisplay(new InventoryController(
            inventoryInteractor));
        inventoryPresenter.setView(inventoryDisplay);
        drawableComponents.put(Toggler.ToggleableComponent.INVENTORY, inventoryDisplay);

        /////Tutorial scene////
        TutorialTextController textChanger = new TutorialTextController(false);
        TutorialTextDisplay textDisplay = new TutorialTextDisplay();
        TutorialTextWindow tutorialDisplay = new TutorialTextWindow(textChanger, textDisplay);
        drawableComponents.put(Toggler.ToggleableComponent.TUTORIAL, tutorialDisplay);
        //////////////////////

        //WalkingMenu scene//
        WalkVisInteractor walkVisInteractor = new WalkVisInteractor(state);
        WalkVisController walkVisController = new WalkVisController(walkVisInteractor);
        WalkingMenu walkingMenu = new WalkingMenu(walkVisController);
        drawableComponents.put(Toggler.ToggleableComponent.WALK_MENU, walkingMenu);
        /////////////////////

        //LoseMenu scene//
        ReplayGenerator replayGenerator = new ReplayGenerator(state, minimapInterpreter);
        replayGenerator.inventoryClean();
        replayGenerator.attributeInheritance();
        ReplayGeneratorInterpreter replayGeneratorInterpreter = new ReplayGeneratorInterpreter(replayGenerator);
        ReplayGeneratorButton replayGeneratorButton = new ReplayGeneratorButton(replayGeneratorInterpreter, sceneController);
        LoseMenu loseMenu = new LoseMenu(replayGeneratorButton);
        drawableComponents.put(Toggler.ToggleableComponent.LOSE_MENU, loseMenu);
        ////////////////////

        //BattleMenu scene//
        //OpponentSet setup
        OpponentSetInteractor opponentInteractor = new OpponentSetInteractor(state);

        // Creating Saver
        Save[] savers = {new PartySaver(state, new PartyDataAccess(new MoveDataAccess()))};
        Saver saver = new Saver(savers);

        //Battle setup
        BattleInteractor battleInteractor = new BattleInteractor(state, inventoryInteractor, saver);
        BattlePresenter battlePresenter = new BattlePresenter(battleInteractor, sceneController);
        BattleMenu battleMenu = new BattleMenu(battlePresenter);
        drawableComponents.put(Toggler.ToggleableComponent.BATTLE, battleMenu);
        /////////////////////

        RoomUpdater roomUpdater = new RoomUpdater();
        roomUpdater.addObserver(mapDrawer);
        roomUpdater.addObserver(minimapInterpreter);

        CharacterMover characterMover = new CharacterMover(state, roomUpdater, replayGenerator);
        ChestInteractor chestInteractor = new ChestInteractor(state, inventoryInteractor, roomUpdater);
        InputInterpreter inputInterpreter = new InputInterpreter(characterMover, sceneController, textChanger, chestInteractor,
            opponentInteractor);
        InputListener inputListener = new InputListener(inputInterpreter);
        primaryStage.addEventFilter(KeyEvent.KEY_TYPED, inputListener);
        sceneController.toggle(Toggler.ToggleableComponent.MAIN_MENU);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
