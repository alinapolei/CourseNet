package main.java.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginController {

    public Labeled erorMsg;
    public TextField mailText;


    public void actionLogin(ActionEvent actionEvent) throws IOException {
       String mail=mailText.getText();
        if(!bguMail(mail)) {
            erorMsg.setText("אנא התחבר עם מייל אוניברסיטאי");
            erorMsg.setVisible(true);
        }
            else
        {
            erorMsg.setVisible(false);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
            Parent root = (Parent)fxmlLoader.load();
            MainScreenController controller = fxmlLoader.getController();
            Scene scene=new Scene(root,1000,650);
            ((Stage) mailText.getScene().getWindow()).setScene(scene);

        }

    }

    private boolean bguMail(String mail) {
        Pattern p = Pattern.compile("@post\\.bgu\\.ac\\.il$");//I want to know the right format
        Matcher m = p.matcher(mail);
        return m.find();

    }
}
