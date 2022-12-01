package com.mg105.use_cases.Battle;

import com.mg105.entities.*;
import com.mg105.use_cases.BattleInteractor;
import com.mg105.use_cases.BattlePresenterInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class BattleInteractorTest {

    BattlePresenterInterface presenter = new BattlePresenterInterface() {
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
            10, "Professor Chad", 10, 10, true,
            new Move(-10, 0, "Move1", false),
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
        BattleInteractor interactor = new BattleInteractor(state);
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
            10, "Professor Chad", 10, 10, true,
            new Move(-10, 0, "Move1", false),
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
        BattleInteractor interactor = new BattleInteractor(state);
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
            10, "Professor Chad", 10, 10, true,
            new Move(-10, 0, "Move1", false),
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
        BattleInteractor interactor = new BattleInteractor(state);
        interactor.setPresenter(presenter);
        interactor.createEncounter();

        interactor.executeTurn(1, "Leslie", "Opponent 0");
        Assertions.assertTrue(interactor.isCharacterFainted("Opponent 0"));
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
            10, "Professor Chad", 10, 10, true,
            new Move(-10, 0, "Move1", false),
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
        BattleInteractor interactor = new BattleInteractor(state);
        interactor.setPresenter(presenter);
        interactor.createEncounter();

        Assertions.assertEquals("Leslie", interactor.roundStart());
    }
}

