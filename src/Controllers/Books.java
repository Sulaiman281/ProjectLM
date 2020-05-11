package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.IOException;

public class Books {


    public TextField search_box;
    public Pane root;

    @FXML
    void initialize(){

        float x = -200;
        float y = -250;
        root.setPrefWidth(800);

        if(Main.books.size() == 0){
            root.setPrefHeight(475);
            Text text = new Text("There are no books");
            text.setFont(Font.font("Comic Sans MS", 24));
            text.setLayoutX(root.getWidth()/2);
            text.setLayoutY(root.getHeight()/2);
            root.getChildren().add(text);
        }

        for(int i = 0; i<Main.books.size(); i++){
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
            if(Main.books.get(i).getCover() != null)
                rectangle.setFill(new ImagePattern(Main.books.get(i).getCover()));

            int finalI = i;
            rectangle.setOnMouseClicked(e -> {
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("../FXML/BookInformation.fxml"));
                AnchorPane anchorPane = null;
                try {
                    anchorPane = fxml.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                BookInformation bookInformation = fxml.getController();
                if(Main.books.get(finalI).getCover() != null)
                    bookInformation.setCover(Main.books.get(finalI).getCover());
                bookInformation.setTitle(Main.books.get(finalI).getTitle());
                bookInformation.setAuthor(Main.books.get(finalI).getAuthor());
                bookInformation.setId(Main.books.get(finalI).getId());
                bookInformation.setShelf(Main.books.get(finalI).getShelf());
                bookInformation.setRelease_date(Main.books.get(finalI).getRelease_date());
                // set issue msg here
                bookInformation.setIssue_msg(String.valueOf(Main.books.get(finalI).isIssued()));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(anchorPane));
                stage.show();
            });
            Text text = new Text(Main.books.get(i).getTitle());
            text.setFont(Font.font("Comic Sans MS", 24));

            vBox.getChildren().addAll(rectangle,new TextFlow(text));
            root.getChildren().add(vBox);

        }

    }
}
