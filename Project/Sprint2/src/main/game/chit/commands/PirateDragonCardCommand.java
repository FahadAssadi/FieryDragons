package main.game.chit.commands;

import main.game.GameBoard;
import main.game.chit.type.PirateDragonCard;
import main.game.tile.TileNode;

public class PirateDragonCardCommand extends DragonCardCommand{
    private final PirateDragonCard pirateDragonCard;

    public PirateDragonCardCommand(PirateDragonCard pirateDragonCard) {
        this.pirateDragonCard = pirateDragonCard;
    }

    @Override
    public void execute(GameBoard gameBoard) {
        TileNode currPlayerTileNode = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode();

    }
}
