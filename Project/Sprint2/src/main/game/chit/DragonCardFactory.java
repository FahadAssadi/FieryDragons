package main.game.chit;

import main.game.chit.types.CreatureDragonCard;
import main.game.chit.types.PirateDragonCard;
import main.game.creature.Creature;
import main.misc.Settings;

import javax.swing.*;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DragonCardFactory implements Iterator<DragonCard> {
    private final List<Creature> creatures;
    private int cardCount;
    private int creatureIndex;
    private int creatureRepeatIndex;
    private int creatureQuantityIndex;

    public DragonCardFactory(List<Creature> creatureList) {
        this.creatures = creatureList;
        this.cardCount = 0;
        this.creatureIndex = 0;
        this.creatureRepeatIndex = 0;
        this.creatureQuantityIndex = 0;
    }

    @Override
    public boolean hasNext() {
        long boardSize = (long) Settings.getSetting("VolcanoTile");
        return cardCount < boardSize && creatureIndex < creatures.size();
    }

    @Override
    public DragonCard next() {
        Creature currentCreature = creatures.get(creatureIndex);
        int creatureQuantity = currentCreature.getCreatureQuantity();
        int creatureRepeat = currentCreature.getCreatureRepeat();
        String creatureImagePath = "/assets/pngs/creatures/" + currentCreature.getCreatureName() + "_" + (creatureQuantityIndex + 1) + ".png";
        ImageIcon dragonCardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(creatureImagePath)));

        DragonCard dragonCard;
        if (currentCreature.isTileable()) {
            dragonCard = new CreatureDragonCard(dragonCardImage, currentCreature, creatureQuantityIndex + 1);
        } else {
            dragonCard = new PirateDragonCard(dragonCardImage, currentCreature, creatureQuantityIndex + 1);
        }

        creatureQuantityIndex++;
        if (creatureQuantityIndex >= creatureQuantity) {
            creatureQuantityIndex = 0;
            creatureRepeatIndex++;
            if (creatureRepeatIndex >= creatureRepeat) {
                creatureRepeatIndex = 0;
                creatureIndex++;
            }
        }

        cardCount++;
        return dragonCard;
    }
}
