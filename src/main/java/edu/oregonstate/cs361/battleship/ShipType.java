package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 */
public enum ShipType {
    AIRCRAFTCARRIER("AircraftCarrior", 5),
    BATTLESHIP("Battleship", 4),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2),
    SUPMARINE("Submarine", 2),
    ;

    private int length;
    private String name;

    private ShipType(String name, int length) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}
