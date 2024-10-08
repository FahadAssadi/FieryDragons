package main.game.chit.commands;

import main.game.GameBoard;
import main.game.chit.type.PirateDragonCard;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.tile.TileNode;

public class PirateDragonCardCommand extends DragonCardCommand{
    private final PirateDragonCard pirateDragonCard;

    public PirateDragonCardCommand(PirateDragonCard pirateDragonCard) {
        this.pirateDragonCard = pirateDragonCard;
    }

    /**
     * Executes the PirateDragonCardCommand by moving the player on the game board.
     *
     * @param  gameBoard  the game board on which the command is executed
     */
    @Override
    public void execute(GameBoard gameBoard) {
        TileNode currPlayerTileNode = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode();
        int steps = -1 * this.pirateDragonCard.getAmount();
        TileNode nextTileNode;

        try {
            nextTileNode = currPlayerTileNode.traverseBackward(steps);
            currPlayerTileNode.movePlayerToTile(nextTileNode, steps);
        } catch (Exception e) {
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            return;
        }


        gameBoard.getTileKeeper().getPlayerTileQueue().updateCurrPlayerTileNode(nextTileNode);
        EventManager.getInstance().notify(EventType.PLAYER_MOVED);
        EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
    }
}
