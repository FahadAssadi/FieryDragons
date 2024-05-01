package main.java.com;

public class Main {
    public static void main(String[] args) {
        int noOfPlayers = 4;
        int noOfDragonCards = 16;
        int noOfVolcanoCards = 24;

        GameBoard gameBoard = new GameBoard(noOfPlayers, noOfDragonCards, noOfVolcanoCards);
        gameBoard.beginGame();
    }
}