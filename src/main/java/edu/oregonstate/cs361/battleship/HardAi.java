package edu.oregonstate.cs361.battleship;

import java.util.List;

/**
 * Created by Christopher on 3/18/2017.
 */
public class HardAi implements ComputerAi {

    @Override
    public ShipLocation getShipPlacementOf(String shipName) {
        //TEMP
        return new ShipLocation(new Coordinate(0,0), true);
    }

    @Override
    public Coordinate fire(List<Coordinate> playerHits , List<Coordinate> playerMisses) {
        //TEMP
        return new Coordinate(0,0);
    }

}
