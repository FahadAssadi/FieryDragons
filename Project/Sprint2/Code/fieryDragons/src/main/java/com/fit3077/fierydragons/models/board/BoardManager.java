package com.fit3077.fierydragons.models.board;

import com.fit3077.fierydragons.Application;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import com.fit3077.fierydragons.models.dragonCards.DragonCard;
import com.fit3077.fierydragons.models.player.Player;
import com.fit3077.fierydragons.models.player.PlayerManager;
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
    List<DragonCard> dragonCards;

    public BoardManager(CreatureFactory creatureFactory, PlayerManager playerManager) {
        this.caveTiles = new ArrayList<>();
        this.volcanoTiles = new ArrayList<>();
        this.dragonCards = new ArrayList<>();
        loadTiles(creatureFactory, playerManager);
    }

    private void loadTiles(CreatureFactory creatureFactory, PlayerManager playerManager) {
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

            // getting players to add to caves
            List <Player> players = playerManager.getPlayers();

            for (int i = 0; i < caves.length(); i++) {
                String creatureName = caves.getString(i);
                caveTiles.add(new CaveTile(creatureFactory.getCreatureByName(creatureName), players.get(i)));
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
