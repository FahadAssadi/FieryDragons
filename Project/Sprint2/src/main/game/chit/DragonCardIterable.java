package main.game.chit;

import main.game.chit.types.CreatureDragonCard;
import main.game.chit.types.PirateDragonCard;
import main.game.creature.Creature;
import main.game.creature.iterators.CreatureIterable;

import javax.swing.*;
import java.util.*;

public class DragonCardIterable implements Iterable<DragonCard> {
    private final List<DragonCard> dragonCards;

    public DragonCardIterable(CreatureIterable creatureIterable) {
        dragonCards = new ArrayList<>();
        List<Creature> creatures = new ArrayList<>();
        for (Creature creature : creatureIterable) {
            creatures.add(creature);
        }
        createCreatureRelatedCards(creatures);
    }

    @Override
    public Iterator<DragonCard> iterator() {
        return dragonCards.iterator();
    }

    private void createCreatureRelatedCards(List<Creature> creatures) {
        // Loop through all creatures
        for (Creature creature : creatures) {
            int creatureTimes = creature.getCreatureRepeat();
            int creatureQuantity = creature.getCreatureQuantity();

            String creatureImagePath = "/assets/pngs/creatures/" + creature.getCreatureName() + "_";

            // Multiple of the same creature and quantity
            for (int j = 0; j < creatureTimes; j++) {
                // Multiple creatures with different quantities
                for (int k = 1; k <= creatureQuantity; k++) {
                    String dragonCardImagePath = creatureImagePath + k + ".png";
                    ImageIcon dragonCardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(dragonCardImagePath)));

                    if (creature.isTileable()) {
                        dragonCards.add(new CreatureDragonCard(dragonCardImage, creature, k));
                    } else {
                        dragonCards.add(new PirateDragonCard(dragonCardImage, creature, k));
                    }
                }
            }
        }
    }
}