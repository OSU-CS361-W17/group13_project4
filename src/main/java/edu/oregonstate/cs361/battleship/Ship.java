package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 * Trey - removed ShipType class dependencies 2/1/2017.
 *
 * Represents a single ship on the field.
 */
 
public class Ship {
    private String Name;
    private int length;
    private Point start, end;

    public Ship(String name, int length, Point startPoint, Point endPoint) {
        this.Name = name;
        this.length = length;
        this.start = startPoint;
        this.end = endPoint;
        //validate();
    }

    /**
     * Ensures that a ship is valid. i.e. the endpoints and length make sense, and the shipType matches the length.
	 * This is currently causing errors with JSON formation and needs to be addressed during development of ship placement.
     */
    private void validate() {
        if(start.getAcross() != end.getAcross() && start.getDown() != end.getDown()) {
            throw new RuntimeException("Ship object is invalid. Ensure endpoints are either horizontal, or vertical to each other");
        }
        int distance = Math.abs(start.getAcross() - end.getAcross()) + Math.abs(start.getDown() - end.getDown());
        if(distance != length) {
            throw new RuntimeException("Ship object is invalid. Ensure length between endpoints equals the length of this ship type");
        }
    }

    public String getName() {
        return Name;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}

