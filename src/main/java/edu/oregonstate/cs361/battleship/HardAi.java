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

    public void initiateShotQueue() {
        shotQueue = new ArrayList<>();
    }

    @Override
    public ShipLocation getShipPlacementOf(String shipName) {
        //Randomize ship placement
        Random random = new Random();

        int isHorizontal = random.nextInt(2);
        int max;
        int min;

        if (shipName == "Computer_AircraftCarrier") {
            max = 6;
            min = 1;
        } else if (shipName == "Computer_Battleship") {
            max = 7;
            min = 1;
        } else if (shipName == "Computer_Clipper") {
            max = 8;
            min = 1;
        } else if (shipName == "Computer_Submarine") {
            max = 9;
            min = 1;
        } else {
            max = 10;
            min = 1;
        }

        if (isHorizontal == 0) {
            int randRow = random.nextInt(10 - 1 + 1) + 1;
            int randCol = random.nextInt(max - min + 1) + min;
            return new ShipLocation( new Coordinate(randCol,randRow), false);
        } else {
            int randRow = random.nextInt(max - min + 1) + min;
            int randCol = random.nextInt(10 - 1 + 1) + 1;
            return new ShipLocation( new Coordinate(randCol,randRow), true);
        }
    }

    @Override
    public Coordinate fire(List<Coordinate> playerHits , List<Coordinate> playerMisses, Coordinate lastShot) {
        for(i = 0; i < playerHits.size(); i++) { // For every player hit.
            if(lastShot.equals(playerHits.get(i))) { // If the last shot was a hit.
                // Fire intelligently - adds four surrounding shots to shot queue
                if (lastShot.getDown() > 1) {
                    southCell = new Coordinate(lastShot.getAcross(), (lastShot.getDown() - 1));
                    shotQueue.add(southCell);
                }
                if (lastShot.getAcross() > 1) {
                    westCell = new Coordinate((lastShot.getAcross() - 1), lastShot.getDown());
                    shotQueue.add(westCell);
                }
                if (lastShot.getDown() < 10) {
                    northCell = new Coordinate(lastShot.getAcross(), (lastShot.getDown() + 1));
                    shotQueue.add(northCell);
                }
                if (lastShot.getAcross() < 10) {
                    eastCell = new Coordinate((lastShot.getAcross() + 1), lastShot.getDown());
                    shotQueue.add(eastCell);
                }
            }
        }
        // If the shot queue is empty, fire at random.
        if(shotQueue.isEmpty()) {
            int max = 10;
            int min = 1;

            Random random = new Random();
            int randRow = random.nextInt(max - min + 1) + min;
            int randCol = random.nextInt(max - min + 1) + min;

            return new Coordinate(randRow, randCol);
        }
        else { // Else, the shot queue is not empty and we have fired the first shot out of the shot queue, continue firing around the first hit.
            across = shotQueue.get(shotQueue.size() - 1).getAcross();
            down = shotQueue.get(shotQueue.size() - 1).getDown();
            shotQueue.remove(shotQueue.size() - 1);
            return new Coordinate(across, down);
        }
    }
}

