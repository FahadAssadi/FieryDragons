package main.game.chit.iterators;

import main.game.chit.DragonCard;
import main.game.chit.type.BackwardDragonCard;
import main.game.chit.type.CreatureDragonCard;
import main.game.chit.type.PirateDragonCard;
import main.game.creature.Creature;
import main.game.creature.iterators.CreatureIterable;
import main.game.snapshot.Memento;

import javax.swing.*;
import java.util.*;

public class DragonCardIterable implements Iterable<DragonCard>, Memento {
    private final List<DragonCard> dragonCards = new ArrayList<>();

    public DragonCardIterable(CreatureIterable creatureIterable) {
        createCreatureRelatedCards(creatureIterable);
        createOtherDragonCards();
        Collections.shuffle(dragonCards);
    }

    public DragonCardIterable(Map<String , Object> saveMap, CreatureIterable creatureIterable) {
        List<Map<String, Object>> dragonCardArray = (List<Map<String, Object>>) saveMap.get("dragonCards");

        loadCreatureRelatedDragonCards(dragonCardArray, creatureIterable);
    }

    @Override
    public Iterator<DragonCard> iterator() {
        return dragonCards.iterator();
    }

    private void createCreatureRelatedCards(CreatureIterable creatureIterable) {
        // Loop through all creatures
        for (Creature creature : creatureIterable) {
            int creatureTimes = creature.getCreatureRepeat();
            int creatureQuantity = creature.getCreatureQuantity();

            // Multiple of the same creature and quantity
            for (int j = 0; j < creatureTimes; j++) {
                // Multiple creatures with different quantities
                for (int k = 1; k <= creatureQuantity; k++) {
                    createRelatedCard(creature, k);
                }
            }

        }
    }

    private void createOtherDragonCards(){
        String dragonCardImagePath = "/assets/pngs/dragoncard/BackwardsDragon.png";
        ImageIcon dragonCardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(dragonCardImagePath)));
        dragonCards.add(new BackwardDragonCard(dragonCardImage));
        dragonCards.add(new BackwardDragonCard(dragonCardImage));
    }

    private void createRelatedCard(Creature creature, int k) {
        String creatureImagePath = "/assets/pngs/creatures/" + creature.getCreatureName() + "_";

        String dragonCardImagePath = creatureImagePath + k + ".png";
        ImageIcon dragonCardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(dragonCardImagePath)));

        if (creature.isTileable()) {
            dragonCards.add(new CreatureDragonCard(dragonCardImage, creature, k));
        } else {
            dragonCards.add(new PirateDragonCard(dragonCardImage, creature, k));
        }
    }

    private void loadCreatureRelatedDragonCards(List<Map<String, Object>> dragonCardArray, CreatureIterable creatureIterable) {
        for (Map<String, Object> dragonCardSaveMap : dragonCardArray) {
            boolean hasCreature = (boolean) dragonCardSaveMap.get("hasCreature");

            if (hasCreature) {
                int dragonCreatureID = (int) dragonCardSaveMap.get("creature");
                int dragonCreatureAmount = (int) dragonCardSaveMap.get("amount");

                // Loop through all creatures
                for (Creature creature : creatureIterable) {
                    if (creature.getCreatureID() != dragonCreatureID){
                        continue;
                    }

                    String creatureImagePath = "/assets/pngs/creatures/" + creature.getCreatureName() + "_";

                    String dragonCardImagePath = creatureImagePath + dragonCreatureAmount + ".png";
                    ImageIcon dragonCardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(dragonCardImagePath)));

                    if (creature.isTileable()) {
                        dragonCards.add(new CreatureDragonCard(dragonCardImage, creature, dragonCreatureAmount));
                    } else {
                        dragonCards.add(new PirateDragonCard(dragonCardImage, creature, dragonCreatureAmount));
                    }
                }
            } else {
                String dragonCardImagePath = "/assets/pngs/dragoncard/BackwardsDragon.png";
                ImageIcon dragonCardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource(dragonCardImagePath)));
                dragonCards.add(new BackwardDragonCard(dragonCardImage));
            }

        }

    }

    @Override
    public Map<String , Object> save(Map<String , Object> map) {
        // Create a loop that calls the save for the individual dragon cards
        List<Map<String , Object>> dragonCardsSaveList = new ArrayList<>();

        for (DragonCard dragonCard : this.dragonCards) {
            dragonCardsSaveList.add(dragonCard.save(new LinkedHashMap<>()));
        }

        map.put("dragonCards", dragonCardsSaveList);
        return map;
    }

}