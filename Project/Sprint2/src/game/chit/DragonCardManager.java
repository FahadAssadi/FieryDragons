package game.chit;

import game.chit.types.CreatureDragonCard;
import game.chit.types.PirateDragonCard;
import game.creature.Creature;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DragonCardManager {
    private final List<DragonCard> dragonCards;

    public DragonCardManager() {
        this.dragonCards = new ArrayList<>();
    }

    public void setDragonCards(List<Creature> creatures) {
        // Create all dragon cards that require creatures
        createCreatureRelatedCards(creatures);
    }

    private void createCreatureRelatedCards(List<Creature> creatures) {
        // Loop through all creatures
        for (Creature creature : creatures) {
            // 1 most of the time except for pirate dragon
            int creatureTimes = creature.getCreatureRepeat();
            int creatureQuantity = creature.getCreatureQuantity();
            String creatureImagePath = "Project/Sprint2/src/resources/assets/pngs/creatures/" + creature.getCreatureName() + "_";

            // Multiple of the same creature and quantity
            for (int j = 0; j < creatureTimes; j++) {
                // Multiple creatures with different quantities
                for (int k = 1; k <= creatureQuantity; k++) {
                    String dragonCardImagePath = creatureImagePath + k + ".png";
                    ImageIcon dragonCardImage = new ImageIcon(dragonCardImagePath);

                    if (creature.isTileable()) {
                        this.dragonCards.add(new CreatureDragonCard(dragonCardImage, creature, k));
                    } else {
                        this.dragonCards.add(new PirateDragonCard(dragonCardImage, creature, k));
                    }
                }
            }

        }
    }

    public List<DragonCard> getDragonCards() {
        return dragonCards;
    }
}
