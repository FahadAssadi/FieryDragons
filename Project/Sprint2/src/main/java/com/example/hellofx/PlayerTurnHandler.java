package com.example.hellofx;

import com.example.hellofx.DragonToken;

public class PlayerTurnHandler {
    // Class used to generate players, and handle turns and turn outcomes and also flipping chits
    private int currentPlayer;
    private int noOfPlayers;
    private int currentTurn;

    public PlayerTurnHandler(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    // Methods
    public com.example.hellofx.DragonToken[] generatePlayers() {
        // Generating players
        String[] colors = {"red", "blue", "yellow", "white"};
        com.example.hellofx.DragonToken[] players = new com.example.hellofx.DragonToken[this.noOfPlayers];

        return players;
    }

    public void startPlayerTurns() {
        // Method implementation
    }

    public void endTurn() {
        // Method implementation
    }

    public void handleTurn(com.example.hellofx.DragonToken player) {
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
