package com.example.hellofx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class DragonTokenFX {
    public void draw(Group root) {
        Image chitImage = new Image("chit.png");
        Image chitImgArray[] = {
                new Image("bat.png"),
                new Image("salamander.png"),
                new Image("spider.png"),
                new Image("babyDragon.png"),
                new Image("pirate.png")
        };

        double[][] chitCoords = {
                {655.0, 475.0},
                {620.6230589874906, 580.8013454126451},
                {530.6230589874906, 646.1901729331277},
                {419.37694101250946, 646.1901729331277},
                {329.3769410125095, 580.8013454126452},
                {295.0, 475.0},
                {329.37694101250946, 369.19865458735484},
                {419.37694101250946, 303.80982706687234},
                {530.6230589874905, 303.80982706687234},
                {620.6230589874905, 369.1986545873548},
                {575.0, 475.0},
                {525.0, 561.6025403784439},
                {425.0, 561.6025403784439},
                {375.0, 475.0},
                {424.99999999999994, 388.39745962155615},
                {524.9999999999999, 388.3974596215561},
        };

        for (int i = 0; i < 16; i++) {
            int chitIndex = Math.floorDiv(i, 5);
            ImageView imageView = new ImageView(chitImage);
            Image image2 = chitImgArray[chitIndex];

            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            imageView.setX(chitCoords[i][0]);
            imageView.setY(chitCoords[i][1]);
            root.getChildren().add(imageView);


            // Add event handler to change image on click
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // Change image to image2
                    imageView.setImage(image2);

                    // Start timer to revert image back to chitImage after 3 seconds
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            imageView.setImage(chitImage);
                        }
                    }));
                    timeline.play();
                }
            });
        }
    }
}
