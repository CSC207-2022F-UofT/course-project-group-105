package com.mg105.use_cases.Battle;

import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.access.PartyDataAccess;
import com.mg105.entities.*;
import com.mg105.use_cases.battle.BattleInteractor;
import com.mg105.use_cases.battle.BattlePresenterInterface;
import com.mg105.use_cases.inventory.InventoryInteractor;
import com.mg105.use_cases.inventory.InventoryPresenterInterface;
import com.mg105.use_cases.outputds.ItemDetails;
import com.mg105.use_cases.save.PartySaver;
import com.mg105.use_cases.save.Saver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BattleInteractorTest {

    private Saver createSaver(GameState state){
        PartySaver[] partySavers = {new PartySaver(state, new PartyDataAccess(new MoveDataAccess()))};
        return new Saver(partySavers);
    }

    private static final InventoryPresenterInterface inventoryPresenterInterface = new InventoryPresenterInterface() {
        @Override
        public void addItem(boolean isSuccessful, ItemDetails itemDetails) {

        }

        @Override
        public void removeItem(boolean isSuccessful, ItemDetails itemDetails) {

        }

        @Override
        public void useItem(boolean isSuccessful, String characterName, ItemDetails itemDetails) {

        }

        @Override
        public void inventoryDetails(ItemDetails[] allItemsDetails) {

        }
    };

    private final static BattlePresenterInterface presenter = new BattlePresenterInterface() {
        @Override
        public void setViewNames(String[] partyNames, String[] opponentNames) {

        }

        @Override
        public void updateViewCharacter(String targetName) {

        }
    };

    @Test
    void createValidEncounter() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p3 = new BattleCharacter(10, "Michael", 10, 10, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p4 = new BattleCharacter(6, "Alex", 12, 14, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1, p2, p3, p4};

        List<BattleCharacter> opponents = new ArrayList<>();
        opponents.add(new BattleCharacter(
            10, "Opponent 0", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 1", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 2", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 3", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();

        Assertions.assertEquals(4, interactor.retrieveTargets(1, "Leslie").size());
        Assertions.assertEquals(4, interactor.retrieveTargets(2, "Leslie").size());
        Assertions.assertNotNull(interactor.roundStart());
    }

    @Test
    void executeMoveSuccess() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p3 = new BattleCharacter(10, "Michael", 10, 10, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p4 = new BattleCharacter(6, "Alex", 12, 14, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1, p2, p3, p4};

        List<BattleCharacter> opponents = new ArrayList<>();
        opponents.add(new BattleCharacter(
            10, "Opponent 0", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 1", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 2", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 3", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();

        ArrayList<String> targets = interactor.retrieveTargets(1, "Leslie");
        String target = targets.get(0);
        int originalHealth = interactor.getCharacterHealth(target);
        interactor.executeTurn(1, "Leslie", target);

        Assertions.assertTrue(interactor.isCharacterFainted(target) ||
            interactor.getCharacterHealth(target) != originalHealth);
    }

    @Test
    void findFaintedCharacter() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false,
            new Move(-2000, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p3 = new BattleCharacter(10, "Michael", 10, 10, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p4 = new BattleCharacter(6, "Alex", 12, 14, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1, p2, p3, p4};

        List<BattleCharacter> opponents = new ArrayList<>();
        opponents.add(new BattleCharacter(
            10, "Opponent 0", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 1", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 2", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Opponent 3", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();

        interactor.executeTurn(1, "Leslie", "Opponent 0");
        Assertions.assertTrue(interactor.isCharacterFainted("Opponent 0"));
        Assertions.assertFalse(interactor.isCharacterFainted("Opponent 1"));
        Assertions.assertFalse(interactor.isCharacterFainted("Leslie"));
    }

    @Test
    void startRoundProperly() {
        //Note: BattleInteractor will never have 100% line coverage due to use of Random number generation being
        // involved in conditional statements.
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 2000, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p3 = new BattleCharacter(10, "Michael", 10, 10, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p4 = new BattleCharacter(6, "Alex", 12, 14, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1, p2, p3, p4};

        List<BattleCharacter> opponents = new ArrayList<>();
        opponents.add(new BattleCharacter(
            10, "Professor Chad", 10, 1000, true,
            new Move(0, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "RobotOneEvil", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Evil Person", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );
        opponents.add(new BattleCharacter(
            10, "Megan", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();
        String first = interactor.roundStart();
        String second = interactor.roundStart();


        Assertions.assertEquals("Leslie", first);
        Assertions.assertEquals("Professor Chad", second);
    }

    @Test
    void roundStartWin() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false,
            new Move(-2000, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p3 = new BattleCharacter(10, "Michael", 10, 10, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));
        BattleCharacter p4 = new BattleCharacter(6, "Alex", 12, 14, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1, p2, p3, p4};

        List<BattleCharacter> opponents = new ArrayList<>();
        opponents.add(new BattleCharacter(
            10, "Professor Chad", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(1, 1, "Move2", true))
        );

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();
        interactor.executeTurn(1, "Leslie", "Professor Chad");

        Assertions.assertNull(interactor.roundStart());
    }

    @Test
    void roundStartLoss() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1};

        List<BattleCharacter> opponents = new ArrayList<>();
        opponents.add(new BattleCharacter(
            10, "Professor Chad", 10, 10, true,
            new Move(-10, 0, "Move1", false),
            new Move(-1000, 0, "Move2", false))
        );

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();
        interactor.executeTurn(2, "Professor Chad", "Leslie");

        Assertions.assertNull(interactor.roundStart());
    }

    @Test
    void getCharacterDetails() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false,
            new Move(-5, 0, "first", false),
            new Move(0, 1, "second",true));

        BattleCharacter[] party = {p1};

        List<BattleCharacter> opponents = new ArrayList<>();

        GameState state = new GameState(inventory, party, character);
        state.setCurrOpponent(new OpponentSet(new Point(4, 6), opponents));
        BattleInteractor interactor = new BattleInteractor(state, new InventoryInteractor(state,
            inventoryPresenterInterface), createSaver(state));
        interactor.setPresenter(presenter);
        interactor.createEncounter();
        int dmg = interactor.getCharacterDamage("Leslie");
        int moveOneHpChange = interactor.getCharacterMoveStats("Leslie")[0];
        String moveOneName = interactor.getCharacterMoveNames("Leslie")[0];

        Assertions.assertEquals(7, dmg);
        Assertions.assertEquals(-5, moveOneHpChange);
        Assertions.assertEquals("first", moveOneName);
    }
}

