package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 3/18/2017.
 */
public class ShipLocation {
    private Coordinate start;
    private boolean isHorizontal;

    public ShipLocation(Coordinate start, Boolean isHorizontal) {
        this.start = start;
        this.isHorizontal = isHorizontal;
    }

    public Coordinate getStart() {
        return start;
    }

    public boolean getIsHorizontal() {
        return isHorizontal;
    }

}
