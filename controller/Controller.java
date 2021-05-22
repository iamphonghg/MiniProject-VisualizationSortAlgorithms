package controller;

import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Block;
import sort.BubbleSort;
import sort.HeapSort;
import sort.MergeSort;
import sort.QuickSort;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane paneDisplay;
    @FXML
    private ComboBox<String> cbxAlgorithms;
    @FXML
    private Button btnSort;
    @FXML
    private Button btnGenerateRandom;
    @FXML
    private TextField txtNumberBlocks;

    @FXML
    private Button btnBoost;


    public static final double DISPLAY_WIDTH = 851f;
    public static final double DISPLAY_HEIGHT = 488f;
    public static final double SPACE = 1f;
    public static double SPEED = 500;

    private SequentialTransition sequentialTransition;
    private int tempNumberOfBlocks;
    private Block[] blocks;
    private List<Transition> transitions;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbxAlgorithms.setItems(FXCollections.observableArrayList("BUBBLE SORT", "QUICK SORT",
                "HEAP SORT", "RADIX SORT", "MERGE SORT", "BUCKET SORT"));
        cbxAlgorithms.getSelectionModel().select(0);
        handleEvents();
    }

    private void handleEvents() {
        btnGenerateRandom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayBlockAfterRandom();
            }
        });
        btnSort.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleSort();
            }
        });
        btnBoost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                double rate = sequentialTransition.getRate();
                sequentialTransition.setRate(rate *= 10);
            }
        });

    }

    public void handleSort() {
        String selectedAlgorithm = cbxAlgorithms.getSelectionModel().getSelectedItem();
        switch (selectedAlgorithm) {
            case "BUBBLE SORT": {
                BubbleSort bubbleSort = new BubbleSort();
                transitions = bubbleSort.startSort(blocks);
                break;
            }
            case "QUICK SORT": {
                QuickSort quickSort = new QuickSort();
                transitions = quickSort.startSort(blocks);
                break;
            }
            case "HEAP SORT": {
                HeapSort heapSort = new HeapSort();
                transitions = heapSort.startSort(blocks);
                break;
            }
            case "RADIX SORT": {
                break;
            }
            case "MERGE SORT": {
                MergeSort mergeSort = new MergeSort();
                transitions = mergeSort.startSort(blocks);
                break;
            }
            case "BUCKET SORT": {
                break;
            }
            default:
                break;
        }
        if (transitions != null) {
            sequentialTransition = new SequentialTransition();
            sequentialTransition.getChildren().addAll(transitions);
            sequentialTransition.play();
            transitions.clear();
        }
    }

    public void displayBlockAfterRandom() {
        String numberOfBlocks = txtNumberBlocks.getText();
        String regex = "\\d+";
        if (numberOfBlocks.matches(regex)) {
            tempNumberOfBlocks = Integer.parseInt(numberOfBlocks);
            blocks = randomGenerateBlock(tempNumberOfBlocks);
            paneDisplay.getChildren().clear();
            paneDisplay.getChildren().addAll(blocks);
        }
    }


    private Block[] randomGenerateBlock(int numberOfBlocks) {
        Block[] blocks = new Block[numberOfBlocks];
        double blockWidth = (DISPLAY_WIDTH - (numberOfBlocks + 1) * SPACE) / numberOfBlocks;

        for (int i = 0; i < numberOfBlocks; i++) {
            double blockHeight = Math.floor(Math.random() * (480 - 10 + 1) + 10);
            blocks[i] = new Block(blockWidth, blockHeight);
            if (i == 0) {
                blocks[i].setLayoutX(SPACE);
            } else {
                blocks[i].setLayoutX(SPACE * (i + 1) + i * blockWidth);
            }
            blocks[i].setLayoutY(DISPLAY_HEIGHT - blockHeight);
        }
        return blocks;
    }
}
