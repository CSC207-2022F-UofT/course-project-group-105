package com.mg105;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.Move;
import com.mg105.interface_adapters.MapGeneratorInterpreter;
import com.mg105.use_cases.MapGenerator;
import com.mg105.user_interface.MapGeneratorButton;
import com.mg105.utils.PartyConstants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        // Set up the initial entities
        // Set up should probably be moved to private method(s) or separate class?
        Inventory inventory = new Inventory();
        BattleCharacter[] party = new BattleCharacter[PartyConstants.ALL_PARTY_MEMBER_NAMES.length];
        for(int i = 0; i < PartyConstants.ALL_PARTY_MEMBER_NAMES.length; i++){
            party[i] = new BattleCharacter(1, PartyConstants.ALL_PARTY_MEMBER_NAMES[i],
                1,1, new Move(1, 1), new Move(1, 1));
        }
        GameState state = new GameState(inventory, party);

        // Set up the initial use cases
        MapGenerator mapGenerator = new MapGenerator(state);

        // Set up the initial interface adapters
        MapGeneratorInterpreter mapGeneratorInterpreter = new MapGeneratorInterpreter(mapGenerator);

        // ... and finally we start the user interface
        Button generateMapButton = new Button("Generate Map");
        generateMapButton.setOnAction(new MapGeneratorButton(mapGeneratorInterpreter));

        StackPane mainMenuLayout = new StackPane();
        mainMenuLayout.getChildren().add(generateMapButton);

        Scene mainMenuScene = new Scene(mainMenuLayout);

        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Mountain Group 105");
        primaryStage.show();
    }
}
