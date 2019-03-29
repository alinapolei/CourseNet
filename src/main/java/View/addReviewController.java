package main.java.View;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import main.java.sample.Review;
import main.java.sample.queryDB;


public class addReviewController {
    public Button addFeedBackBtn;
    public TextArea feedbackText;
    public ToggleGroup ranked;
    queryDB q=new queryDB();

    Boolean add=true;
    private String feedback;
    private double rank;
    String courseId;


    public void setCourseId(String id){
        this.courseId=id;

    }

    public void addReview(){
        add=true;
        if(feedbackText.getText()!=null&&feedbackText.getText().compareTo("")!=0)
            feedback=feedbackText.getText();
        else
            add = false;

        if (ranked.getSelectedToggle()!=null)
             rank=Double.valueOf(((RadioButton)ranked.getSelectedToggle()).getText());
        else
            add=false;
        if(add) {
            Review review=new Review(courseId,feedback);
            review.setDescription(feedback);
            q.insertReview(review);
        }
    }
}
