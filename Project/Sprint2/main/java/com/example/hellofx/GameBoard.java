package com.example.hellofx;

import com.example.hellofx.ArrayShuffler;
import com.example.hellofx.BoardTileManager;
import com.example.hellofx.Cave;
import com.example.hellofx.DragonCard;
import com.example.hellofx.DragonToken;
import com.example.hellofx.GameCharacters;
import com.example.hellofx.PlayerTurnHandler;
import com.example.hellofx.VolcanoCard;

public class GameBoard {

    // Attributes
    private int noOfPlayers;
    private int noOfDragonCards;
    private int noOfVolcanoCards;
    private VolcanoCard[] volcanoCards;
    private DragonToken[] players;
    private com.example.hellofx.DragonCard[] dragonCards;
    private Cave[] caves;
    private com.example.hellofx.PlayerTurnHandler playerTurnHandler;
    private com.example.hellofx.BoardTileManager boardTileManager;

    public GameBoard(int noOfPlayers, int noOfDragonCards, int noOfVolcanoCards) {
        this.noOfPlayers = noOfPlayers;
        this.noOfDragonCards = noOfDragonCards;
        this.noOfVolcanoCards = noOfVolcanoCards;
        this.playerTurnHandler = new PlayerTurnHandler(noOfPlayers);
        this.boardTileManager = new BoardTileManager(this.noOfVolcanoCards, this.noOfPlayers);

    }

    // Methods
    public void initializeBoard() {
        // Implementation
        this.drawBoardTiles();
        this.drawDragonCards();
        this.placePlayers();

    }

    public void drawBoardTiles() {
        // Implementation
        this.boardTileManager.generateTiles();
    }

    public void drawDragonCards() {
        // creating dragon cards and setting locations
        this.dragonCards = new com.example.hellofx.DragonCard[] {new com.example.hellofx.DragonCard(1, com.example.hellofx.GameCharacters.Salamander),
                            new com.example.hellofx.DragonCard(2, com.example.hellofx.GameCharacters.Salamander),
                            new com.example.hellofx.DragonCard(3, com.example.hellofx.GameCharacters.Salamander),
                            new com.example.hellofx.DragonCard(1, com.example.hellofx.GameCharacters.Bat),
                            new com.example.hellofx.DragonCard(2, com.example.hellofx.GameCharacters.Bat),
                            new com.example.hellofx.DragonCard(3, com.example.hellofx.GameCharacters.Bat),
                            new com.example.hellofx.DragonCard(1, com.example.hellofx.GameCharacters.Spider),
                            new com.example.hellofx.DragonCard(2, com.example.hellofx.GameCharacters.Spider),
                            new com.example.hellofx.DragonCard(3, com.example.hellofx.GameCharacters.Spider),
                            new com.example.hellofx.DragonCard(1, com.example.hellofx.GameCharacters.BabyDragon),
                            new com.example.hellofx.DragonCard(2, com.example.hellofx.GameCharacters.BabyDragon),
                            new com.example.hellofx.DragonCard(3, com.example.hellofx.GameCharacters.BabyDragon),
                            new com.example.hellofx.DragonCard(1, com.example.hellofx.GameCharacters.PirateDragon),
                            new com.example.hellofx.DragonCard(1, com.example.hellofx.GameCharacters.PirateDragon),
                            new com.example.hellofx.DragonCard(2, com.example.hellofx.GameCharacters.PirateDragon),
                            new DragonCard(2, GameCharacters.PirateDragon)
        };

        // random coordinates inside circle 11x11 grid
        float[][] relativeCoords = {
            {6, 3}, {5, 4}, {7, 4}, {4, 5},
            {6, 5}, {8, 5}, {3, 6}, {5, 6},
            {7, 6}, {9, 6}, {4, 7}, {6, 7},
            {8, 7}, {5, 8}, {7, 8}, {6, 9}
        };
        // randomly rearrange coordinates
        ArrayShuffler.shuffleArray(relativeCoords);

        int i;
        // Setting random locations
        for (i = 0; i < this.dragonCards.length; i++) {
            float x;
            float y;
            x = relativeCoords[i][0];
            y = relativeCoords[i][1];
            this.dragonCards[i].setPos(x, y);
        }

    }


    public void placePlayers() {
        // Generates players and sets home caves and moves to them
        this.players = this.playerTurnHandler.generatePlayers();
        int i;

        // Setting home caves for each player
        for (i = 0; i < this.players.length; i++) {
            this.players[i].setHomeCave(this.boardTileManager.getCaves()[i]);
            this.players[i].move(this.boardTileManager.getCaves()[i]);
        }
    }

    public void beginGame() {
        // Implementation
        this.initializeBoard();
        this.playerTurnHandler.startPlayerTurns();
    }

}
