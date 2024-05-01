package main.java.com;

public class PlayerTurnHandler {
    // Class used to generate players, and handle turns and turn outcomes and also flipping chits
    private int currentPlayer;
    private int noOfPlayers;
    private int currentTurn;

    public PlayerTurnHandler(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    // Methods
    public DragonToken[] generatePlayers() {
        // Generating players
        String[] colors = {"red", "blue", "yellow", "white"};
        DragonToken[] players = new DragonToken[this.noOfPlayers];

        return players;
    }

    public void startPlayerTurns() {
        // Method implementation
    }

    public void endTurn() {
        // Method implementation
    }

    public void handleTurn(DragonToken player) {
        // Method implementation
    }

    public void updateLeaderBoard(DragonToken player) {
        // Method implementation
    }

    public void chooseDragonToken() {
        // Method implementation
    }

    public void flipDragontoken() {
        // Method implementation
    }
}
