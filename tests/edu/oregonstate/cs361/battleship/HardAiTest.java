package edu.oregonstate.cs361.battleship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HardAITest {

    @Test
    void fire(){
        Coordinate coor = new Coordinate(1,1);
        Coordinate lastShot = new Coordinate(1,1);
        List<Coordinate> playerHits = new ArrayList<>();
        playerHits.add(0, coor);
        HardAi hardAI = new HardAi();
        hardAI.initiateShotQueue();
        hardAI.fire(playerHits, new ArrayList<>(), lastShot);
        hardAI.fire(new ArrayList<>(), new ArrayList<>(), lastShot);
        hardAI.getShipPlacementOf("Computer_AircraftCarrier");
        hardAI.getShipPlacementOf("Computer_Battleship");
        hardAI.getShipPlacementOf("Computer_Clipper");
        hardAI.getShipPlacementOf("Computer_Submarine");
        hardAI.getShipPlacementOf("Computer_Dinghy");
    }
}
