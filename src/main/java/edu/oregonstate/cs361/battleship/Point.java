package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 * Changed by Trey 2/1/2017.
 */
 
public class Point {
    private int Across, Down;

    public Point(int across, int down) {
        this.Across = across;
        this.Down = down;
    }

    public int getAcross() {
        return Across;
    }

    public int getDown() {
        return Down;
    }

    public Point traverse(int across, int down) {
        return new Point(getAcross() + across, getDown() + down);
    }

}