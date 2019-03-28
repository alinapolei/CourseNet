package main.java.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main extends Application {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/mm/yyyy");
    static LocalDate localDate;


    public static Stage getStage() {
        return stage;
    }
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        primaryStage.setTitle("Course Net");
        primaryStage.setScene(new Scene(root, 450, 275));
        stage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        queryDB queryDB=new queryDB();
        launch(args);

        System.out.println("here");
    }
}
