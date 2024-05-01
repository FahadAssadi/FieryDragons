package main.java.com;

public class BoardTileManager {
    private VolcanoCard[] volcanoCards;
    private GameCharacters[] volcanoCardOrder;
    private int noOfVolcanoCards;
    private int noOfPlayers;

    public BoardTileManager(VolcanoCard[] volcanoCards, int noOfVolcanoCards, int noOfPlayers) {
        this.volcanoCards = volcanoCards;
        this.noOfVolcanoCards = noOfVolcanoCards;
        this.noOfPlayers = noOfPlayers;
    }


    public float[][] calculateLocations() {
        // Sets coordinates for the volcano cards
        // Coordinates provided
        float[][] relativeCoords = {
            {4, 1}, {5, 1}, {6, 1},
            {7, 1}, {8, 2}, {9, 3},
            {9, 4}, {9, 5}, {9, 6},
            {9, 7}, {8, 8}, {7, 9},
            {6, 9}, {5, 9}, {4, 9},
            {3, 9}, {2, 8}, {1, 7},
            {1, 6}, {1, 5}, {1, 4},
            {1, 3}, {2, 2}, {3, 1}
        };
        return relativeCoords;
}

    public VolcanoCard[] generateVolcanoCards() {
        // Method to generate volcano card order
        VolcanoCard[] volcanoCards = new VolcanoCard[noOfVolcanoCards]; // Array to store VolcanoCards

        // Relative Coords
        float[][] relativeCoords = this.calculateLocations();


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


        return volcanoCards;
    }

    public void displayTiles() {
        // Method to display tiles
        this.generateVolcanoCards();
    }

}
