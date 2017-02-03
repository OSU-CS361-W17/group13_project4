package edu.oregonstate.cs361.battleship;

import java.util.LinkedList;
import java.util.List;

public class BattleshipModel {

    private static final String AIRCRAFT_CARRIER = "aircraftCarrier";
    private static final String BATTLESHIP = "battleship";
    private static final String CRUISER = "cruiser";
    private static final String DESTROYER = "destroyer";
    private static final String SUBMARINE = "submarine";
    private static final String CPU_AIRCRAFT_CARRIER = "computer_" + AIRCRAFT_CARRIER;
    private static final String CPU_BATTLESHIP = "computer_" + BATTLESHIP;
    private static final String CPU_CRUISER = "computer_" + CRUISER;
    private static final String CPU_DESTROYER = "computer_" + DESTROYER;
    private static final String CPU_SUBMARINE = "computer_" + SUBMARINE;

    // Opted for individual objects instead of arrays in the interest of JSON compatibility...
    // If anyone else wants to figure out how to make Ship arrays without compromising JSON structure,
    // then go for it. - Trey, 2/1
    private Ship aircraftCarrier, battleship, cruiser, destroyer, submarine;
    private Ship computer_aircraftCarrier, computer_battleship, computer_cruiser, computer_destroyer, computer_submarine;
    private List<Shot> playerHits, playerMisses, computerHits, computerMisses;

    public BattleshipModel() {
        this.playerHits = new LinkedList<>();
        this.playerMisses = new LinkedList<>();
        this.computerHits = new LinkedList<>();
        this.computerMisses = new LinkedList<>();

        this.aircraftCarrier = new Ship(AIRCRAFT_CARRIER, 5, new Point(0, 0), new Point(0, 0));
        this.battleship = new Ship(BATTLESHIP, 4, new Point(0, 0), new Point(0, 0));
        this.cruiser = new Ship(CRUISER, 3, new Point(0, 0), new Point(0, 0));
        this.destroyer = new Ship(DESTROYER, 2, new Point(0, 0), new Point(0, 0));
        this.submarine = new Ship(SUBMARINE, 2, new Point(0, 0), new Point(0, 0));
        this.computer_aircraftCarrier = new Ship(CPU_AIRCRAFT_CARRIER, 0, new Point(0, 0), new Point(0, 0));
        this.computer_battleship = new Ship(CPU_BATTLESHIP, 0, new Point(0, 0), new Point(0, 0));
        this.computer_cruiser = new Ship(CPU_CRUISER, 0, new Point(0, 0), new Point(0, 0));
        this.computer_destroyer = new Ship(CPU_DESTROYER, 0, new Point(0, 0), new Point(0, 0));
        this.computer_submarine = new Ship(CPU_SUBMARINE, 0, new Point(0, 0), new Point(0, 0));
    }

    public Ship getAircraftCarrier() {
        return aircraftCarrier;
    }

    public Ship getBattleship() {
        return battleship;
    }

    public Ship getDestroyer() {
        return destroyer;
    }

    public Ship getCruiser() {
        return cruiser;
    }

    public Ship getSubmarine() {
        return submarine;
    }

    public Ship getComputer_aircraftCarrier() {
        return computer_aircraftCarrier;
    }

    public Ship getComputer_battleship() {
        return computer_battleship;
    }

    public Ship getComputer_cruiser() {
        return computer_cruiser;
    }

    public Ship getComputer_destroyer() {
        return computer_destroyer;
    }

    public Ship getComputer_submarine() {
        return computer_submarine;
    }

    public List<Shot> getPlayerHits() {
        return playerHits;
    }

    public List<Shot> getPlayerMisses() {
        return playerMisses;
    }

    public List<Shot> getCpuHits() {
        return computerHits;
    }

    public List<Shot> getCpuMisses() {
        return computerMisses;
    }

    public int placeShip(String shipName, int across, int down, String orientation) {
        int length = 0;
        switch (shipName) {
            case AIRCRAFT_CARRIER:
            case CPU_AIRCRAFT_CARRIER:
                length = 5;
                break;
            case BATTLESHIP:
            case CPU_BATTLESHIP:
                length = 4;
                break;
            case CRUISER:
            case CPU_CRUISER:
                length = 3;
                break;
            case DESTROYER:
            case SUBMARINE:
            case CPU_SUBMARINE:
            case CPU_DESTROYER:
                length = 2;
                break;
        }
        Point start = new Point(across, down);
        Point end;
        if (orientation.equals("horizontal")) {
            end = start.traverse(0, length - 1);
        } else if (orientation.equals("vertical")) {
            end = start.traverse(length - 1, 0);
        } else {
            throw new RuntimeException("orientation must be horizontal or vertical!");
        }
        Ship temp = new Ship(shipName, length, start, end);

        if (shipName.substring(0, 9).equals("computer_")) {
            if (end.getAcross() > 10 || end.getDown() > 10)
                return 1;

            for (Ship ship : getCpuShips()) {
                if (ship.overLapsWith(temp) && !temp.getName().equals(ship.getName())) {
                    return 1;
                }
            }
        } else {
            for (Ship ship : getPlayerShips()) {
                if (ship.overLapsWith(temp) && !temp.getName().equals(ship.getName())) {
                    throw new RuntimeException("Illegal ship placement: Overlapping ships!");
                }
            }
        }

        switch (shipName) {
            case AIRCRAFT_CARRIER:
                aircraftCarrier = temp;
                break;
            case CPU_AIRCRAFT_CARRIER:
                computer_aircraftCarrier = temp;
                break;
            case BATTLESHIP:
                battleship = temp;
                break;
            case CPU_BATTLESHIP:
                computer_battleship = temp;
                break;
            case CRUISER:
                cruiser = temp;
                break;
            case CPU_CRUISER:
                computer_cruiser = temp;
                break;
            case DESTROYER:
                destroyer = temp;
                break;
            case SUBMARINE:
                submarine = temp;
                break;
            case CPU_SUBMARINE:
                computer_submarine = temp;
                break;
            case CPU_DESTROYER:
                computer_destroyer = temp;
                break;
        }
        return 0;
    }

    public Ship[] getAllShips() {
        Ship[] ships = {
                aircraftCarrier,
                battleship,
                cruiser,
                destroyer,
                submarine,
                computer_aircraftCarrier,
                computer_battleship,
                computer_cruiser,
                computer_destroyer,
                computer_submarine
        };
        return ships;
    }

    public Ship[] getCpuShips() {
        Ship[] ships = {
                computer_aircraftCarrier,
                computer_battleship,
                computer_cruiser,
                computer_destroyer,
                computer_submarine
        };
        return ships;
    }

    public Ship[] getPlayerShips() {
        Ship[] ships = {
                aircraftCarrier,
                battleship,
                cruiser,
                destroyer,
                submarine
        };
        return ships;
    }

    public boolean fireAt(int x, int y) {
        Shot checkShot = new Shot(x, y);

        if ((playerHits.contains(checkShot)) || playerMisses.contains(checkShot)) {
            throw new RuntimeException("You have already shot here.");
        }

        boolean hitAShip = false;
        for (Ship s : getCpuShips()) {
            hitAShip |= s.overLapsWith(checkShot.getLoc());
        }
        if (hitAShip) {
            playerHits.add(checkShot);
        } else {
            playerMisses.add(checkShot);
        }

        if (checkEndGame()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEndGame() {
        return ((playerMisses.size() == 16) || playerHits.size() == 16);
    }
}

