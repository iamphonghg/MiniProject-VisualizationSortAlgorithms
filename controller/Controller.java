package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Block;

import java.net.Inet4Address;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private HBox paneDisplay;
    @FXML
    private ComboBox<String> cbxAlgorithms;
    @FXML
    private Button btnSort;
    @FXML
    private Button btnGenerateRandom;
    @FXML
    private TextField txtNumberBlocks;

    private static final double DISPLAY_WIDTH = 851f;
    private static final double SPACE = 4f;
    private static final Insets MARGIN = new Insets(0, SPACE / 2, 0, SPACE / 2);
    private int tempNumberOfBlocks;
    private Block[] blocks;

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
    }

    public void handleSort() {
        ObservableList<Node> unsorted = paneDisplay.getChildren();
        for (int i = 0; i < tempNumberOfBlocks; i++) {
            for (int j = 0; j < tempNumberOfBlocks - i - 1; j++) {
                double h1 = blocks[j].getHeight();
                double h2 = blocks[j + 1].getHeight();
                if (h1 > h2) {
                    swap(blocks, j, j + 1);
                }
            }
        }
        paneDisplay.getChildren().clear();
        paneDisplay.getChildren().addAll(blocks);
    }

    public void swap(Block[] blocks, int a, int b) {
        Block temp = blocks[b];
        blocks[b] = blocks[a];
        blocks[a] = temp;
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
        double blockWidth = ((DISPLAY_WIDTH - numberOfBlocks * SPACE) / numberOfBlocks) - SPACE / 2;

        for (int i = 0; i < numberOfBlocks; i++) {
            double blockHeight = Math.floor(Math.random() * (480 - 10 + 1) + 10);
            blocks[i] = new Block(blockWidth, blockHeight, MARGIN);
        }
        return blocks;
    }
}
