package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michaelhilton on 2/7/17.
 */
class CoordinateTest {
    @Test
    void getDown() {
        Coordinate c = new Coordinate(1,2);
        assertEquals(2,c.getDown());
    }

    @Test
    void setDown() {
        Coordinate c = new Coordinate(1,1);
        c.setDown(9);
        assertEquals(9,c.getDown());
    }

    @Test
    void getAcross() {
        Coordinate c = new Coordinate(1,2);
        assertEquals(1,c.getAcross());
    }

    @Test
    void setAcross() {
        Coordinate c = new Coordinate(1,1);
        c.setAcross(9);
        assertEquals(9,c.getAcross());
    }
    @Test
    void equals() {
        Coordinate c = new Coordinate(2,3);
        Coordinate d = new Coordinate(2,3);
        assertEquals(c, d);
    }
    @Test
    void notEquals() {
        Coordinate c = new Coordinate(1,2);
        Coordinate d = new Coordinate(1,3);
        assertNotEquals(c, d);
        assertNotEquals(c, new Object());
    }
}