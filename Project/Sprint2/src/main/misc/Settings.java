package main.misc;

import java.util.Map;

/**
 * The Settings class manages main.game settings loaded from a configuration file.
 */
public class Settings {
    // Singleton instance of Settings
    private static Settings instance;
    // Map to store settings key-value pairs
    private final Map<String, Object> settingsMap;
    // Path to the default settings configuration file
    private static final String DEFAULT_SETTINGS_CONFIG_PATH = "/data/config.json";

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

        this.settingsMap.put("TotalPlayers", totalPlayers);
        this.settingsMap.put("PlayerDistance", playerDistance);
        this.settingsMap.put("BoardSize", boardSize);
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
