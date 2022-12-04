package com.mg105.use_cases;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.WalkingCharacter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class WalkVisInteractorTest {

    @Test
    void setWalkingSprite() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(1, 1));
        BattleCharacter[] party = {};

        GameState state = new GameState(inventory, party, character);
        WalkVisInteractor interactor = new WalkVisInteractor(state);
        interactor.setWalkingSprite("Diana");

        Assertions.assertEquals("Diana", state.getWalkingCharacter().getSpriteName());
    }
}
