package sort;

import controller.Controller;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSort {
    public List<Transition> transitions;
    public static final Color SELECT_COLOR = Color.RED;
    public static final Color SORTED_COLOR = Color.GREEN;
    public static final Color DEFAULT_COLOR = Color.BLACK;

    public abstract List<Transition> startSort(Block[] blocks);

    public ParallelTransition swap(Block[] blocks, int a, int b) {
        double variation = (b - a) * (blocks[a].getWidth() + Controller.SPACE);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(blocks[a].moveBlock(variation), blocks[b].moveBlock(-variation));
        Block temp = blocks[a];
        blocks[a] = blocks[b];
        blocks[b] = temp;
        return parallelTransition;
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

    public List<Transition> colorBlock(Block[] blocks, Color color) {
        List<Transition> transitionList = new ArrayList<>();
        for (int i = 0; i < blocks.length; i++) {
            FillTransition fillTransition = new FillTransition();
            fillTransition.setShape(blocks[i]);
            fillTransition.setToValue(color);
            fillTransition.setDuration(Duration.millis(100));
            transitionList.add(fillTransition);
        }
        return transitionList;
    }
}
