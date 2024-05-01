package main.java.com;

public class BoardTileManager {
    private int noOfVolcanoCards;
    private int noOfPlayers;
    private VolcanoCard[] volcanoCards;
    private GameCharacters[] volcanoCardOrder;
    private Cave[] caves;


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
        Cave[] caves = new Cave[this.noOfPlayers];
        GameCharacters[] caveCharacters = {GameCharacters.Bat, GameCharacters.BabyDragon, GameCharacters.Salamander, GameCharacters.Spider};

        float[][] relativeCoords = { {6, 1}, {11, 6}, {6, 1}, {1, 6}};


        for (int i = 0; i< caves.length; i++) {
            float x = relativeCoords[i][0];
            float y = relativeCoords[i][1];
            caves[i] = new Cave(x,y,caveCharacters[i]);
        }

        this.caves = caves;
    }

    public void generateVolcanoCards() {
        // Method to generate volcano card order
        VolcanoCard[] volcanoCards = new VolcanoCard[noOfVolcanoCards]; // Array to store VolcanoCards

        // Relative Coords
        float[][] relativeCoords = this.calculateVolcanoCardLocations();

        // Define the values to insert
        GameCharacters[] characters = {
            GameCharacters.Bat, GameCharacters.Spider, GameCharacters.BabyDragon,
            GameCharacters.Salamander, GameCharacters.Bat, GameCharacters.Salamander,
            GameCharacters.Spider, GameCharacters.Bat, GameCharacters.Spider,
            GameCharacters.Bat, GameCharacters.Salamander, GameCharacters.Spider,
            GameCharacters.Salamander, GameCharacters.BabyDragon, GameCharacters.Bat,
            GameCharacters.BabyDragon, GameCharacters.Salamander, GameCharacters.Bat,
            GameCharacters.Spider, GameCharacters.BabyDragon, GameCharacters.Salamander,
            GameCharacters.BabyDragon, GameCharacters.Spider, GameCharacters.BabyDragon
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
