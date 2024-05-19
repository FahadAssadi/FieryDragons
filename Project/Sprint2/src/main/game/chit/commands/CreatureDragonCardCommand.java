package main.game.chit.commands;

import main.game.GameBoard;
import main.game.chit.type.CreatureDragonCard;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;
import main.game.tile.TileNode;

public class CreatureDragonCardCommand extends DragonCardCommand {
    private final CreatureDragonCard creatureDragonCard;

    public CreatureDragonCardCommand(CreatureDragonCard creatureDragonCard){
        super();
        this.creatureDragonCard = creatureDragonCard;
    }

    @Override
    public void execute(GameBoard gameBoard) {
        // Get the tile the player is standing on
        TileNode currPlayerTileNode = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode();

        // Match it to the chit they selected
        boolean match = creatureDragonCard.getCreature().equals(currPlayerTileNode.getType().getCreature());

        Player currPlayer = currPlayerTileNode.getType().getPlayer();

        if (!match){
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            System.out.println(currPlayer + " did not move, creature not matching");
            return;
        }

        // Get the next tile to move to
        int steps = this.creatureDragonCard.getAmount();


        int totalMoves = currPlayer.getTotalMoves();
        TileNode nextTileNode;

        // Move the player to that tile
        try {
            nextTileNode = currPlayerTileNode.traverseForward(steps, totalMoves);
            currPlayerTileNode.movePlayerToTile(nextTileNode, steps);
        } catch (Exception e) {
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            System.out.println(currPlayer + " did not move");
            return;
        }

        gameBoard.getTileKeeper().getPlayerTileQueue().updateCurrPlayerTileNode(nextTileNode);
        System.out.println(currPlayer + " moved from " + currPlayerTileNode.getTempID() + " to " + nextTileNode.getTempID());
    }
}
