package main.java.View;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.java.sample.Course;
import main.java.sample.Review;
import main.java.sample.queryDB;

import java.io.IOException;

public class NewReviewDetailsBoxController {
    private Course course;

    @FXML
    public VBox vBox;
    public Label courseName ;
    public void initialize(){

    }

    public void setCourse(Course c){
        course = c;
        courseName.setText(course.getName());
        queryDB query=new queryDB();
        ObservableList<Review> reviews = query.getAllReviewPerDay(course.getIdCourse());

        for (Review review : reviews) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ReviewDetailsBox.fxml"));
            Parent root = null;
            try {
                root = (Parent) fxmlLoader.load();
                ReviewDetailsBoxController controller = fxmlLoader.getController();
                controller.setData(review);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 1000, 50);
            vBox.getChildren().add(scene.getRoot());
        }
    }
}
