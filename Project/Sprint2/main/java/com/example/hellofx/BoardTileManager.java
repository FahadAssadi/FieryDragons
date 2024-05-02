package com.example.hellofx;

import com.example.hellofx.Cave;
import com.example.hellofx.GameCharacters;
import com.example.hellofx.VolcanoCard;

public class BoardTileManager {
    private int noOfVolcanoCards;
    private int noOfPlayers;
    private com.example.hellofx.VolcanoCard[] volcanoCards;
    private com.example.hellofx.GameCharacters[] volcanoCardOrder;
    private com.example.hellofx.Cave[] caves;


    public BoardTileManager(int noOfVolcanoCards, int noOfPlayers) {
        this.noOfVolcanoCards = noOfVolcanoCards;
        this.noOfPlayers = noOfPlayers;
    }


    public float[][] calculateVolcanoCardLocations() {
        // Sets coordinates for the volcano cards
        float[][] relativeCoords = {
            {5, 2}, {6, 2}, {7, 2},
            {8, 2}, {9, 3}, {10, 4},
            {10, 5}, {10, 6}, {10, 7},
            {10, 8}, {9, 9}, {8, 10},
            {7, 10}, {6, 10}, {5, 10},
            {4, 10}, {3, 9}, {2, 8},
            {2, 7}, {2, 6}, {2, 5},
            {2, 4}, {3, 3}, {4, 2}
        };
        return relativeCoords;
}

    public void generateCaves() {
        // Generates caves
        com.example.hellofx.Cave[] caves = new com.example.hellofx.Cave[this.noOfPlayers];
        com.example.hellofx.GameCharacters[] caveCharacters = {com.example.hellofx.GameCharacters.Bat, com.example.hellofx.GameCharacters.BabyDragon, com.example.hellofx.GameCharacters.Salamander, com.example.hellofx.GameCharacters.Spider};

        float[][] relativeCoords = { {6, 1}, {11, 6}, {6, 1}, {1, 6}};


        for (int i = 0; i< caves.length; i++) {
            float x = relativeCoords[i][0];
            float y = relativeCoords[i][1];
            caves[i] = new com.example.hellofx.Cave(x,y,caveCharacters[i]);
        }

        this.caves = caves;
    }

    public void generateVolcanoCards() {
        // Method to generate volcano card order
        com.example.hellofx.VolcanoCard[] volcanoCards = new com.example.hellofx.VolcanoCard[noOfVolcanoCards]; // Array to store VolcanoCards

        // Relative Coords
        float[][] relativeCoords = this.calculateVolcanoCardLocations();

        // Define the values to insert
        com.example.hellofx.GameCharacters[] characters = {
            com.example.hellofx.GameCharacters.Bat, com.example.hellofx.GameCharacters.Spider, com.example.hellofx.GameCharacters.BabyDragon,
            com.example.hellofx.GameCharacters.Salamander, com.example.hellofx.GameCharacters.Bat, com.example.hellofx.GameCharacters.Salamander,
            com.example.hellofx.GameCharacters.Spider, com.example.hellofx.GameCharacters.Bat, com.example.hellofx.GameCharacters.Spider,
            com.example.hellofx.GameCharacters.Bat, com.example.hellofx.GameCharacters.Salamander, com.example.hellofx.GameCharacters.Spider,
            com.example.hellofx.GameCharacters.Salamander, com.example.hellofx.GameCharacters.BabyDragon, com.example.hellofx.GameCharacters.Bat,
            com.example.hellofx.GameCharacters.BabyDragon, com.example.hellofx.GameCharacters.Salamander, com.example.hellofx.GameCharacters.Bat,
            com.example.hellofx.GameCharacters.Spider, com.example.hellofx.GameCharacters.BabyDragon, com.example.hellofx.GameCharacters.Salamander,
            com.example.hellofx.GameCharacters.BabyDragon, com.example.hellofx.GameCharacters.Spider, com.example.hellofx.GameCharacters.BabyDragon
        };

        boolean[] indentations = {
            false, true, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false, false, true, false, false, false, false
        };

        // Volcano Card Instantiator Loop
        for (int i = 0; i < volcanoCards.length; i++) {
            GameCharacters character = characters[i];
            Boolean indent = indentations[i];
            float x = relativeCoords[i][0];
            float y = relativeCoords[i][1];
            volcanoCards[i] = new VolcanoCard(x,y,character, indent);
        }
        this.volcanoCards = volcanoCards;
    }

    public void generateTiles() {
        // Method to display tiles
        this.generateVolcanoCards();
        this.generateCaves();
    }

    public Cave[] getCaves() {
        return caves;
    }


}
