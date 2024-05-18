package main.game.command.type.chit;

import main.game.chit.type.CreatureDragonCard;
import main.game.tile.TileNode;

public class CreatureDragonCardCommand extends DragonCardCommand{
    private final CreatureDragonCard creatureDragonCard;

    public CreatureDragonCardCommand(CreatureDragonCard creatureDragonCard){
        super();
        this.creatureDragonCard = creatureDragonCard;
    }

    @Override
    public void execute() {
        TileNode currTileNode = this.gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode();


    }
}
