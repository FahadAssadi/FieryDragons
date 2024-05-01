package main.java.com;

public class Main {
    public static void main(String[] args) {
        int noOfPlayers = 4;
        int noOfDragonCards = 16;
        int noOfVolcanoCards = 24;


        VolcanoCard[] volcanoCards = new VolcanoCard[noOfVolcanoCards]; // 8 volcano cards with 4 cards each
        DragonToken[] players = new DragonToken[noOfPlayers];
        DragonCard[] dragonCards = new DragonCard[noOfDragonCards];
        Cave[] caves = new Cave[noOfPlayers];
        PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler(1, players, 1);
        BoardTileManager boardTileManager = new BoardTileManager(volcanoCards, noOfPlayers, noOfVolcanoCards);
    }
}