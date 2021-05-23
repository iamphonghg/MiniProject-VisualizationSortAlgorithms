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
        transitions.addAll(colorBlock(blocks, SORTED_COLOR));
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
        int pivot = high;
        int left = low;
        int right = high - 1;
        while (true) {
            while (blocks[left].getHeight() < blocks[pivot].getHeight() && left <= right)
                left++;
            while (blocks[right].getHeight() > blocks[pivot].getHeight() && left < right)
                right--;
            if (left >= right)
                break;
            transitions.add(swap(blocks, left, right));
            left++;
            right--;
        }
        transitions.add(swap(blocks, pivot, left));
        return left;
    }



}
