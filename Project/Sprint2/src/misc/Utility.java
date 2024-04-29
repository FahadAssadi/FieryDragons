package misc;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class Utility {
    public static JSONObject readJSONFileToObj(String filePath){
        try {
            return (JSONObject) new JSONParser().parse(new FileReader(filePath));

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray readJSONFileToArr(String filePath) {

        try {
            return (JSONArray) new JSONParser().parse(new FileReader(filePath));

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public static Dictionary getDictionaryFromObj(JSONObject jsonObject){
        Dictionary<String, Object> dict = new Hashtable<>();

        for (Object key : jsonObject.keySet()) {
            dict.put((String) key, jsonObject.get(key));
        }

        return dict;
    }

    public static Dictionary getObjFromArr(JSONArray jsonArray, int index){
        JSONObject jsonObject = (JSONObject) jsonArray.get(index);

        return Utility.getDictionaryFromObj(jsonObject);
    }

    public static ImageIcon getScaledImage(ImageIcon imageIcon, int width, int height){
        Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }
}
