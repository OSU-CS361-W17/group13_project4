package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 3/2/2017.
 */
public class StealthShip extends Ship {

    public StealthShip(String n, int l, Coordinate s, Coordinate e) {
        super(n, l, s, e);
    }

    public StealthShip(String n, int i, ShipLocation s) {
        super(n, i, s);
    }

    /*
        cannot be found by scans;
     */
    public boolean scan(Coordinate coor) {
        return false;
    }
}
