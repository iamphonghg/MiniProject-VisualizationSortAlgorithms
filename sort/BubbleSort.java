package sort;

import controller.Controller;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {
    public List<Transition> transitions;

    public BubbleSort() {
        transitions = new ArrayList<>();
    }

    public List<Transition> startSort(Block[] blocks) {
        bubbleSort(blocks);
        return transitions;
    }

    public void bubbleSort(Block[] blocks) {
        int numberOfBlocks = blocks.length - 1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length - i - 1; j++) {
                double h1 = blocks[j].getHeight();
                double h2 = blocks[j+1].getHeight();
                transitions.add(colorBlock(blocks, Color.RED, j, j + 1));
                if (h1 > h2) {
                    swap(blocks, j, j+1);
                }
                transitions.add(colorBlock(blocks, Color.WHITE, j, j + 1));
            }
            transitions.add(colorBlock(blocks, Color.GREEN, numberOfBlocks--));
        }
    }

    public void swap(Block[] blocks, int a, int b) {
        double variation = (b - a) * (blocks[a].getWidth() + Controller.SPACE);
        ParallelTransition p = new ParallelTransition();
        p.getChildren().addAll(blocks[a].moveBlock(variation), blocks[b].moveBlock(-variation));
        transitions.add(p);
        Block temp = blocks[a];
        blocks[a] = blocks[b];
        blocks[b] = temp;
    }

    public void fillTransition(ParallelTransition parallelTransition, Block block, Color color) {
        FillTransition fillTransition = new FillTransition();
        fillTransition.setShape(block);
        fillTransition.setToValue(color);
        fillTransition.setDuration(Duration.millis(Controller.SPEED));
        parallelTransition.getChildren().add(fillTransition);
    }

    public ParallelTransition colorBlock(Block[] blocks, Color color, int... b) {
        ParallelTransition parallelTransition = new ParallelTransition();
        for (int j : b) {
            fillTransition(parallelTransition, blocks[j], color);
        }
        return parallelTransition;
    }


}
