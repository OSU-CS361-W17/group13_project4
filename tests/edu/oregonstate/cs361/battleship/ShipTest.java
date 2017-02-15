package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michaelhilton on 2/7/17.
 */
class ShipTest {

    @Test
    public void testPlaceShip() {
        Ship s = new Ship("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(true,s.covers(new Coordinate(5,2)));
    }

    @Test
    public void testPlaceShipHorizontal() {
        Ship s = new Ship("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(10,2));
        assertEquals(true,s.covers(new Coordinate(6,2)));
    }

    @Test
    public void testPlaceShipHorizontalFalse() {
        Ship s = new Ship("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(10,2));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    @Test
    public void testPlaceShipVerticalFalse() {
        Ship s = new Ship("AircraftCarrier",5,new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    //BAD INPUT
    @Test
    public void testPlaceShipVerticalNoLocation() {
        Ship s = new Ship("AircraftCarrier",5,new Coordinate(0,0),new Coordinate(9,9));
        assertEquals(false,s.covers(new Coordinate(1,1)));
    }

    @Test
    public void testScan() {
        Ship s = new Ship("AircraftCarrier",5, new Coordinate(5,2),new Coordinate(5,7));
        assertEquals(false,s.scan(new Coordinate(1,1)));
        assertEquals(true,s.scan(new Coordinate(4,2)));
        assertEquals(true,s.scan(new Coordinate(5,2)));
        assertEquals(true,s.scan(new Coordinate(6,2)));
        assertEquals(true,s.scan(new Coordinate(5,1)));
        assertEquals(true,s.scan(new Coordinate(5,3)));
    }




}