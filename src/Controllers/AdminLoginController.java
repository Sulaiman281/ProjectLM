package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.Admin;

import java.io.IOException;

public class AdminLoginController {

    @FXML
    public TextField inputField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button loginBtn;

    private Admin admin;

    @FXML
    void initialize(){
        admin = new Admin("Arif","Makiyubatao2");
    }


    public void login_Admin(ActionEvent actionEvent) {
        if(inputField.getText().length() == 0 || passwordField.getText().length() == 0) return;

        String name = inputField.getText();
        String pass = passwordField.getText();

        if(name.equals(admin.getUsername()) && pass.equals(admin.getPassword())){
            try{
                AnchorPane root = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));
                Scene scene = loginBtn.getScene();
                scene.setRoot(root);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public void close_app(ActionEvent actionEvent) {
       // System.exit(0);
    }
}
