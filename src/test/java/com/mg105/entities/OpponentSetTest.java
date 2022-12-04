package com.mg105.entities;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OpponentSetTest {
    @Test
    void getPosition() {
        Point position = new Point(1, 1);
        OpponentSet opponentSet = new OpponentSet(position, new ArrayList<>());
        assertEquals(position, opponentSet.getPosition());
    }

    @Test
    void getNextOpponents() {
        List<BattleCharacter> opponents = new ArrayList<>();
        OpponentSet opponentSet = new OpponentSet(new Point(), opponents);
        assertEquals(opponents, opponentSet.getOpponents());
    }
}
