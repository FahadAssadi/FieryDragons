package com.fit3077.fierydragons.models.creatures;

import com.fit3077.fierydragons.Application;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CreatureFactory {
    private final List<Creature> creatures;

    public CreatureFactory() {
        this.creatures = new ArrayList<>();
        loadCreatures();
    }

    private void loadCreatures() {
        String fileName = "data/creatures.json"; // Location of JSON file in resources
        try (InputStream is = Application.class.getResourceAsStream(fileName)) {
            if (is == null) {
                throw new IOException("Cannot find resource file " + fileName);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }
            JSONArray jsonArray = new JSONArray(jsonText.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String imagePath = jsonObject.getString("image_path");
                creatures.add(new Creature(name, imagePath)); // Assuming Creature constructor accepts name and image path
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public Creature getCreatureByName(String name){
        for (Creature creature : creatures) {
            if (creature.getName().equalsIgnoreCase(name)) {
                return creature;
            }
        }
        return null;
    }
}
