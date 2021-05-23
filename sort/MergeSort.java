package sort;

import controller.Controller;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class MergeSort extends AbstractSort {

    public MergeSort() {
        transitions = new ArrayList<>();
    }

    @Override
    public List<Transition> startSort(Block[] blocks) {
        mergeSort(blocks, 0, blocks.length - 1);
        transitions.addAll(colorBlock(blocks, SORTED_COLOR));
        return transitions;
    }

    public void mergeSort(Block[] blocks, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(blocks, l, m);
            mergeSort(blocks, m+1, r);
            merge(blocks, l, m, r);
        }
    }

    public void merge(Block[] blocks, int l, int m, int r) {
        int i, j, k;
        int a1 = m - l + 1;
        int a2 = r - m;
        Block[] L = new Block[a1];
        Block[] R = new Block[a2];
        Block[] tempBlocks = new Block[blocks.length];
        for (int n = l; n <= r; n++) {
            tempBlocks[n] = blocks[n];
        }
        for (i = 0; i < a1; i++) {
            L[i] = blocks[l + i];
        }
        for (j = 0; j < a2; j++) {
            R[j] = blocks[m + j + 1];
        }
        i = 0;
        j = 0;
        k = l;
        while (i < a1 && j < a2) {
            if (L[i].getHeight() <= R[j].getHeight()) {
                blocks[k] = L[i];
                i++;
            } else {
                blocks[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < a1) {
            blocks[k++] = L[i++];
        }
        while(j < a2) {
            blocks[k++] = R[j++];
        }

        ParallelTransition parallelTransition = new ParallelTransition();
        for (int n = l; n <= r ; n++) {
            for (int o = l; o <= r ; o++) {
                if (tempBlocks[n].equals(blocks[o])) {
                    parallelTransition.getChildren().add(tempBlocks[n].moveBlock((blocks[n].getWidth() + Controller.SPACE) * (o - n)));
                }
            }
        }
        transitions.add(parallelTransition);
    }
}
