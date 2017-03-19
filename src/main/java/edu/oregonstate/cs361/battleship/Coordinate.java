package edu.oregonstate.cs361.battleship;

/**
 * Created by michaelhilton on 1/8/17.
 */
public class Coordinate {
    private int Across;
    private int Down;

    public Coordinate(int letter, int number) {
        Across = letter;
        Down = number;
    }

    public boolean equals(Object other) {
        if(other instanceof Coordinate) {
            return this.Across == ((Coordinate) other).Across && this.Down == ((Coordinate) other).Down;
        }
        return false;
    }

    public int getDown() {
        return Down;
    }

    public void setDown(int down) {
        Down = down;
    }

    public int getAcross() {
        return Across;
    }

    public void setAcross(int across) {
        Across = across;
    }

    public String toString() {
        return ("Coordinate{Across: " + Across + " Down: " + Down + "}");
    }
}
