package model;

import controller.Controller;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Comparator;

public class Block extends Rectangle implements Comparable<Block> {
    public Block() {}

    public Block(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        this.setFill(Color.BLACK);
    }

    public TranslateTransition moveBlock(double distanceVariation) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(Controller.SPEED), this);
        transition.setByX(distanceVariation);
        return transition;
    }

    @Override
    public int compareTo(Block o) {
        return (int) (this.getHeight() - o.getHeight());
    }
}
