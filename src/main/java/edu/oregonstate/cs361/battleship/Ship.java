package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 *
 * Represents a single ship on the field.
 */
public class Ship {
    private ShipType shipType;
    private Point startPoint, endPoint;

    public Ship(ShipType shipType, Point startPoint, Point endPoint) {
        this.shipType = shipType;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        validate();
    }

    /**
     * Ensures that a ship is valid. i.e. the endpoints and length make sense, and the shipType matches the length.
     */
    private void validate() {
        if(startPoint.getAcross() != endPoint.getAcross() && startPoint.getDown() != endPoint.getDown()) {
            throw new RuntimeException("Ship object is invalid. Ensure endpoints are either horizontal, or vertical to each other");
        }
        int distance = Math.abs(startPoint.getAcross() - endPoint.getAcross()) + Math.abs(startPoint.getDown() - endPoint.getDown());
        if(distance != shipType.getLength()) {
            throw new RuntimeException("Ship object is invalid. Ensure length between endpoints equals the length of this ship type");
        }
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
