package com.vogtec.hfc.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EmojiLabelExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label emojiLabel = new Label("\uD83C\uDDE8\uD83C\uDDED"); // Unicode 表示的笑脸 Emoji
        emojiLabel.setStyle("-fx-font-size: 36px;"); // 设置字体大小
        StackPane root = new StackPane(emojiLabel);
        primaryStage.setScene(new Scene(root, 200, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
