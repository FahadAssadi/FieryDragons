package com.fit3077.fierydragons.models.board;

import com.fit3077.fierydragons.Application;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BoardManager {
    List<Tile> volcanoTiles;
    List<Tile> caveTiles;
    private final CreatureFactory creatureFactory;

    public BoardManager(CreatureFactory creatureFactory) {
        this.creatureFactory = creatureFactory;
        this.caveTiles = new ArrayList<>();
        this.volcanoTiles = new ArrayList<>();
        loadTiles();
    }

    private void loadTiles() {
        String fileName = "data/Tiles.json";
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
            JSONObject jsonObject = new JSONObject(jsonText.toString());
            JSONArray caves = jsonObject.getJSONArray("caves");
            for (int i = 0; i < caves.length(); i++) {
                String creatureName = caves.getString(i);
                caveTiles.add(new CaveTile(creatureFactory.getCreatureByName(creatureName)));
            }

            JSONArray volcanoTilesArray = jsonObject.getJSONArray("volcanoTiles");
            for (int i = 0; i < volcanoTilesArray.length(); i++) {
                String creatureName = volcanoTilesArray.getString(i);
                volcanoTiles.add(new VolcanoTile(creatureFactory.getCreatureByName(creatureName)));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Tile> getCaveTiles() {
        return caveTiles;
    }

    public List<Tile> getVolcanoTiles() {
        return volcanoTiles;
    }

    boolean movePlayer () {
        return false;
    }
}
