package main.game.chit.type;

import main.game.chit.DragonCard;
import main.game.chit.commands.BackwardsDragonCardCommand;
import main.game.chit.commands.DragonCardCommand;

import javax.swing.*;
import java.util.Map;

public class BackwardDragonCard extends DragonCard {

    public BackwardDragonCard(ImageIcon dragonCardImage) {
        super(dragonCardImage);
    }

    @Override
    public DragonCardCommand getDragonCardCommand() {
        return new BackwardsDragonCardCommand(this);
    }

    @Override
    public Map<String, Object> save(Map<String, Object> map) {
        map.put("hasCreature", false);

        return map;
    }
}
