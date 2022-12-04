package com.mg105.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

class PointComparatorTest {
    @Test
    void distanceStraightLine() {
        PointComparator p = new PointComparator(new Point());

        assertEquals(1, p.distanceTo(new Point(1, 0)));
        assertEquals(2, p.distanceTo(new Point(-2, 0)));
        assertEquals(3, p.distanceTo(new Point(0, 3)));
        assertEquals(4, p.distanceTo(new Point(0, -4)));
    }

    @Test
    void distanceNotStraight() {
        PointComparator p = new PointComparator(new Point());

        assertEquals(5, p.distanceTo(new Point(1, 4)));
        assertEquals(5, p.distanceTo(new Point(-2, -3)));
        assertEquals(5, p.distanceTo(new Point(-2, 3)));
        assertEquals(5, p.distanceTo(new Point(1, -4)));
    }

    @Test
    void distanceZero() {
        PointComparator p = new PointComparator(new Point());
        assertEquals(0, p.distanceTo(new Point(0, 0)));

        p = new PointComparator(new Point(3, 7));
        assertEquals(0, p.distanceTo(new Point(3, 7)));
    }

    @Test
    void compareNotEqual() {
        PointComparator p = new PointComparator(new Point());

        assertTrue(0 > p.compare(new Point(1, 2), new Point(3, 1)));
        assertTrue(0 < p.compare(new Point(3, 1), new Point(1, 2)));

        assertTrue(0 > p.compare(new Point(), new Point(0, -1)));
        assertTrue(0 < p.compare(new Point(-1, 0), new Point()));
    }

    @Test
    void compareEqual() {
        PointComparator p = new PointComparator(new Point());

        assertEquals(0, p.compare(new Point(), new Point()));
        assertEquals(0, p.compare(new Point(1, 0), new Point(-1, 0)));
        assertEquals(0, p.compare(new Point(3, 7), new Point(5, -5)));
    }
}
