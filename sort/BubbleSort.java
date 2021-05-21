package sort;

import javafx.animation.Transition;
import model.Block;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort extends AbstractSort {

    public BubbleSort() {
        transitions = new ArrayList<>();
    }

    @Override
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
                transitions.add(colorBlock(blocks, SELECT_COLOR, j, j + 1));
                if (h1 > h2) {
                    transitions.add(swap(blocks, j, j+1));
                }
                transitions.add(colorBlock(blocks, DEFAULT_COLOR, j, j + 1));
            }
            transitions.add(colorBlock(blocks, SORTED_COLOR, numberOfBlocks--));
        }
    }
}
