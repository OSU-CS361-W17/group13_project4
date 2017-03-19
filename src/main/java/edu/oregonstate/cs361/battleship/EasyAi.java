package edu.oregonstate.cs361.battleship;

import java.util.List;
import java.util.Random;

/**
 * Created by Christopher on 3/18/2017.
 */
public class EasyAi implements ComputerAi {

    @Override
    public ShipLocation getShipPlacementOf(String shipName) {
        if(shipName.equalsIgnoreCase("Computer_AircraftCarrier")) {
            return new ShipLocation( new Coordinate(2,2), true);
        }
        if(shipName.equalsIgnoreCase("Computer_Battleship")) {
            return new ShipLocation( new Coordinate(2,8), false);
        }
        if(shipName.equalsIgnoreCase("Computer_Clipper")) {
            return new ShipLocation( new Coordinate(4,1), true);
        }
        if(shipName.equalsIgnoreCase("Computer_Dinghy")) {
            return new ShipLocation( new Coordinate(7,3), false);
        }
        if(shipName.equalsIgnoreCase("Computer_Submarine")) {
            return new ShipLocation( new Coordinate(9,6), true);
        }
        return null;
    }

    @Override
    public Coordinate fire(List<Coordinate> playerHits, List<Coordinate> playerMisses) {
        int max = 10;
        int min = 1;

        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;

        return new Coordinate(randRow,randCol);
    }

}
