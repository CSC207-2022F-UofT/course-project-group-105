package com.mg105.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BattleCharacterTest {

    @Test
    void modifyHealthMinimum() {
        Move m1 = new Move(5, 0, "first", true);
        Move m2 = new Move(0, 1, "second", true);

        BattleCharacter person = new BattleCharacter(10, "Jeff", 10, 10, true, m1, m2);
        person.modifyHealth(-20);

        Assertions.assertEquals(0, person.getHp());
    }

    @Test
    void modifyHealthMaximum() {
        Move m1 = new Move(5, 0, "first", true);
        Move m2 = new Move(0, 1, "second", true);

        BattleCharacter person = new BattleCharacter(10, "Jeff", 10, 10, true, m1, m2);
        person.modifyHealth(-5);
        person.modifyHealth(20);

        Assertions.assertEquals(10, person.getHp());
    }

    @Test
    void compareTo() {
        Move m1 = new Move(5, 0, "first", true);
        Move m2 = new Move(0, 1, "second", true);

        BattleCharacter p1 = new BattleCharacter(14, "Leslie", 7, 8, true, m1, m2);
        BattleCharacter p2 = new BattleCharacter(9, "Mariam", 6, 11, true, m1, m2);
        BattleCharacter p3 = new BattleCharacter(20, "Karen", 2, 11, false, m1, m2);

        Assertions.assertEquals(-1, p1.compareTo(p2));
        Assertions.assertEquals(1, p3.compareTo(p1));
        Assertions.assertEquals(0, p2.compareTo(p3));
    }
}
