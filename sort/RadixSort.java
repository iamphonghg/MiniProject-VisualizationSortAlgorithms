package sort;

import javafx.animation.Transition;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class RadixSort extends AbstractSort {

    public RadixSort() {
        transitions = new ArrayList<>();
    }

    @Override
    public List<Transition> startSort(Block[] blocks) {
        radixSort(blocks);
        return null;
    }

    private void radixSort(Block[] blocks) {
        Block[] tempBlocks = new Block[blocks.length];

        for (int i = 0; i < blocks.length; i++) {
            tempBlocks[i] = new Block();
        }
        int m = (int) blocks[0].getHeight(), exp = 1;

        for (int i = 0; i < blocks.length; i++)
            if ((int) blocks[i].getHeight() > m)
                m = (int) blocks[i].getHeight();

        while (m / exp > 0) {
            int[] bucket = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int i = 0; i < blocks.length; i++)
                bucket[((int) blocks[i].getHeight()) / exp % 10]++;
            for (int i = 1; i < 10; i++)
                bucket[i] += bucket[i - 1];
            for (int i = blocks.length - 1; i >= 0; i--) {
                tempBlocks[--bucket[((int) blocks[i].getHeight()) / exp % 10]] = blocks[i];
            }
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = tempBlocks[i];
            }
            exp *= 10;
        }
    }
}
