package edu.oregonstate.cs361.battleship;

public class BattleshipModel {
	// Opted for individual objects instead of arrays in the interest of JSON compatibility...
	// If anyone else wants to figure out how to make Ship arrays without compromising JSON structure,
	// then go for it. - Trey, 2/1
    private Ship aircraftCarrier, battleship, cruiser, destroyer, submarine;
    private Ship computer_aircraftCarrier, computer_battleship, computer_cruiser, computer_destroyer, computer_submarine;
    private Shot[] playerHits, playerMisses, computerHits, computerMisses;

    public BattleshipModel() {
        this.playerHits = new Shot[0];
        this.playerMisses = new Shot[0];
        this.computerHits = new Shot[0];
        this.computerMisses = new Shot[0];

        this.aircraftCarrier = new Ship("AircraftCarrier", 5, new Point(0, 0), new Point(0, 0));
        this.battleship = new Ship("Battleship", 4, new Point(0, 0), new Point(0, 0));
        this.cruiser = new Ship("Cruiser", 3, new Point(0, 0), new Point(0, 0));
        this.destroyer = new Ship("Destroyer", 2, new Point(0, 0), new Point(0, 0));
        this.submarine = new Ship("Submarine", 2, new Point(0, 0), new Point(0, 0));
        this.computer_aircraftCarrier = new Ship("Computer_AircraftCarrier", 5, new Point(2, 2), new Point(2, 7));
        this.computer_battleship = new Ship("Computer_Battleship", 4, new Point(2, 8), new Point(6, 8));
        this.computer_cruiser = new Ship("Computer_Cruiser", 3, new Point(4, 1), new Point(4, 4));
        this.computer_destroyer = new Ship("Computer_Destroyer", 2, new Point(7, 3), new Point(7, 5));
        this.computer_submarine = new Ship("Computer_Submarine", 2, new Point(9, 6), new Point(9, 8));
    }

    public Ship getAircraftCarrier() { return aircraftCarrier; }

    public Ship getBattleship() { return battleship; }

    public Ship getDestroyer() { return destroyer; }

    public Ship getCruiser() { return cruiser; }

    public Ship getSubmarine() { return submarine; }

    public Ship getComputer_aircraftCarrier() { return computer_aircraftCarrier; }

    public Ship getComputer_battleship() { return computer_battleship; }

    public Ship getComputer_cruiser() { return computer_cruiser; }

    public Ship getComputer_destroyer() { return computer_destroyer; }

    public Ship getComputer_submarine() { return computer_submarine; }

    public Shot[] getPlayerHits() { return playerHits; }

    public Shot[] getPlayerMisses() { return playerMisses; }

    public Shot[] getCpuHits() { return computerHits; }

    public Shot[] getCpuMisses() { return computerMisses; }

}