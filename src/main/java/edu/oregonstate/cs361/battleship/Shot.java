package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 */
public class Shot {
    private Point loc;

    public Shot(Point p) {
        loc = p;
        validate();
    }

    private void validate() {
        if(loc == null) {
            throw new RuntimeException("The given point for a shot cannot be null!");
        }
    }

    public Shot(int across, int down) {
        this(new Point(across, down));
    }

    public Point getLoc() {
        return loc;
    }

    public int getAcross() {
        return loc.getAcross();
    }

    public int getDown() {
        return loc.getDown();
    }

}
