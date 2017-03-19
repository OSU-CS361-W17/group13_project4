package edu.oregonstate.cs361.battleship;

import java.util.List;

/**
 * Created by Christopher on 3/18/2017.
 */
public interface ComputerAi
{

    public abstract ShipLocation getShipPlacementOf(String shipName);

    public abstract Coordinate fire(List<Coordinate> playerHits, List<Coordinate> playerMisses);

}
