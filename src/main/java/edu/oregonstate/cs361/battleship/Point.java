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
        //validate();	
    }

	// Validation has been disabled for dev purposes - this needs to be fixed, currently it prevents
	// the program from converting between Java and JSON.
    private void validate() {
        if(Across < 0 || Across > 9 || Down < 0 || Down > 9) {
            throw new RuntimeException("Points must have both coordinates exist between 0 and 9, inclusive");
        }
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