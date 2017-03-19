package edu.oregonstate.cs361.battleship;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Created by Christopher on 3/18/2017.
 */
public class HardAi implements ComputerAi {

    private ArrayList<Coordinate> shotQueue;
    private Coordinate southCell, westCell, northCell, eastCell;
    private int i, across, down;
    private int shotIndex = 3;

    @Override
    public ShipLocation getShipPlacementOf(String shipName) {
        //Randomize ship placement
        return new ShipLocation( new Coordinate(9,6), true);
    }

    @Override
    public Coordinate fire(List<Coordinate> playerHits , List<Coordinate> playerMisses, Coordinate lastShot) {
        for(i = 0; i < playerHits.size(); i++) { // For every player hit.
            if(lastShot.equals(playerHits.get(i))) { // If the last shot was a hit.
                // Fire intelligently.
                if(shotQueue.get(0) == null) { // If the shot queue is empty and the last shot was a hit, fill the shot queue.
                    southCell = new Coordinate(lastShot.getAcross(), (lastShot.getDown() - 1));
                    westCell = new Coordinate((lastShot.getAcross() - 1), lastShot.getDown());
                    northCell = new Coordinate(lastShot.getAcross(), (lastShot.getDown() + 1));
                    eastCell = new Coordinate((lastShot.getAcross() + 1), lastShot.getDown());
                    shotQueue.add(0, southCell);
                    shotQueue.add(1, westCell);
                    shotQueue.add(2, northCell);
                    shotQueue.add(3, eastCell);
                    shotIndex = 3;

                    // Fire the first shot of the shot queue and remove it. Not necessary to add to shot queue but for clarity it was written this way.
                    across = eastCell.getAcross();
                    down = eastCell.getDown();
                    shotQueue.remove(shotIndex);
                    shotIndex--;
                    return new Coordinate(across, down);
                }

            }
        }

        // If the shot queue is empty, fire at random.
        if(shotQueue.get(0) == null) {
            int max = 10;
            int min = 1;

            Random random = new Random();
            int randRow = random.nextInt(max - min + 1) + min;
            int randCol = random.nextInt(max - min + 1) + min;

            return new Coordinate(randRow, randCol);
        }
        else { // Else, the shot queue is not empty and we have fired the first shot out of the shot queue, continue firing around the first hit.
            across = shotQueue.get(shotIndex).getAcross();
            down = shotQueue.get(shotIndex).getDown();
            shotQueue.remove(shotIndex);
            shotIndex--;
            return new Coordinate(across, down);
        }
    }


}

