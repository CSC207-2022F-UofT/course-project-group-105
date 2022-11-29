package com.mg105.interface_adapters;

import com.mg105.entities.*;
import com.mg105.use_cases.BattleInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

class BattlePresenterTest {
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

        GameState state = new GameState(inventory, party, character);
        BattleInteractor interactor = new BattleInteractor(state);
        BattlePresenter presenter = new BattlePresenter(interactor);
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

        GameState state = new GameState(inventory, party, character);
        BattleInteractor interactor = new BattleInteractor(state);
        BattlePresenter presenter = new BattlePresenter(interactor);
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

        GameState state = new GameState(inventory, party, character);
        BattleInteractor interactor = new BattleInteractor(state);
        BattlePresenter presenter = new BattlePresenter(interactor);
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

        GameState state = new GameState(inventory, party, character);
        BattleInteractor interactor = new BattleInteractor(state);
        BattlePresenter presenter = new BattlePresenter(interactor);
        presenter.startBattle();

        Assertions.assertEquals("Leslie", presenter.roundStart());
    }
}
