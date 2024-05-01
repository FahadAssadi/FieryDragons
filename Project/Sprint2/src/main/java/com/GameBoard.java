package main.java.com;

public class GameBoard {

    // Attributes
    private int noOfPlayers;
    private VolcanoCard[] volcanoCards;
    private DragonToken[] players;
    private DragonCard[] dragonCards;
    private Cave[] caves;
    private PlayerTurnHandler playerTurnHandler;
    private BoardTileManager boardTileManager;

    public GameBoard(int noOfPlayers, VolcanoCard[] volcanoCards, DragonToken[] players, DragonCard[] dragonCards, Cave[] caves, PlayerTurnHandler playerTurnHandler, BoardTileManager boardTileManager) {
        this.noOfPlayers = noOfPlayers;
        this.volcanoCards = volcanoCards;
        this.players = players;
        this.dragonCards = dragonCards;
        this.caves = caves;
        this.playerTurnHandler = playerTurnHandler;
        this.boardTileManager = boardTileManager;
    }

    // Methods
    public void initializeBoard() {
        // Implementation
    }

    public void drawBoardTiles() {
        // Implementation
        this.boardTileManager.displayTiles();
    }

    public void drawDragonCards() {
        // Implementation
    }

    public void placePlayers() {
        // Implementation
    }

    public void beginGame() {
        // Implementation
        this.initializeBoard();
        this.drawBoardTiles();
        this.drawDragonCards();
        this.placePlayers();
        this.beginGame();
    }

}
