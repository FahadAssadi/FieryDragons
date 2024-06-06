package main.misc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * The Utility class provides various utility methods for common tasks.
 */
public class Utility {
    /**
     * Reads a JSON file and parses its content into a JSONObject.
     *
     * @param inputStream The path to the JSON file.
     * @return The parsed JSONObject.
     */
    public static JSONObject readJSONFileToObj(InputStream inputStream){
        try {
            return (JSONObject) new JSONParser().parse(new InputStreamReader(inputStream));

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads a JSON file and parses its content into a JSONArray.
     *
     * @param inputStream The path to the JSON file.
     * @return The parsed JSONArray.
     */
    public static JSONArray readJSONFileToArr(InputStream inputStream) {
        try {
            return (JSONArray) new JSONParser().parse(new InputStreamReader(inputStream));

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Converts a JSONObject into a Dictionary.
     *
     * @param jsonObject The JSONObject to convert.
     * @return The Dictionary containing key-value pairs from the JSONObject.
     */
    public static Dictionary getDictionaryFromObj(JSONObject jsonObject){
        Dictionary<String, Object> dict = new Hashtable<>();

        for (Object key : jsonObject.keySet()) {
            dict.put((String) key, jsonObject.get(key));
        }

        return dict;
    }

    /**
     * Retrieves a Dictionary object from a JSONArray at the specified index.
     *
     * @param jsonArray The JSONArray to retrieve the object from.
     * @param index     The index of the object in the JSONArray.
     * @return The Dictionary object obtained from the JSONArray at the specified index.
     */
    public static Dictionary getObjFromArr(JSONArray jsonArray, int index){
        JSONObject jsonObject = (JSONObject) jsonArray.get(index);

        return Utility.getDictionaryFromObj(jsonObject);
    }

    /**
     * Scales an ImageIcon to the specified width and height.
     *
     * @param imageIcon The ImageIcon to scale.
     * @param width     The desired width of the scaled image.
     * @param height    The desired height of the scaled image.
     * @return The scaled ImageIcon.
     */
    public static ImageIcon getScaledImage(ImageIcon imageIcon, int width, int height){
        Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }

    public static void writeYamlFile(Map<String, Object> data, String fileName) {
        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        final Yaml yaml = new Yaml(options);

        try {
            String resourcesPath = Utility.class.getClassLoader().getResource("data/").getPath();

            String filePath = resourcesPath + fileName;

            FileWriter writer = new FileWriter(filePath);
            yaml.dump(data, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> readYamlFile(String fileName) {
        Yaml yaml = new Yaml();

        String filePath = "/data/" + fileName;

        InputStream inputStream = Utility.class.getResourceAsStream(filePath);

        return yaml.load(inputStream);
    }

    public static void main(String[] args) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[] { "ONE_HAND", "ONE_EYE" });

        String filename = "save_1.yml";

        writeYamlFile(data, filename);

        System.out.println(readYamlFile(filename));
    }
}
