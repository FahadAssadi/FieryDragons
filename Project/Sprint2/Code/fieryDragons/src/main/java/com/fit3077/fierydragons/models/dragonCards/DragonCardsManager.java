package com.fit3077.fierydragons.models.dragonCards;

import com.fit3077.fierydragons.Application;
import com.fit3077.fierydragons.models.creatures.Creature;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragonCardsManager {
    List<DragonCard> dragonCards;
    public DragonCardsManager(CreatureFactory creatureFactory){
        createDragonCards(creatureFactory);
    }
    void createDragonCards(CreatureFactory creatureFactory) {
        dragonCards = new ArrayList<DragonCard>();
        String fileName = "data/dragonCards.json";
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
                int moves = Math.abs(jsonObject.getInt("moves"));
                Creature creature = creatureFactory.getCreatureByName(name);
                if (creature != null) {
                    String imagePath = creature.getImagePath();
                    if (moves != 2) {
                        imagePath = imagePath.replaceAll("\\.png$", "_" + moves + ".png");
                    }
                    DragonCard dragonCard = new StandardDragonCard(null, moves, creature, imagePath);
                    dragonCards.add(dragonCard);
                } else {
                    throw new IllegalArgumentException("Creature not found: " + name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void shuffleCards() {
        Collections.shuffle(dragonCards);
    }

    public List<DragonCard> getDragonCards() {
        return dragonCards;
    }
}
