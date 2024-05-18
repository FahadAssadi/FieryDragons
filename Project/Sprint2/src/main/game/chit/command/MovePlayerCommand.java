package main.game.chit.command;

import main.game.GameBoard;
import main.game.chit.types.CreatureRelatedDragonCard;
import main.game.event.EventManager;
import main.game.event.EventType;
import main.game.player.Player;

/**
 * The MovePlayerCommand class represents a command to move a player on the main.game board.
 * This command is associated with a dragon card that specifies the movement direction and distance.
 */
public class MovePlayerCommand extends DragonCardCommand{
    // The dragon card associated with the move command
    private final CreatureRelatedDragonCard dragonCard;
    // Indicates whether the movement is forward or backward
    private final boolean isForward;

    /**
     * Constructs a MovePlayerCommand object with the specified dragon card and movement direction.
     *
     * @param dragonCard The dragon card associated with the move command.
     * @param isForward  Indicates whether the movement is forward or backward.
     */
    public MovePlayerCommand(CreatureRelatedDragonCard dragonCard, boolean isForward) {
        super();
        this.dragonCard = dragonCard;
        this.isForward = isForward;
    }

    /**
     * Executes the move player command by moving the player on the main.game board.
     *
     * @param gameBoard The main.game board where the command is executed.
     * @param player    The player who triggers the command.
     */
    @Override
    public void execute(GameBoard gameBoard, Player player) {
        int numberOfSteps = this.dragonCard.getAmount();

        // Calculate the number of steps to move forward or backward based on the dragon card
        int moveSteps = this.isForward ? numberOfSteps : (-1 * numberOfSteps);

        boolean hasTurnCompleted = true;

        // Only done if the current player has picked a chit with the wrong creature.
        if (hasTurnCompleted){
            // If the player's turn is completed, notify the event manager
            EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
        }

        // TODO: NEXT SPRINT
    }
}
