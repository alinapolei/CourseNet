package main.java.View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class acceptController {
    @FXML
    public Button b1;
    public TextArea ta1;
    public ChoiceBox c1;

    public void onClick(){
        //insert to db

        ((Stage)b1.getScene().getWindow()).close();
    }
}
