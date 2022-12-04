package com.mg105.interface_adapters;

import com.mg105.data_control.access.MoveDataAccess;
import com.mg105.data_control.access.PartyDataAccess;
import com.mg105.entities.*;
import com.mg105.use_cases.BattleInteractor;
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

class BattlePresenterTest {

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

    BattleMenuInterface view = new BattleMenuInterface() {
        @Override
        public void setNames(String[] playerNames, String[] opponentNames) {

        }

        @Override
        public void updateCharacter(String character) {

        }
    };
    @Test
    void startValidBattle() {
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
        BattlePresenter presenter = new BattlePresenter(interactor);
        presenter.setView(view);
        presenter.startBattle();

        Assertions.assertEquals(4, presenter.retrieveTargets(1, "Leslie").size());
        Assertions.assertEquals(4, presenter.retrieveTargets(2, "Leslie").size());
        Assertions.assertNotNull(presenter.roundStart());
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
        BattlePresenter presenter = new BattlePresenter(interactor);
        presenter.setView(view);
        presenter.startBattle();

        ArrayList<String> targets = presenter.retrieveTargets(1, "Leslie");
        String target = targets.get(0);
        int originalHealth = presenter.givenCharacterHealth(target);
        presenter.executeTurn(1, "Leslie", target);

        Assertions.assertTrue(presenter.givenCharacterFainted(target) ||
            presenter.givenCharacterHealth(target) != originalHealth);
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
        BattlePresenter presenter = new BattlePresenter(interactor);
        presenter.setView(view);
        presenter.startBattle();

        presenter.executeTurn(1, "Leslie", "Opponent 0");
        Assertions.assertTrue(presenter.givenCharacterFainted("Opponent 0"));
    }

    @Test
    void startRoundProperly() {
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
        BattlePresenter presenter = new BattlePresenter(interactor);
        presenter.setView(view);
        presenter.startBattle();

        Assertions.assertEquals("Leslie", presenter.roundStart());
    }

    @Test
    void givenCharacterDetails() {
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
        BattlePresenter presenter = new BattlePresenter(interactor);
        presenter.setView(view);
        presenter.startBattle();

        int dmg = presenter.givenCharacterDamage("Leslie");
        int moveOneHpChange = presenter.givenCharacterMoveStats("Leslie")[0];
        String moveOneName = presenter.givenCharacterMoveNames("Leslie")[0];

        Assertions.assertEquals(7, dmg);
        Assertions.assertEquals(-5, moveOneHpChange);
        Assertions.assertEquals("first", moveOneName);
    }
}
