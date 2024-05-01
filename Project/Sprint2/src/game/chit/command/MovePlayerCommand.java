package game.chit.command;

import game.GameBoard;
import game.chit.types.CreatureRelatedDragonCard;
import game.event.EventManager;
import game.event.EventType;
import game.player.Player;
import game.tile.TileManager;

public class MovePlayerCommand extends DragonCardCommand{
    private final CreatureRelatedDragonCard dragonCard;
    private final boolean isForward;

    public MovePlayerCommand(CreatureRelatedDragonCard dragonCard, boolean isForward) {
        super();
        this.dragonCard = dragonCard;
        this.isForward = isForward;
    }

    @Override
    public void execute(GameBoard gameBoard, Player player) {
        int numberOfSteps = this.dragonCard.getAmount();

        int moveSteps = this.isForward ? numberOfSteps : (-1 * numberOfSteps);

        boolean hasTurnCompleted = true;

        // Only done if the current player has picked a chit with the wrong creature.
        if (hasTurnCompleted){
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
        }

        // TODO: NEXT SPRINT
        TileManager tileManager = gameBoard.getTileManager();
    }
}
