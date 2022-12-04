package com.mg105.interface_adapters;

import com.mg105.entities.BattleCharacter;
import com.mg105.entities.GameState;
import com.mg105.entities.Inventory;
import com.mg105.entities.WalkingCharacter;
import com.mg105.use_cases.WalkVisInteractor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

class WalkVisControllerTest {

    @Test
    void changePlayerSprite() {
        Inventory inventory = new Inventory();
        WalkingCharacter character = new WalkingCharacter(new Point(1, 1));
        BattleCharacter[] party = {};

        GameState state = new GameState(inventory, party, character);
        WalkVisInteractor interactor = new WalkVisInteractor(state);
        WalkVisController controller = new WalkVisController(interactor);
        controller.changePlayerSprite("Diana");

        Assertions.assertEquals("Diana", state.getWalkingCharacter().getSpriteName());
    }
}
