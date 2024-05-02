package com.example.hellofx;

import com.example.hellofx.BoardTileManagerFX;
import com.example.hellofx.DragonTokenFX;
import com.example.hellofx.TextNode;
import javafx.scene.Group;

public class GameBoardFX {
    private Group root;

    public GameBoardFX(Group root) {
        this.root = root;
    }

    public void draw() {
        com.example.hellofx.TextNode textNode = new TextNode();
        textNode.draw(root);

        com.example.hellofx.BoardTileManagerFX volcano = new BoardTileManagerFX();
        volcano.draw(root);

        com.example.hellofx.DragonTokenFX chit = new DragonTokenFX();
        chit.draw(root);
    }
}
