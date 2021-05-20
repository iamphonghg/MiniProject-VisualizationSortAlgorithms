package controller;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Block;

import java.net.Inet4Address;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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
    private Button btnTemp;


    private static final double DISPLAY_WIDTH = 851f;
    private static final double DISPLAY_HEIGHT = 488f;
    private static final double SPACE = 4f;
    private static final Insets MARGIN = new Insets(0, SPACE / 2, 0, SPACE / 2);
    private int tempNumberOfBlocks;
    private Block[] blocks;
    private List<Transition> transitions = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbxAlgorithms.setItems(FXCollections.observableArrayList("BUBBLE SORT", "QUICK SORT",
                "HEAP SORT", "RADIX SORT", "MERGE SORT", "BUCKET SORT"));
        cbxAlgorithms.getSelectionModel().select(0);
        handleEvents();

        Block b1 = new Block(67, 200);
        b1.setLayoutX(0);
        Block b2 = new Block(67, 200);
        b2.setLayoutX(67);
        b2.setFill(Color.RED);
//        Block b3 = new Block(67, 200, MARGIN);
        paneDisplay.getChildren().addAll(b1, b2);
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(b1);
        transition1.setDuration(Duration.millis(500));
        transition1.setByX(b2.getLayoutX() - b1.getLayoutX());
        transition1.play();

        TranslateTransition transition2 = new TranslateTransition();
        transition2.setNode(b2);
        transition2.setDuration(Duration.millis(500));
        transition2.setByX(-200);
        Block temp = b1;
        b1 = b2;
        b2 = temp;

        System.out.println(b1.getLayoutX());


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
        transitions.clear();
        for (int i = 0; i < tempNumberOfBlocks; i++) {
            for (int j = 0; j < tempNumberOfBlocks-i-1; j++) {
                double h1 = blocks[j].getHeight();
                double h2 = blocks[j+1].getHeight();
                if (h1 > h2) {
                    swap(j, j+1);
                }
            }
        }
        SequentialTransition st = new SequentialTransition();
        st.getChildren().addAll(transitions);
        st.play();
        for (Block b : blocks) {
            System.out.println(b.getHeight());
        }
    }

    public void swap(int a, int b) {
        double posA = blocks[a].getLayoutX();
        double posB = blocks[b].getLayoutX();
        TranslateTransition transitionA = new TranslateTransition(Duration.millis(50), blocks[a]);
        TranslateTransition transitionB = new TranslateTransition(Duration.millis(50), blocks[b]);
        double variation = (b - a) * (blocks[a].getWidth() + SPACE);
        transitionA.setByX(variation);
        transitionB.setByX(-variation);
        ParallelTransition p = new ParallelTransition();
        p.getChildren().addAll(transitionA, transitionB);
        transitions.add(p);
        Block temp = blocks[a];
        blocks[a] = blocks[b];
        blocks[b] = temp;

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
                blocks[i].mySetLayoutX(SPACE);
            } else {
                blocks[i].mySetLayoutX(SPACE * (i + 1) + i * blockWidth);
            }
            blocks[i].setLayoutY(DISPLAY_HEIGHT - blockHeight);
        }
        return blocks;
    }
}
