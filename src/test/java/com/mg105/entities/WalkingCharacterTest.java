package com.mg105.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WalkingCharacterTest {
    private WalkingCharacter player;

    @BeforeEach
    void setup() {
        player = new WalkingCharacter(new Point());
    }

    @Test
    void getPosition() {
        assertEquals(new Point(), player.getCharPosition());
    }

    @Test
    void setPosition() {
        player.setCharPosition(new Point(1, 2));
        assertEquals(new Point(1, 2), player.getCharPosition());
    }

    @Test
    void getPositionModification() {
        Point p = player.getCharPosition();
        p.x += 5;
        assertNotEquals(p, player.getCharPosition());
        assertEquals(new Point(), player.getCharPosition());
    }

    @Test
    void setPositionModification() {
        Point p = new Point(1, 2);
        player.setCharPosition(p);
        p.x += 5;
        assertNotEquals(p, player.getCharPosition());
        assertEquals(new Point(1, 2), player.getCharPosition());
    }

    @Test
    void getSprite() {
        assertEquals("A", player.getSpriteName());
    }

    @Test
    void setSprite() {
        player.setSpriteName("Jonathan");
        assertEquals("Jonathan", player.getSpriteName());
    }
}
