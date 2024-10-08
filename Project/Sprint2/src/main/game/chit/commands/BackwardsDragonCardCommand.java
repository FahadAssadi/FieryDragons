package main.game.chit.commands;

import main.game.GameBoard;
import main.game.chit.DragonCard;
import main.game.chit.type.PirateDragonCard;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.tile.TileNode;

public class BackwardsDragonCardCommand extends DragonCardCommand{
    private final DragonCard dragonCard;

    public BackwardsDragonCardCommand(DragonCard dragonCard) {
        this.dragonCard = dragonCard;
    }

    @Override
    public void execute(GameBoard gameBoard) {
        TileNode currPlayerTileNode = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode();
        TileNode nextTileNode;


        boolean caveFound = false;
        int stepsToCave = 0;
        TileNode currNode = currPlayerTileNode;

        if (currNode.getPreviousTile() != null) {
            while (!caveFound) {
                stepsToCave++;
                // If Tile is next to Cave
                if (currNode.getAdjacentTile() != null) {
                    TileNode caveNode = currNode.getAdjacentTile();
                    // If no player is at cave
                    if (caveNode.getType().getPlayer() == null) {
                        caveFound = true;
                    }
                    System.out.println(caveNode);
                }
                currNode = currNode.getPreviousTile();
            }
        }

        int steps = -1 * stepsToCave + 1;

        try {
            nextTileNode = currPlayerTileNode.traverseBackward(steps);
//            currPlayerTileNode.movePlayerToTile(nextTileNode, steps);
            currPlayerTileNode.movePlayerToTile(nextTileNode.getAdjacentTile(), steps - 1);

            System.out.println(nextTileNode.getAdjacentTile().getType().getPlayer());
//            nextTileNode.movePlayerToTile(nextTileNode.getAdjacentTile(), -1);

        } catch (Exception e) {
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            return;
        }


        gameBoard.getTileKeeper().getPlayerTileQueue().updateCurrPlayerTileNode(nextTileNode.getAdjacentTile());
        EventManager.getInstance().notify(EventType.PLAYER_MOVED);
    }
}
