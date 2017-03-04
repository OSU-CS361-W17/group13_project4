package edu.oregonstate.cs361.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by michaelhilton on 1/4/17.
 */
public class BattleshipModel {

    private Ship aircraftCarrier = new Ship("AircraftCarrier",5, new Coordinate(0,0),new Coordinate(0,0));
    private StealthShip battleship = new StealthShip("Battleship",4, new Coordinate(0,0),new Coordinate(0,0));
    private CivilianShip clipper = new CivilianShip("Clipper",3, new Coordinate(0,0),new Coordinate(0,0));
    private CivilianShip dinghy = new CivilianShip("Dinghy",1, new Coordinate(0,0),new Coordinate(0,0));
    private StealthShip submarine = new StealthShip("Submarine",2, new Coordinate(0,0),new Coordinate(0,0));

    private Ship computer_aircraftCarrier = new Ship("Computer_AircraftCarrier",5, new Coordinate(2,2),new Coordinate(2,6));
    private StealthShip computer_battleship = new StealthShip("Computer_Battleship",4, new Coordinate(2,8),new Coordinate(5,8));
    private CivilianShip computer_clipper = new CivilianShip("Computer_Clipper",3, new Coordinate(4,1),new Coordinate(4,3));
    private CivilianShip computer_dinghy = new CivilianShip("Computer_Dinghy",1, new Coordinate(7,3),new Coordinate(7,4));
    private StealthShip computer_submarine = new StealthShip("Computer_Submarine",2, new Coordinate(9,6),new Coordinate(9,7));

    ArrayList<Coordinate> playerHits;
    private ArrayList<Coordinate> playerMisses;
    ArrayList<Coordinate> computerHits;
    ArrayList<Coordinate> computerMisses;

    boolean scanResult = false;



    public BattleshipModel() {
        playerHits = new ArrayList<>();
        playerMisses= new ArrayList<>();
        computerHits = new ArrayList<>();
        computerMisses= new ArrayList<>();
    }

    public List<Ship> getPlayerShips() {
        List<Ship> list = new ArrayList<>();
        list.add(aircraftCarrier);
        list.add(battleship);
        list.add(clipper);
        list.add(dinghy);
        list.add(submarine);
        return list;
    }
    public List<Ship> getCpuShips() {
        List<Ship> list = new ArrayList<>();
        list.add(computer_aircraftCarrier);
        list.add(computer_battleship);
        list.add(computer_clipper);
        list.add(computer_dinghy);
        list.add(computer_submarine);
        return list;
    }

    public Ship getShip(String shipName) {
        if (shipName.equalsIgnoreCase("aircraftcarrier")) {
            return aircraftCarrier;
        } if(shipName.equalsIgnoreCase("battleship")) {
            return battleship;
        } if(shipName.equalsIgnoreCase("clipper")) {
        return clipper;
        } if(shipName.equalsIgnoreCase("dinghy")) {
            return dinghy;
        }if(shipName.equalsIgnoreCase("submarine")) {
            return submarine;
        } else {
            return null;
        }
    }

    public BattleshipModel placeShip(String shipName, String row, String col, String orientation) {
        int rowint = Integer.parseInt(row);
        int colInt = Integer.parseInt(col);
        if(orientation.equals("vertical")){
            Ship s = this.getShip(shipName);
            if(s == null) {
                return this;
            }
            s.setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint, colInt + s.getLength() - 1));
        }else{
            Ship s = this.getShip(shipName);
            if(s == null) {
                return this;
            }
            s.setLocation(new Coordinate(rowint, colInt), new Coordinate(rowint + s.getLength() - 1, colInt));
        }
        return this;
    }

    public void shootAtComputer(int row, int col) {
        Coordinate coor = new Coordinate(row,col);
        List<Ship> cpuShips = getCpuShips();
        if(computerHits.contains(coor)) {
            return;
        }
        if(computerMisses.contains(coor)) {
            return;
        }
        for(Ship s : cpuShips) {
            if(s.covers(coor)) {
                computerHits.add(coor);
                return;
            }
        }
        computerMisses.add(coor);

    }

    public void shootAtPlayer() {
        int max = 10;
        int min = 1;
        Random random = new Random();
        int randRow = random.nextInt(max - min + 1) + min;
        int randCol = random.nextInt(max - min + 1) + min;

        Coordinate coor = new Coordinate(randRow,randCol);
        playerShot(coor);
    }

    void playerShot(Coordinate coor) {
        List<Ship> playerShips = getPlayerShips();
        if(playerHits.contains(coor)) {
            return;
        }
        if(playerMisses.contains(coor)) {
            return;
        }
        for(Ship s : playerShips) {
            if(s.covers(coor)) {
                playerHits.add(coor);
                return;
            }
        }
        playerMisses.add(coor);
    }


    public void scan(int rowInt, int colInt) {
        Coordinate coor = new Coordinate(rowInt,colInt);
        scanResult = false;
        List<Ship> cpuShips = getCpuShips();
        for(Ship s : cpuShips) {
            if(s.scan(coor)) {
                scanResult = true;
                return;
            }
        }
        // no ship found
        // if scan doesn't find a ship...
        // Do nothing
        /*
        computerMisses.add(new Coordinate(coor.getAcross(), coor.getDown()));
        computerMisses.add(new Coordinate(coor.getAcross()-1, coor.getDown()));
        computerMisses.add(new Coordinate(coor.getAcross()+1, coor.getDown()));
        computerMisses.add(new Coordinate(coor.getAcross(), coor.getDown()-1));
        computerMisses.add(new Coordinate(coor.getAcross(), coor.getDown()+1));
        */
    }

    public boolean getScanResult() {
        return scanResult;
    }

    public String getWinner() {
        boolean playerwins = true;
        boolean cpuwins = true;
        List<Ship> cpuships = getCpuShips();
        List<Ship> playerships = getPlayerShips();
        for(Ship s : cpuships) {
            playerwins &= s.isSunk(computerHits);
        }
        if(playerwins) return "PLAYER";
        for(Ship s : playerships) {
            cpuwins &= s.isSunk(playerHits);
        }
        if(cpuwins) return "COMPUTER";
        return "NONE";
    }
}