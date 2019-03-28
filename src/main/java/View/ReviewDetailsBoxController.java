package main.java.View;

import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import main.java.sample.Review;


public class ReviewDetailsBoxController {

    public Labeled dateLabel;
    public TextArea descText;
    Review review;
    Pane pane;



    public void setData(Review review, Pane pane) {
        this.review = review;
        this.pane = pane;

    }
    
}
