package edu.oregonstate.cs361.battleship;


public class BattleshipModel {
    private Ship[] playerShips, cpuShips;
    private Shot[] playerHits, playerMisses, cpuHits, cpuMisses;

    public BattleshipModel(Ship[] playerShips, Ship[] cpuShips, Shot[] playerHits, Shot[] playerMisses, Shot[] cpuHits, Shot[] cpuMisses) {
        this.playerHits = playerHits;
        this.cpuHits = cpuHits;
        this.cpuMisses = cpuMisses;
        this.cpuShips = cpuShips;
        this.playerShips = playerShips;
        this.playerMisses = playerMisses;
    }

    public Ship[] getPlayerShips() {
        return playerShips;
    }

    public Ship[] getCpuShips() {
        return cpuShips;
    }

    public Shot[] getPlayerHits() {
        return playerHits;
    }

    public Shot[] getPlayerMisses() {
        return playerMisses;
    }

    public Shot[] getCpuHits() {
        return cpuHits;
    }

    public Shot[] getCpuMisses() {
        return cpuMisses;
    }

}
