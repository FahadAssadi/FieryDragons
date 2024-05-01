package main.java.com;

public class GameBoard {

    // Attributes
    private int noOfPlayers;
    private int noOfDragonCards;
    private int noOfVolcanoCards;
    private VolcanoCard[] volcanoCards;
    private DragonToken[] players;
    private DragonCard[] dragonCards;
    private Cave[] caves;
    private PlayerTurnHandler playerTurnHandler;
    private BoardTileManager boardTileManager;

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
        this.dragonCards = {new DragonCard(1, GameCharacters.Salamander),
                            new DragonCard(2, GameCharacters.Salamander),
                            new DragonCard(3, GameCharacters.Salamander)
                            new DragonCard(1, GameCharacters.Bat),
                            new DragonCard(2, GameCharacters.Bat),
                            new DragonCard(3, GameCharacters.Bat),
                            new DragonCard(1, GameCharacters.Spider),
                            new DragonCard(2, GameCharacters.Spider),
                            new DragonCard(3, GameCharacters.Spider),
                            new DragonCard(1, GameCharacters.BabyDragon),
                            new DragonCard(2, GameCharacters.BabyDragon),
                            new DragonCard(3, GameCharacters.BabyDragon),
                            new DragonCard(1, GameCharacters.PirateDragon),
                            new DragonCard(1, GameCharacters.PirateDragon),
                            new DragonCard(2, GameCharacters.PirateDragon),
                            new DragonCard(2, GameCharacters.PirateDragon)
        };




        int i;

        // Setting home caves for each player
        for (i = 0; i < this.dragonCards.length; i++) {
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
