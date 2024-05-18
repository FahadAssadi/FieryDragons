package main.game;

import main.game.chit.DragonCard;
import main.game.chit.DragonCardKeeper;
import main.game.creature.CreatureKeeper;
import main.game.event.EventListener;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.tile.TileKeeper;
import main.game.tile.TileNode;

/**
 * The GameBoard class represents the game board and manages game-related entities.
 * It initializes players, tiles, and dragon cards, and handles player turns.
 */
public class GameBoard implements EventListener {
    private final DragonCardKeeper dragonCardKeeper;
    private final TileKeeper tileKeeper;

    /**
     * Constructs a GameBoard object and initializes game-related entities.
     */
    public GameBoard() {
        CreatureKeeper creatureKeeper = new CreatureKeeper();
        this.tileKeeper = new TileKeeper(creatureKeeper.getTileableCreatureIterable());
        this.dragonCardKeeper = new DragonCardKeeper(creatureKeeper.getCreatureIterable());

        EventManager.getInstance().subscribe(EventType.PLAYER_TURN_START, this);
        test();
    }

    public TileKeeper getTileKeeper() {
        return tileKeeper;
    }

    public DragonCardKeeper getDragonCardKeeper() {
        return dragonCardKeeper;
    }

    public void nextPlayerTurn() {
        DragonCard currDragonCard = this.dragonCardKeeper.getSelectedDragonCard();

        currDragonCard.getDragonCardCommand().setGameBoard(this);
        currDragonCard.getDragonCardCommand().execute();
    }

    @Override
    public void update(EventType eventType) {
        if (eventType == EventType.PLAYER_TURN_START){
            this.nextPlayerTurn();
        }
    }

    public void test() {
        TileNode playerNode = tileKeeper.getPlayerTileQueue().getCurrPlayerTileNode();
        int totalMoves = playerNode.getType().getPlayer().getTotalMoves();
        TileNode newNode = playerNode.getNextTile(26, totalMoves);
        System.out.println("starting node: " + playerNode.getTempID() + playerNode.getType());
        System.out.println("ending node: " + newNode.getTempID() + newNode.getType());
    }

    public void testTraverseBackwards(int steps) throws Exception {
        TileNode rootNode = tileKeeper.getVolcanoTileIterable().iterator().next();
        TileNode currentNode = rootNode;
        TileNode curr = tileKeeper.getPlayerTileQueue().getCurrPlayerTileNode();
        curr.getType().getPlayer().getTotalMoves();
        System.out.println("Starting from node: " + currentNode.getTempID());

        for (int i = 0; i < steps; i++) {
            currentNode = currentNode.getNextVolcanoTile();
            System.out.println("Moving forward to node: " + currentNode.getTempID());
        }

        System.out.println("Traversing backwards " + steps + " steps...");
        TileNode newNode = currentNode.traverseBackwards(steps);
        System.out.println("After traversing backwards, the new node is: " + newNode.getTempID());
    }
}
