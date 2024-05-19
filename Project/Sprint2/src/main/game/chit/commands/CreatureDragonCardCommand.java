package main.game.chit.commands;

import main.game.GameBoard;
import main.game.chit.type.CreatureDragonCard;
import main.game.event.EventManager;
import main.game.event.EventType;
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

        if (!match){
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            return;
        }

        // Get the next tile to move to
        int steps = this.creatureDragonCard.getAmount();
        int totalMoves = currPlayerTileNode.getType().getPlayer().getTotalMoves();
        TileNode nextTileNode;

        // Move the player to that tile
        try {
            nextTileNode = currPlayerTileNode.traverseForward(steps, totalMoves);
            currPlayerTileNode.movePlayerToTile(nextTileNode, steps);
        } catch (Exception _) {
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            return;
        }

        gameBoard.getTileKeeper().getPlayerTileQueue().updateCurrPlayerTileNode(nextTileNode);
        System.out.println(nextTileNode.getType().getPlayer());
    }
}
