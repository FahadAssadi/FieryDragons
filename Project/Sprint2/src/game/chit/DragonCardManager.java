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

/**
 * The DragonCardManager class manages dragon cards in the game.
 * It handles the creation, shuffling, and management of dragon cards.
 */
public class DragonCardManager implements EventListener {
    // List to store all dragon cards
    private final List<DragonCard> dragonCards;

    // Currently active dragon card
    private DragonCard currDragonCard;

    /**
     * Constructs a DragonCardManager object.
     * Subscribes to the SHUFFLE_CARDS event.
     */
    public DragonCardManager() {
        this.dragonCards = new ArrayList<>();
        EventManager.getInstance().subscribe(EventType.SHUFFLE_CARDS, this);
    }

    /**
     * Sets the currently active dragon card.
     *
     * @param dragonCard The dragon card to set as active.
     */
    public void setCurrDragonCard(DragonCard dragonCard) {
        this.currDragonCard = dragonCard;
    }

    /**
     * Sets the dragon cards based on the given list of creatures.
     *
     * @param creatures The list of creatures to create dragon cards from.
     */
    public void setDragonCards(List<Creature> creatures) {
        createCreatureRelatedCards(creatures);
    }

    /**
     * Creates creature-related dragon cards based on the given list of creatures.
     *
     * @param creatures The list of creatures to create dragon cards from.
     */
    private void createCreatureRelatedCards(List<Creature> creatures) {
        // Loop through all creatures
        for (Creature creature : creatures) {
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

    /**
     * Updates the dragon card manager in response to game events.
     * In this case, shuffles the dragon cards.
     *
     * @param eventType The type of event that occurred.
     */
    @Override
    public void update(EventType eventType) {
        shuffleDragonCards();
    }

    /**
     * Shuffles the dragon cards.
     */
    public void shuffleDragonCards() {
        Collections.shuffle(this.dragonCards);
    }

    /**
     * Retrieves the list of dragon cards.
     *
     * @return The list of dragon cards.
     */
    public List<DragonCard> getDragonCards() {
        return dragonCards;
    }

    /**
     * Retrieves the currently active dragon card.
     *
     * @return The currently active dragon card.
     */
    public DragonCard getCurrDragonCard() {
        return currDragonCard;
    }
}
