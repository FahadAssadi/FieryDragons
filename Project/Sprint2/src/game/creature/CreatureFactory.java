package game.creature;

import misc.Settings;
import misc.Utility;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class CreatureFactory {
    private static final String DEFAULT_CREATURE_CONFIG_PATH = "Project/Sprint2/src/resources/data/creature.json";

    public static List<Creature> createCreatures(){
        List<Creature> creatures = new ArrayList<>();

        long totalCreatures = (long) Settings.getSetting("Creatures");

        for (int i = 0; i < totalCreatures; i++){
            Dictionary creatureConfig = Utility.getObjFromArr(Utility.readJSONFileToArr(DEFAULT_CREATURE_CONFIG_PATH), i);

            long creatureID = (long) creatureConfig.get("id");
            String creatureName = (String) creatureConfig.get("name");
            boolean creatureTileable = (long) creatureConfig.get("tileable") == 1;
            long creatureQuantity = (long) creatureConfig.get("maxNumber");
            long creatureTimes = (long) creatureConfig.get("eachNumber");

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
