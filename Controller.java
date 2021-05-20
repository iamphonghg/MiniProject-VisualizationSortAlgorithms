import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
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
    private TextField txtNumberElements;

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
                int numberOfBlocks = Integer.parseInt(txtNumberElements.getText());
                displayBlock(numberOfBlocks);
            }
        });
    }

    private void displayBlock(int numberOfBlocks) {
        paneDisplay.getChildren().clear();
        double WIDTH_DISPLAY = 851f;
        int NUMBER_OF_BLOCKS = numberOfBlocks;
        int MARGIN = 2;
        Insets margin = new Insets(0, MARGIN, 0, MARGIN);
        double BLOCK_WIDTH_TEMP = ((WIDTH_DISPLAY - NUMBER_OF_BLOCKS * MARGIN * 2) / NUMBER_OF_BLOCKS) - MARGIN;
        System.out.println((BLOCK_WIDTH_TEMP + MARGIN * 2) * NUMBER_OF_BLOCKS);
        Rectangle[] blocks = new Rectangle[NUMBER_OF_BLOCKS];
        for (Rectangle block : blocks) {
            block = new Rectangle();
            block.setWidth(BLOCK_WIDTH_TEMP);
            block.setHeight(Math.floor(Math.random() * (480 - 10 + 1) + 10));
            block.setFill(Color.WHITE);
            HBox.setMargin(block, margin);
            paneDisplay.getChildren().add(block);
        }

    }
}
