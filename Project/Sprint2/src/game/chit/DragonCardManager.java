package game.chit;

import game.chit.types.CreatureDragonCard;
import game.chit.types.PirateDragonCard;
import game.creature.Creature;
import game.event.EventListener;
import game.event.EventManager;
import game.event.EventType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragonCardManager implements EventListener {
    private final List<DragonCard> dragonCards;
    private DragonCard currDragonCard;

    public DragonCardManager() {
        this.dragonCards = new ArrayList<>();

        EventManager.getInstance().subscribe(EventType.SHUFFLE_CARDS, this);
    }

    public void setCurrDragonCard(DragonCard dragonCard){
        this.currDragonCard = dragonCard;
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

    @Override
    public void update(EventType eventType) {
        this.shuffleDragonCards();
    }

    public void shuffleDragonCards(){
        Collections.shuffle(this.dragonCards);
    }

    public List<DragonCard> getDragonCards() {
        return dragonCards;
    }

    public DragonCard getCurrDragonCard() {
        return currDragonCard;
    }


}
