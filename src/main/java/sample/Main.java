package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    public static Stage getStage() {
        return stage;
    }
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        stage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        queryDB queryDB=new queryDB();
        launch(args);

   //     Course course=new Course("372.1.5000","kjk",3.5,3,1,"16-18",true,true,5,"jhhj","jhjh");
     //   queryDB.insertCourse(course);
        System.out.println("here");
    }
}
