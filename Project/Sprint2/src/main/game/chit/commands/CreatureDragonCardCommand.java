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
        TileNode currPlayerTileNode = gameBoard.getTileKeeper().getPlayerTileQueue().getCurrPlayerTileNode();

        boolean match = creatureDragonCard.getCreature().equals(currPlayerTileNode.getType().getCreature());

        if (!match){
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
            return;
        }

    }
}
