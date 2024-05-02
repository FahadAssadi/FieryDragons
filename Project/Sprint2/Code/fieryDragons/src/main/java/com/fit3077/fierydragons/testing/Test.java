package com.fit3077.fierydragons.testing;

import com.fit3077.fierydragons.models.board.BoardManager;
import com.fit3077.fierydragons.models.board.Tile;
import com.fit3077.fierydragons.models.creatures.CreatureFactory;
import com.fit3077.fierydragons.models.dragonCards.DragonCard;
import com.fit3077.fierydragons.models.dragonCards.DragonCardsManager;
import com.fit3077.fierydragons.models.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        CreatureFactory creatureFactory = new CreatureFactory();
        PlayerManager playerManager = new PlayerManager();
        BoardManager boardManager = new BoardManager(creatureFactory, playerManager);
        DragonCardsManager dragonCardsManager = new DragonCardsManager(creatureFactory);

        testDragonCardsCreated(dragonCardsManager);
        testShuffle(dragonCardsManager);
        testBoardManager(boardManager);
    }

    public static void testShuffle(DragonCardsManager dragonCardsManager) {
        // Get the initial list of dragon cards
        List<DragonCard> originalList = new ArrayList<>(dragonCardsManager.getDragonCards());

        // Shuffle the dragon cards
        dragonCardsManager.shuffleCards();

        // Get the shuffled list of dragon cards
        List<DragonCard> shuffledList = dragonCardsManager.getDragonCards();

        // Check if the list has been shuffled by comparing with the original list
        if (!originalList.equals(shuffledList)) {
            System.out.println("The dragon cards have been shuffled.");
        } else {
            throw new IllegalStateException("The dragon cards have not been shuffled");
        }
    }

    public static void testDragonCardsCreated(DragonCardsManager dragonCardsManager){
        List<DragonCard> dragonCards = dragonCardsManager.getDragonCards();

        // Check if the dragon cards list is empty
        if (dragonCards.isEmpty()) {
            throw new IllegalStateException("Dragons cards not created");
        }
    }

    public static void testBoardManager(BoardManager boardManager){

        List<Tile> caveTiles = boardManager.getCaveTiles();
        List<Tile> volcanoTiles = boardManager.getVolcanoTiles();

        if (caveTiles.isEmpty()) {
            throw new IllegalStateException("Cave tiles not created");
        }

        if (volcanoTiles.isEmpty()) {
            throw new IllegalStateException("Volcano tiles not created");
        }
    }
}


