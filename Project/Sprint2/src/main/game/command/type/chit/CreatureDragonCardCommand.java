package main.game.command.type.chit;

import main.game.chit.type.CreatureDragonCard;
import main.game.event.EventManager;
import main.game.event.EventType;

public class CreatureDragonCardCommand extends DragonCardCommand{
    private final CreatureDragonCard creatureDragonCard;

    public CreatureDragonCardCommand(CreatureDragonCard creatureDragonCard){
        this.creatureDragonCard = creatureDragonCard;
    }

    @Override
    public void execute() {
        EventManager.getInstance().notify(EventType.PLAYER_TURN_END);
    }
}
