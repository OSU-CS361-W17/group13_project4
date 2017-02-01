package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 */
public class Point {
    private int across, down;

    public Point(int across, int down) {
        this.across = across;
        this.down = down;
        validate();
    }
	
	// Default constructor for JSON/GSON testing...
    public Point () {
        this.across = 0;
        this.down = 0;
        validate();
    }

    private void validate() {
        if(across < 0 || across > 9 || down < 0 || down > 9) {
            throw new RuntimeException("Points must have both coordinates exist between 0 and 9, inclusive");
        }
    }

    public int getAcross() {
        return across;
    }

    public int getDown() {
        return down;
    }
}
