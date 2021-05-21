package sort;

import javafx.animation.Transition;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class QuickSort extends AbstractSort {

    public QuickSort() {
        transitions = new ArrayList<>();
    }

    @Override
    public List<Transition> startSort(Block[] blocks) {
        quickSort(blocks, 0, blocks.length - 1);
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
        double pivot = blocks[high].getHeight();
        int left = low - 1;
        int right = high - 1;
        for (int i = low; i <= right; i++) {
            if (blocks[i].getHeight() < pivot) {
                left++;
                transitions.add(swap(blocks, left, i));
            }
        }
        transitions.add(swap(blocks, left + 1, high));
        return left + 1;
    }



}
