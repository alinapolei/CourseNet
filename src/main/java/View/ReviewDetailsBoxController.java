package main.java.View;

import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import main.java.sample.Review;


public class ReviewDetailsBoxController {

    @FXML
    public Labeled dateLabel;
    public TextArea descText;
    Review review;

    @FXML
    public void initialize(){
        dateLabel.managedProperty().bind(dateLabel.visibleProperty());
        descText.managedProperty().bind(descText.visibleProperty());
    }


    public void setData(Review review) {
        this.review = review;

        dateLabel.setText(review.getDate());
        descText.setText(review.getDescription());

    }

}
