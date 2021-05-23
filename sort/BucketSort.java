package sort;

import controller.Controller;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Block;

import java.lang.reflect.Array;
import java.util.*;

public class BucketSort extends AbstractSort {
    private final int BUCKET_CAPACITY = 3;

    public BucketSort() {
        transitions = new ArrayList<>();
    }

    @Override
    public List<Transition> startSort(Block[] blocks) {
        bucketSort(blocks);
        return transitions;
    }

    private void bucketSort(Block[] blocks) {
        int numberOfBlocks = blocks.length / 2;

        int maxValue = (int) blocks[0].getHeight();
        int minValue = (int) blocks[0].getHeight();

        for (int i = 0; i < numberOfBlocks; i++) {
            if ((int) blocks[i].getHeight() > maxValue) {
                maxValue = (int) blocks[i].getHeight();
            }
            if ((int) blocks[i].getHeight() < minValue) {
                minValue = (int) blocks[i].getHeight();
            }
        }

        int numberOfBuckets = (maxValue - minValue + 1) / BUCKET_CAPACITY + ((maxValue - minValue + 1) % BUCKET_CAPACITY == 0 ? 0 : 1);

        List<Color> bucketColors = generateBucketColors(numberOfBuckets);

        ArrayList<Block>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<Block>();
        }

        for (int i = 0; i < numberOfBlocks; i++) {
            int bucketIndex = ((int) blocks[i].getHeight() - minValue + 1) / BUCKET_CAPACITY + (((int) blocks[i].getHeight() - minValue + 1) % BUCKET_CAPACITY == 0 ? -1 : 0);;
            buckets[bucketIndex].add(blocks[i]);
            transitions.add(colorBlock(blocks, bucketColors.get(bucketIndex), i));

            bucketIndex = ((int) blocks[i + numberOfBlocks].getHeight() - minValue + 1) / BUCKET_CAPACITY + (((int) blocks[i + numberOfBlocks].getHeight() - minValue + 1) % BUCKET_CAPACITY == 0 ? -1 : 0);
            transitions.add(colorBlock(blocks, bucketColors.get(bucketIndex), i + numberOfBlocks));
        }

        SequentialTransition sequentialTransition = new SequentialTransition();
        int index = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            Collections.sort(buckets[i]);
            for (int j = 0, size = buckets[i].size(); j < size; j++) {
                blocks[index] = buckets[i].get(j);
                FadeTransition fadeTransition = new FadeTransition(Duration.millis(100), blocks[index]);
                fadeTransition.setFromValue(1.0);
                fadeTransition.setToValue(0.0);
                sequentialTransition.getChildren().add(fadeTransition);
                index++;
            }
        }
        for (int i = 0; i < numberOfBlocks; i++) {
            sequentialTransition.getChildren().add(swap(blocks, i + numberOfBlocks, i));
        }
        transitions.add(sequentialTransition);
    }

    private List<Color> generateBucketColors(int colorsNeeded) {
        List<Color> bucketColors = new ArrayList<>();
        Random random = new Random();
        double r, g, b;
        for (int i = 0; i < colorsNeeded; i++) {
            r = random.nextDouble();
            g = random.nextDouble();
            b = random.nextDouble();
            bucketColors.add(new Color(r, g, b, 1));
        }
        return bucketColors;
    }

    @Override
    public ParallelTransition swap(Block[] blocks, int a, int b) {
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.setDelay(Duration.millis(100));
        double variation = (b - a) * (blocks[a].getWidth() + Controller.SPACE) - 20;
        blocks[a].setVisible(true);
        parallelTransition.getChildren().addAll(blocks[a].moveBlock(variation), blocks[b].moveBlock(variation));
        return parallelTransition;
    }
}
