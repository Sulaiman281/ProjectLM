package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import sample.Main;

import java.io.IOException;

public class Controller {

    @FXML
    private AnchorPane root;

    @FXML
    void add_book(ActionEvent event) throws IOException {
        if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/AddBook.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);
    }

    @FXML
    void add_student(ActionEvent event) throws IOException {
        if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/AddStudent.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);
    }

    @FXML
    void admin_settings(ActionEvent event) throws IOException {
     /*   if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/AddBook.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);*/
    }

    @FXML
    void books(ActionEvent event) throws IOException {
        if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/Books.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);
    }

    @FXML
    void issue_book(ActionEvent event) throws IOException {
        if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/IssueBook.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);
    }

    @FXML
    void return_book(ActionEvent event) throws IOException {
        if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/ReturnBook.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);
    }

    @FXML
    void students(ActionEvent event) throws IOException {
        if(root.getChildren().size() > 3)
            root.getChildren().remove(3);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/Students.fxml"));
        anchorPane.setLayoutX(200);
        anchorPane.setLayoutY(100);
        root.getChildren().add(anchorPane);
    }

    public void closeApplication(ActionEvent actionEvent) {
        Main.close();
    }
}
