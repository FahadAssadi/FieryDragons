package misc;

import java.util.Map;

public class Settings {
    private static Settings instance;
    private final Map<String, Object> settingsMap;
    private static final String DEFAULT_SETTINGS_CONFIG_PATH = "Project/Sprint2/src/resources/data/config.json";

    public Settings() {
        this.settingsMap = (Map<String, Object>) Utility.getDictionaryFromObj(Utility.readJSONFileToObj(DEFAULT_SETTINGS_CONFIG_PATH));
    }

    public static Object getSetting(String key) {
        if (instance == null) {
            instance = new Settings();
        }

        return instance.settingsMap.get(key);
    }
}
