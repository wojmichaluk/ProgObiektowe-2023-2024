package agh.ics.oop.model;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WorldElementBox {

    private VBox cellDisplay = new VBox();

    public WorldElementBox(WorldElement worldElement) {
        Image image = new Image(worldElement.imagePath());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20.0);
        imageView.setFitHeight(20.0);
        Label label = new Label(worldElement.toString());
        label.setFont(new Font(7));
        cellDisplay.getChildren().add(imageView);
        cellDisplay.getChildren().add(label);
        cellDisplay.setAlignment(Pos.CENTER);
    }

    public VBox getCellDisplay() {
        return cellDisplay;
    }
}
