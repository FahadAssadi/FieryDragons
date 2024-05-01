package main.game.creature;

import main.misc.Settings;
import main.misc.Utility;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * The CreatureFactory class is responsible for creating instances of Creature objects.
 */
public class CreatureFactory {
    // Path to the default creature configuration file
    private static final String DEFAULT_CREATURE_CONFIG_PATH = "/data/creature.json";

    /**
     * Creates a list of Creature objects based on the configuration.
     *
     * @return A list of Creature objects.
     */
    public static List<Creature> createCreatures(){
        List<Creature> creatures = new ArrayList<>();

        // Get the total number of creatures from settings
        long totalCreatures = (long) Settings.getSetting("Creatures");

        // Loop through each creature configuration and create Creature objects
        for (int i = 0; i < totalCreatures; i++){
            // Retrieve creature configuration from JSON file
            Dictionary creatureConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(CreatureFactory.class.getResourceAsStream(DEFAULT_CREATURE_CONFIG_PATH)), i);

            // Extract attributes from the creature configuration
            long creatureID = (long) creatureConfig.get("id");
            String creatureName = (String) creatureConfig.get("name");
            boolean creatureTileable = (long) creatureConfig.get("tileable") == 1;
            long creatureQuantity = (long) creatureConfig.get("maxNumber");
            long creatureTimes = (long) creatureConfig.get("eachNumber");

            // Create a new Creature object and add it to the list
            creatures.add(new Creature(
                    (int) creatureID,
                    creatureName,
                    creatureTileable,
                    (int) creatureQuantity,
                    (int) creatureTimes
            ));
        }

        return creatures;
    }
}
