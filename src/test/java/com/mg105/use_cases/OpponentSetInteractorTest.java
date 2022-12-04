package com.mg105.use_cases;

import com.mg105.entities.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class OpponentSetInteractorTest {

    @Test
    void compareLists() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter[] party = {};

        List<BattleCharacter> opponents = new ArrayList<>();

        GameState state = new GameState(inventory, party, character);
        OpponentSet opSet = new OpponentSet(new Point(0, 0), opponents);
        state.setCurrOpponent(opSet);

        List<OpponentSet> opponentSets = new ArrayList<>();
        opponentSets.add(opSet);

        Assertions.assertNull(OpponentSetInteractor.compareLists(new Point(1, 1), opponentSets));
        Assertions.assertEquals(opSet, OpponentSetInteractor.compareLists(new Point(0, 0), opponentSets));
    }

    @Test
    void verifyOpponent() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter[] party = {};

        List<BattleCharacter> opponents = new ArrayList<>();

        GameState state = new GameState(inventory, party, character);
        OpponentSet opSet = new OpponentSet(new Point(1, 1), opponents);

        List<OpponentSet> opponentSets = new ArrayList<>();
        opponentSets.add(opSet);

        state.setMap(new Room(new ArrayList<>(), opponentSets, new ArrayList<>()),
            new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        OpponentSetInteractor interactor = new OpponentSetInteractor(state);

        Assertions.assertNull(interactor.verifyOpponent());
    }

    @Test
    void setOpponentSet() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(0, 0));
        BattleCharacter[] party = {};

        List<BattleCharacter> opponents = new ArrayList<>();

        GameState state = new GameState(inventory, party, character);
        OpponentSet opSet = new OpponentSet(new Point(1, 0), opponents);

        List<OpponentSet> opponentSets = new ArrayList<>();
        opponentSets.add(opSet);

        state.setMap(new Room(new ArrayList<>(), opponentSets, new ArrayList<>()),
            new Room(new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        OpponentSetInteractor interactor = new OpponentSetInteractor(state);
        interactor.setOpponentSet();
        Assertions.assertEquals(opSet, state.getCurrOpponent());
    }
}
