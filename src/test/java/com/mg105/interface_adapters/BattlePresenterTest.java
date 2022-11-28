package com.mg105.interface_adapters;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BattlePresenterTest {

    @Test
    void createValidEncounter() {
        Inventory inventory = new Inventory();
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

        GameState state = new GameState(inventory, party);
        BattlePresenter presenter = new BattlePresenter(state);
        presenter.createEncounter();

        Assertions.assertEquals(4, presenter.retrieveTargets(1, "Leslie").size());
    }

    @Test
    void executeMoveSuccess() {
        Inventory inventory = new Inventory();
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

        GameState state = new GameState(inventory, party);
        BattlePresenter presenter = new BattlePresenter(state);
        presenter.createEncounter();

        ArrayList<String> targets = presenter.retrieveTargets(1, "Leslie");
        String target = targets.get(0);
        int originalHealth = presenter.givenCharacterHealth(target);
        presenter.executeTurn(1, "Leslie", target);

        Assertions.assertTrue(presenter.givenCharacterFainted(target) ||
            presenter.givenCharacterHealth(target) != originalHealth);
    }
}
