package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michaelhilton on 2/7/17.
 */
class BattleshipModelTest {

    @Test
    void getShip() {
        BattleshipModel model = new BattleshipModel();
        assertEquals("AircraftCarrier",model.getShip("AircraftCarrier").getName());
        assertEquals("Battleship",model.getShip("battleship").getName());
        assertEquals("Dinghy",model.getShip("Dinghy").getName());
        assertEquals("Clipper",model.getShip("Clipper").getName());
        assertEquals("Submarine",model.getShip("Submarine").getName());
        assertNull(model.getShip("SS Minnow"));
    }

    Boolean testIfCovers(BattleshipModel model,String shipName, String row, String col, String orientation,int intRow, int intCol){
      return  model.placeShip(shipName,row,col,orientation).getShip(shipName).covers(new Coordinate(intRow,intCol));
    }

    @Test
    void placeShip() {
        BattleshipModel model = new BattleshipModel();
        assertEquals(true,
                testIfCovers(model, "AircraftCarrier","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "AircraftCarrier","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "AircraftCarrier","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "AircraftCarrier","1","1","vertical",9,9));

        assertEquals(true,
                    testIfCovers(model, "Battleship","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "Battleship","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "Battleship","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "Battleship","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "Clipper","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "Clipper","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "Clipper","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "Clipper","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "Dinghy","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "Dinghy","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "Dinghy","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "Dinghy","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "Submarine","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "Submarine","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "Submarine","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "Submarine","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "computer_AircraftCarrier","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "computer_AircraftCarrier","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "computer_AircraftCarrier","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "computer_AircraftCarrier","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "computer_Battleship","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "computer_Battleship","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "computer_Battleship","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "computer_Battleship","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "computer_Clipper","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "computer_Clipper","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "computer_Clipper","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "computer_Clipper","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "computer_Dinghy","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "computer_Dinghy","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "computer_Dinghy","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "computer_Dinghy","1","1","vertical",9,9));

        assertEquals(true,
                testIfCovers(model, "computer_Submarine","1","1","horizontal",1,1));
        assertEquals(true,
                testIfCovers(model, "computer_Submarine","1","1","vertical",1,1));
        assertEquals(false,
                testIfCovers(model, "computer_Submarine","1","1","horizontal",9,9));
        assertEquals(false,
                testIfCovers(model, "computer_Submarine","1","1","vertical",9,9));

        assertNull(model.placeShip("Submarine","1","1","horizontal").getShip("USS Minnow"));


    }

    @Test
    void shootAtComputer() {
        BattleshipModel model = new BattleshipModel();
        model.shootAtComputer(1,1) ;
        assertEquals(true, model.computerHits.isEmpty());

        model.shootAtComputer(2,3) ;
        assertEquals(2, model.computerHits.get(0).getAcross());
        assertEquals(3, model.computerHits.get(0).getDown());

        model.shootAtComputer(5,8) ;
        assertEquals(5, model.computerHits.get(1).getAcross());
        assertEquals(8, model.computerHits.get(1).getDown());

        model.shootAtComputer(4,3) ;
        assertEquals(4, model.computerHits.get(2).getAcross());
        assertEquals(3, model.computerHits.get(2).getDown());

        model.shootAtComputer(7,3) ;
        assertEquals(7, model.computerHits.get(3).getAcross());
        assertEquals(3, model.computerHits.get(3).getDown());

        model.shootAtComputer(9,6) ;
        assertEquals(9, model.computerHits.get(4).getAcross());
        assertEquals(6, model.computerHits.get(4).getDown());
    }


    @Test
    void shootAtPlayer() {
        BattleshipModel model = new BattleshipModel();
        model.placeShip("Aircraftcarrier","1","5","horizontal");
        model.placeShip("Battleship","2","4","horizontal");
        model.placeShip("Clipper","3","3","horizontal");
        model.placeShip("Dinghy","4","2","horizontal");
        model.placeShip("Submarine","5","1","horizontal");

        model.playerShot(new Coordinate(9,9));
        assertEquals(true, model.playerHits.isEmpty());

        model.playerShot(new Coordinate(1,5));
        assertEquals(1, model.playerHits.get(0).getAcross());
        assertEquals(5, model.playerHits.get(0).getDown());

        model.playerShot(new Coordinate(2,4));
        assertEquals(2, model.playerHits.get(1).getAcross());
        assertEquals(4, model.playerHits.get(1).getDown());

        model.playerShot(new Coordinate(3,3));
        assertEquals(3, model.playerHits.get(2).getAcross());
        assertEquals(3, model.playerHits.get(2).getDown());

        model.playerShot(new Coordinate(4,2));
        assertEquals(4, model.playerHits.get(3).getAcross());
        assertEquals(2, model.playerHits.get(3).getDown());

        model.playerShot(new Coordinate(5,1));
        assertEquals(5, model.playerHits.get(4).getAcross());
        assertEquals(1, model.playerHits.get(4).getDown());
    }

    @Test
    void testsScan() {
        BattleshipModel model = new BattleshipModel();
        model.scan(2,2);
        assertEquals(true, model.getScanResult());

        model.scan(6,6);
        assertEquals(false,model.getScanResult());

    }

    @Test
    void testWinner() {
        BattleshipModel model = new BattleshipModel();
        model.placeShip("Aircraftcarrier", "1", "1", "horizontal");
        model.placeShip("Dinghy", "2", "2", "horizontal");
        model.placeShip("Clipper", "3", "3", "horizontal");
        model.placeShip("Battleship", "4", "4", "horizontal");
        model.placeShip("Submarine", "5", "5", "horizontal");

        model.playerShot(new Coordinate(1, 1));
        model.playerShot(new Coordinate(2, 1));
        model.playerShot(new Coordinate(3, 1));
        model.playerShot(new Coordinate(4, 1));
        assertEquals("NONE", model.getWinner());
        System.out.println("NEXT");
        // sink carrior
        model.playerShot(new Coordinate(5, 1));
        // sink Dinghy
        model.playerShot(new Coordinate(2, 2));
        // sing clipper
        model.playerShot(new Coordinate(3, 3));
        // sink battleship
        model.playerShot(new Coordinate(4, 4));
        model.playerShot(new Coordinate(5, 4));
        model.playerShot(new Coordinate(6, 4));
        model.playerShot(new Coordinate(7, 4));
        // sing sub
        model.playerShot(new Coordinate(5, 5));
        model.playerShot(new Coordinate(6,5));

        assertEquals("COMPUTER", model.getWinner());

    }



}