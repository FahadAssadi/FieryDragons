package main.game.creature.iterators;

import main.game.creature.Creature;
import main.misc.Settings;
import main.misc.Utility;

import java.util.*;

public class CreatureIterable implements Iterable<Creature> {
    private List<Creature> creatures;

    private static final String DEFAULT_CREATURE_CONFIG_PATH = "/data/creature.json";

    public CreatureIterable() {
        this.createCreatures();
    }

    /**
     * Creates a list of Creature objects based on the configuration.
     *
     */
    private void createCreatures(){
        this.creatures = new ArrayList<>();

        // Get the total number of creatures from settings
        long totalCreatures = (long) Settings.getSetting("Creatures");

        // Loop through each creature configuration and create Creature objects
        for (int i = 0; i < totalCreatures; i++){
            // Retrieve creature configuration from JSON file
            Dictionary creatureConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(getClass().getResourceAsStream(DEFAULT_CREATURE_CONFIG_PATH)), i);

            // Extract attributes from the creature configuration
            long creatureID = (long) creatureConfig.get("id");
            String creatureName = (String) creatureConfig.get("name");
            boolean creatureTileable = (long) creatureConfig.get("tileable") == 1;
            long creatureQuantity = (long) creatureConfig.get("maxNumber");
            long creatureTimes = (long) creatureConfig.get("eachNumber");

            // Create a new Creature object and add it to the list
            this.creatures.add(new Creature(
                    (int) creatureID,
                    creatureName,
                    creatureTileable,
                    (int) creatureQuantity,
                    (int) creatureTimes
            ));
        }
    }

    @Override
    public Iterator<Creature> iterator() {
        return creatures.iterator();
    }

}