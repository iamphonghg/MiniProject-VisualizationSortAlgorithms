package model;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Block extends Rectangle {

    public Block(double width, double height, Insets margin) {
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.WHITE);
        HBox.setMargin(this, margin);
    }
}
