package Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.IOException;

public class Students {


    @FXML
    public ScrollPane root;
    public Pane pane;
    public TextField search;

    private Main main = new Main();

    @FXML
    void initialize(){
        float x = -200;
        float y = -250;
        pane.setPrefWidth(800);
        // root.setContent(pane);
        if(main.studentList.sList.size() == 0){
            pane.setPrefHeight(475);
            Text text = new Text("There are no members");
            text.setFont(Font.font("Comic Sans MS", 24));
            text.setLayoutX(root.getWidth()/2);
            text.setLayoutY(root.getHeight()/2);
            pane.getChildren().add(text);
        }else
            pane.setPrefHeight(Math.floor(main.studentList.sList.size()*70));
        for(int i = 0; i< main.studentList.sList.size(); i++){
            if(i % 4 == 0) {
                y = y + 250;
                x = 0;
            }else
                x = x + 210;

            VBox vBox = new VBox();
            vBox.setPrefWidth(200);
            vBox.setPrefHeight(250);
            vBox.setLayoutX(x);
            vBox.setLayoutY(y);
            vBox.setMaxWidth(200);
            vBox.setMaxHeight(250);
            vBox.setMinWidth(200);
            vBox.setMinHeight(250);

            vBox.setAlignment(Pos.TOP_CENTER);
            Rectangle rectangle = new Rectangle();
            rectangle.setWidth(200);
            rectangle.setHeight(200);
            rectangle.setStroke(Color.web("#3d261a"));
            rectangle.setStrokeWidth(4);
            if(main.studentList.sList.get(i).getProfile() != null)
                rectangle.setFill(new ImagePattern(main.studentList.sList.get(i).getProfile()));
            else rectangle.setFill(Color.BLUE);
            int finalI = i;
            rectangle.setOnMouseClicked(e -> {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("../FXML/StudentInformation.fxml"));
                AnchorPane anchorPane = null;
                try {
                    anchorPane = fxml.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                StudentInformation si = fxml.getController();
                si.studentInfo(Main.studentList.sList.get(finalI));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(anchorPane));
                stage.show();

            });
            Text text = new Text(main.studentList.sList.get(i).getFullName());
            text.setFont(Font.font("Comic Sans MS", 24));
            text.setOnMouseClicked(e ->{
                System.out.println("Text is clicked");
            });
            vBox.getChildren().addAll(rectangle,text);
            pane.getChildren().add(vBox);
        }
    }
}
