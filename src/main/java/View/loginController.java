package main.java.View;

import javafx.event.ActionEvent;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginController {

    public Labeled erorMsg;
    public TextField mailText;


    public void actionLogin(ActionEvent actionEvent) {
       String mail=mailText.getText();
        if(!bguMail(mail)) {
            erorMsg.setText("please login with bgu mail");
            erorMsg.setVisible(true);
        }
            else
        {
            erorMsg.setVisible(false);
            System.out.println("login Ok");
        }

    }

    private boolean bguMail(String mail) {
        Pattern p = Pattern.compile("@post\\.bgu\\.ac\\.il$");//I want to know the right format
        Matcher m = p.matcher(mail);
        return m.find();

    }
}
