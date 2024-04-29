package game.chit.command;

import game.chit.types.CreatureRelatedDragonCard;
import game.player.Player;

public class MovePlayerCommand extends DragonCardCommand{
    private final CreatureRelatedDragonCard dragonCard;
    private final boolean isForward;

    public MovePlayerCommand(CreatureRelatedDragonCard dragonCard, boolean isForward) {
        super();
        this.dragonCard = dragonCard;
        this.isForward = isForward;
    }

    @Override
    public void execute(Player player) {
        int numberOfSteps = this.dragonCard.getAmount();

        int moveSteps = this.isForward ? numberOfSteps : (-1 * numberOfSteps);

        // TODO: NEXT SPRINT
    }
}
