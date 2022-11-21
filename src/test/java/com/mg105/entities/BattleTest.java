package com.mg105.entities;

import com.mg105.entities.Battle;
import com.mg105.entities.BattleCharacter;
import com.mg105.entities.Move;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    void getMovingCharacter() {
        Move m1 = new Move(5, 0, "first", true);
        Move m2 = new Move(0, 1, "second",true);

        BattleCharacter op1 = new BattleCharacter(10, "Michael", 10, 10, true, m1, m2);
        BattleCharacter op2 = new BattleCharacter(6, "Alex", 12, 14, true, m1, m2);
        BattleCharacter p1 = new BattleCharacter(14, "Mariam", 7, 11,false, m1, m2);
        BattleCharacter p2 = new BattleCharacter(9, "Leslie", 6, 8, false, m1, m2);

        ArrayList<BattleCharacter> opponents = new ArrayList<BattleCharacter>();
        opponents.add(op1);
        opponents.add(op2);

        ArrayList<BattleCharacter> players = new ArrayList<BattleCharacter>();
        players.add(p1);
        players.add(p2);

        Battle encounter = new Battle(opponents, players);

        BattleCharacter fastest = encounter.getMovingCharacter();
        BattleCharacter second = encounter.getMovingCharacter();
        BattleCharacter third = encounter.getMovingCharacter();
        BattleCharacter fourth = encounter.getMovingCharacter();

        Assertions.assertEquals("Alex", fastest.getName());
        Assertions.assertEquals("Mariam", second.getName());
        Assertions.assertEquals("Michael", third.getName());
        Assertions.assertEquals("Leslie", fourth.getName());
        Assertions.assertEquals(fastest.getName(), encounter.getMovingCharacter().getName());
    }

    @Test
    void removeChar() {
        Move m1 = new Move(5, 0, "first", true);
        Move m2 = new Move(0, 1, "second",true);

        BattleCharacter op1 = new BattleCharacter(10, "Michael", 10, 10, true, m1, m2);
        BattleCharacter op2 = new BattleCharacter(6, "Alex", 12, 14, true, m1, m2);
        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, false, m1, m2);
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, false, m1, m2);

        ArrayList<BattleCharacter> opponents = new ArrayList<BattleCharacter>();
        opponents.add(op1);
        opponents.add(op2);

        ArrayList<BattleCharacter> players = new ArrayList<BattleCharacter>();
        players.add(p1);
        players.add(p2);

        Battle encounter = new Battle(opponents, players);

        encounter.removeChar(op1);
        encounter.removeChar(p2);
        encounter.removeChar(op2);

        Assertions.assertEquals(1, encounter.getBattleStatus());
    }
}