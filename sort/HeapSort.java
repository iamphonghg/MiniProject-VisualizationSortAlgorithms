package sort;

import javafx.animation.Transition;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class HeapSort extends AbstractSort {

    public HeapSort() {
        transitions = new ArrayList<>();
    }

    @Override
    public List<Transition> startSort(Block[] blocks) {
        heapSort(blocks);
        return transitions;
    }

    private void heapSort(Block[] blocks) {
        int length = blocks.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(blocks, length, i);
        }
        for (int i = length - 1; i >= 0; i--) {
            transitions.add(swap(blocks, 0, i));
            transitions.add(colorBlock(blocks, SORTED_COLOR, i));
            heapify(blocks, i, 0);
        }
    }

    private void heapify(Block[] blocks, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && blocks[l].getHeight() > blocks[largest].getHeight())
            largest = l;
        if (r < n && blocks[r].getHeight() > blocks[largest].getHeight())
            largest = r;
        if (largest != i) {
            transitions.add(swap(blocks, i, largest));

            heapify(blocks, n, largest);
        }
    }
}
