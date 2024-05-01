package main.testing;

import main.game.GameBoard;
import main.game.chit.DragonCard;
import main.game.chit.DragonCardManager;
import main.game.creature.Creature;
import main.game.creature.CreatureFactory;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.game.player.PlayerManager;
import main.game.tile.Tile;
import main.game.tile.TileManager;
import main.game.tile.types.CaveTile;
import main.game.tile.types.VolcanoTile;

import java.util.List;

public class GameTesting {
    private final GameBoard gameBoard;

    public GameTesting(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public static void main(String[] args) {
        // Create a GameBoard instance
        GameBoard gameBoard = new GameBoard();

        // Initialize the main.testing environment
        GameTesting gameTesting = new GameTesting(gameBoard);

        // Can test the code by running the main.game and looking for these scenarios
        // When chits are initialised they are always shuffled
        // When chits are clicked, they get flipped to display their creature
        // When chits are flipped by a player, the turn moves to the next player
        // Player turn cycles all the way back to the first player.

        // Generic back end test cases.

        // Run test cases
        gameTesting.testCreatureInitialisation();
        gameTesting.testDragonCardInitialisation();
        gameTesting.testDragonCardShuffle();
        gameTesting.testPlayerInitialisation();
        gameTesting.testTileInitialization();
    }

    private void testCreatureInitialisation() {
        List<Creature> creatures = CreatureFactory.createCreatures();

        for (Creature creature : creatures) {
            // Assert that each creature is not null
            assert creature != null : "Creature initialization test failed: Null creature found";
            assert creature.getCreatureID() < 0: "Creature initialization test failed: ID less than 0";
            assert creature.getCreatureQuantity() < 0: "Creature initialization test failed: Quantity less than 0";
        }

        // If all assertions pass, print a success message
        System.out.println("Creature list initialization test passed successfully");
    }

    private void testDragonCardInitialisation(){
        // Get the DragonCardManager instance from the GameBoard
        DragonCardManager dragonCardManager = gameBoard.getDragonCardManager();

        // Get the list of dragon cards from the DragonCardManager
        List<DragonCard> dragonCards = dragonCardManager.getDragonCards();

        // Assert that the list is not null and contains at least one dragon card
        assert dragonCards != null && !dragonCards.isEmpty() : "Dragon card initialization test failed: No dragon cards found";

        // Optionally, you can add more specific assertions based on the properties of each dragon card
        for (DragonCard dragonCard : dragonCards) {
            // Assert that each dragon card is not null
            assert dragonCard != null : "Dragon card initialization test failed: Null dragon card found";
        }

        // If all assertions pass, print a success message
        System.out.println("Dragon card initialization test passed successfully");
    }

    private void testDragonCardShuffle(){
        // Get the DragonCardManager instance from the GameBoard
        DragonCardManager dragonCardManager = gameBoard.getDragonCardManager();

        List<DragonCard> dragonCardsInitial = dragonCardManager.getDragonCards();
        EventManager.getInstance().notify(EventType.SHUFFLE_CARDS);
        List<DragonCard> dragonCardsFinal = dragonCardManager.getDragonCards();

        assert dragonCardsInitial.size() != dragonCardsFinal.size() : "Dragon card shuffle test failed: Different number of cards";
        assert dragonCardsInitial == dragonCardsFinal : "Dragon card shuffle test failed: Dragon cards not shuffled";

        // If all assertions pass, print a success message
        System.out.println("Dragon card shuffle test passed successfully");
    }

    private void testPlayerInitialisation(){
        // Get the PlayerManager instance from the GameBoard
        PlayerManager playerManager = gameBoard.getPlayerManager();

        // Get the list of players from the PlayerManager
        List<Player> players = playerManager.getPlayerList();

        // Assert that the list is not null and contains at least one player
        assert players != null && !players.isEmpty() : "Player initialization test failed: No players found";

        // Check if the players in the queue are the same as the players in the list
        // Check if the queue is working properly
        for (Player player : players) {
            assert player == playerManager.getCurrPlayer(): "Player queue initialization test failed: Player does not match the queue";
            // Moves the queue to the next player
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
        }

        // If all assertions pass, print a success message
        System.out.println("Player initialization test passed successfully");
    }

    private void testTileInitialization() {
        // Get the TileManager instance from the GameBoard
        TileManager tileManager = gameBoard.getTileManager();

        // Test Cave Tiles
        List<Tile> caveTiles = tileManager.getCaveTileList();
        for (Tile tile : caveTiles) {
            CaveTile caveTile = (CaveTile) tile;
            assert caveTile != null : "Cave tile initialization test failed: Null tile found";
            assert caveTile.getPlayer() != null : "Cave tile initialization test failed: Player assigned unexpectedly";
        }

        // Test Volcano Tiles
        List<Tile> volcanoTiles = tileManager.getVolcanoTileList();
        for (Tile tile : volcanoTiles) {
            VolcanoTile volcanoTile = (VolcanoTile) tile;
            assert volcanoTile != null : "Volcano tile initialization test failed: Null tile found";
        }

        // If all assertions pass, print a success message
        System.out.println("Tile initialization test passed successfully");
    }

}
