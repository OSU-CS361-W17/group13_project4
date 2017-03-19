package edu.oregonstate.cs361.battleship;

import java.util.List;

/**
 * Created by Christopher on 3/2/2017.
 */
public class CivilianShip extends Ship {

    public CivilianShip(String n, int l, Coordinate s, Coordinate e) {
        super(n, l, s, e);
    }

    public CivilianShip(String n, int i, ShipLocation s) {
        super(n, i, s);
    }

    /*
    civilian ships are sunk by a single hit.
     */
    @Override
    public boolean isSunk(List<Coordinate> hits) {
        for(Coordinate c : hits) {
            if(covers(c)) {
                System.out.println(name + " is sunk");
                return true;
            }
        }
        System.out.println(name + " is not sunk");
        return false;
    }
}
