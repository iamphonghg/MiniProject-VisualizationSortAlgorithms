package sort;

import javafx.animation.Transition;
import javafx.scene.paint.Color;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class QuickSort extends AbstractSort {
    private static final Color PIVOT_COLOR = Color.CRIMSON;

    public QuickSort() {
        transitions = new ArrayList<>();
    }

    @Override
    public List<Transition> startSort(Block[] blocks) {
        quickSort(blocks, 0, blocks.length - 1);
        transitions.add(colorBlock(blocks, SORTED_COLOR));
        return transitions;
    }

    private void quickSort(Block[] blocks, int low, int high) {
        if (low < high) {
            int pivot = partition(blocks, low, high);
            quickSort(blocks, low, pivot - 1);
            quickSort(blocks, pivot + 1, high);
        }
    }

    public int partition(Block[] blocks, int low, int high){
        int i = low;
        transitions.add(colorBlock(blocks, PIVOT_COLOR, high));
        for (int j = low; j < high; j++) {
            transitions.add(colorBlock(blocks, SELECT_COLOR, j));
            if (blocks[j].getHeight() < blocks[high].getHeight()) {
                transitions.add(swap(blocks, i, j));
                transitions.add(colorBlock(blocks, Color.LIGHTGREEN, i));
                i++;
            }
            else transitions.add(colorBlock(blocks, Color.AQUAMARINE, j));
        }
        transitions.add(swap(blocks, i, high));
        transitions.add(colorBlock(blocks, SORTED_COLOR, i));
        return i;
    }



}
