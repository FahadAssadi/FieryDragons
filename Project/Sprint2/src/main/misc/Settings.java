package main.misc;

import org.json.simple.JSONArray;

import java.io.InputStream;
import java.util.Map;

/**
 * The Settings class manages main.game settings loaded from a configuration file.
 */
public class Settings {
    // Singleton instance of Settings
    private static Settings instance;
    // Map to store settings key-value pairs
    private Map<String, Object> settingsMap;
    // Path to the default settings configuration file
    private static final String DEFAULT_SETTINGS_CONFIG_PATH = "/data/config.json";
    private static final String DEFAULT_VOLCANO_CARD_CONFIG_PATH = "/data/volcanoCard.json";

    /**
     * Private constructor to prevent external instantiation.
     */
    public Settings() {
        // Load settings from the configuration file
        this.settingsMap = (Map<String, Object>) Utility.getDictionaryFromObj(Utility.readJSONFileToObj(getClass().getResourceAsStream(DEFAULT_SETTINGS_CONFIG_PATH)));

        long volcanoCards = (long) this.settingsMap.get("VolcanoCards");
        long animals = (long) this.settingsMap.get("VolcanoCardAnimals");

        long boardSize = volcanoCards * animals;

        long humanPlayers = (long) this.settingsMap.get("HumanPlayers");
        long AIPlayers = (long) this.settingsMap.get("AIPlayers");

        int totalPlayers = (int) (humanPlayers + AIPlayers);
        int playerDistance = (int) (boardSize / totalPlayers);

        // Getting the num of animals for the last volcano card seperately for use in iterators.
        InputStream inputStream = getClass().getResourceAsStream(DEFAULT_VOLCANO_CARD_CONFIG_PATH);
        JSONArray volcanoCardsArray = Utility.readJSONFileToArr(inputStream);

        long lastVolcanoAnimals = (long) Utility.getObjFromArr(volcanoCardsArray, volcanoCardsArray.size() - 1).get("animals");

        this.settingsMap.put("TotalPlayers", totalPlayers);
        this.settingsMap.put("PlayerDistance", playerDistance);
        this.settingsMap.put("BoardSize", boardSize);
        this.settingsMap.put("LastVolcanoAnimals", lastVolcanoAnimals);

    }

    /**
     * Increments the save count in the settings and updates the settings map accordingly.
     *
     */
    public void incrementSaveCount(){
        InputStream inputStream = getClass().getResourceAsStream(DEFAULT_SETTINGS_CONFIG_PATH);
        Utility.incrementValueInJSONFile(inputStream, "Saves");

        this.settingsMap = (Map<String, Object>) Utility.getDictionaryFromObj(Utility.readJSONFileToObj(getClass().getResourceAsStream(DEFAULT_SETTINGS_CONFIG_PATH)));
    }

    /**
     * Returns the singleton instance of the Settings class. If the instance does not exist,
     * it creates a new one.
     *
     * @return  the singleton instance of the Settings class
     */
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }

        return instance;
    }

    /**
     * Retrieves the value of a setting given its key.
     *
     * @param key The key of the setting to retrieve.
     * @return The value of the setting.
     */
    public static Object getSetting(String key) {
        if (instance == null) {
            instance = new Settings();
        }

        return instance.settingsMap.get(key);
    }
}
