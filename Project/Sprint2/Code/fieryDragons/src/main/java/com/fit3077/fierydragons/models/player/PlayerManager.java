package com.fit3077.fierydragons.models.player;

import com.fit3077.fierydragons.Application;
import com.fit3077.fierydragons.models.actions.Action;
import com.fit3077.fierydragons.models.event.EventType;
import com.fit3077.fierydragons.models.event.Subscriber;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager implements Subscriber {
    private List<Player> players;
    private int currentPlayerIndex;

    public PlayerManager() {
        createPlayersList();
        this.currentPlayerIndex = 0;
    }

    void createPlayersList() {
        players = new ArrayList<>();
        String fileName = "data/players.json";
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
                String playerName = jsonArray.getString(i);
                // Assuming Player class has a constructor that takes name as argument
                Player player = new Player(playerName);
                players.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        System.out.println("Player turn changed");
    }

    public List<Player> getPlayers() {
        return players;
    }
}
