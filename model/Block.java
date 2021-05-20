package model;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Block extends Rectangle {
    private double posX;
    public Block(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.WHITE);
    }

    public double getPosX() {
        return posX;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void mySetLayoutX(double x) {
        this.setLayoutX(x);
        this.setPosX(x);
    }
}
