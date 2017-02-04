package edu.oregonstate.cs361.battleship;

/**
 * Created by Christopher on 1/31/2017.
 */
public class Shot {
    private int Across;
    private int Down;

    public Shot(Point p) {
        Across = p.getAcross();
        Down = p.getDown();
    }

    public Shot(int Across, int Down) {
        this(new Point(Across, Down));
    }

    public Point getLoc() {
        return new Point(Across, Down);
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Shot)) {
            return false;
        }
       Shot cast = (Shot) other;
        return Across == cast.Across && Down == cast.Down;
    }

}