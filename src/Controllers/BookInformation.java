package Controllers;

import FileHandling.FileHandling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

import java.io.File;

public class BookInformation {

    @FXML
    public Text title;

    @FXML
    public Text author;

    @FXML
    public Text id;

    @FXML
    public Text shelf;

    @FXML
    public Text issue_msg;

    @FXML
    public Text release_date;
    public Rectangle cover;
    private Image img;


    @FXML
    void initialize(){

        title.setOnMouseClicked(e ->{
            String title = new Main().inputDialog("Type the Book Title");
            if(title.length() == 0) return;
            this.title.setText(title);
            Main.books.search(this.id.getText()).setTitle(title);
        });
        author.setOnMouseClicked(e ->{
            String author = new Main().inputDialog("Author");
            if(author.length() == 0) return;
            this.author.setText(author);
            Main.books.search(this.id.getText()).setAuthor(author);
        });
        shelf.setOnMouseClicked(e -> {
            String shelf = new Main().inputDialog("Shelf No");
            if(shelf.length() == 0) return;
            this.shelf.setText(shelf);
            Main.books.search(this.id.getText()).setShelf(shelf);
        });
        release_date.setOnMouseClicked(e ->{
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            window.setMinWidth(250);
            Label label = new Label("Release Date");

            Button yesButton = new Button("Ok");
            DatePicker picker = new DatePicker();

            yesButton.setOnAction(a -> {
                window.close();
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label,picker,yesButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
            String[] date = picker.getValue().toString().split("-");
            String rd = date[2]+","+date[1]+","+date[0];
            this.release_date.setText(rd);
            Main.books.search(this.id.getText()).setRelease_date(rd);
        });
        id.setOnMouseClicked(e ->{
            new Main().msgDialog("You can not edit Book ID");
        });
        cover.setOnMouseClicked(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
            Stage stage = (Stage)cover.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if( file != null){
                img = new Image(file.toURI().toString());
                new FileHandling().checkDir("C:\\Users\\Public\\LM\\Cover\\");
                String ext = img.getUrl().substring(img.getUrl().indexOf(".")+1);
                new FileHandling().saveImage(img,"C:\\Users\\Public\\LM\\Cover\\"+id.getText(),ext);
                cover.setFill(new ImagePattern(img));
                Main.books.search(id.getText()).setCover(img);
                System.out.println(img.getUrl());
            }
        });

    }

    public void setCover(Image cover) {
        this.cover.setFill(new ImagePattern(cover));
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setAuthor(String _author){
        this.author.setText(_author);
    }
    public void setId(String _id){
        this.id.setText(_id);
    }
    public void setShelf(String _shelf){
        this.shelf.setText(_shelf);
    }
    public void setIssue_msg(String _msg){
        this.issue_msg.setText(_msg);
    }
    public void setRelease_date(String _date){
        this.release_date.setText(_date);
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage)this.issue_msg.getScene().getWindow();
        stage.close();
    }

    public void remove(ActionEvent actionEvent) {
        Button yes = new Button("YES");
        Button no = new Button("NO");
        no.setOnAction(e -> {
            Stage stage = (Stage)no.getScene().getWindow();
            stage.close();
        });
        yes.setOnAction(e -> {
            Main.books.remove(this.id.getText());
            Stage stage = (Stage)yes.getScene().getWindow();
            Stage stage1 = (Stage)this.id.getScene().getWindow();
            stage.close();
            stage1.close();
        });
        Text msg = new Text("are you sure you want to remove?");
        msg.setFill(Color.BLUE);
        msg.setFont(Font.font("Comic Sans MS",20));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(yes,no);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(msg,hBox);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(vBox));
        stage.show();
    }
}
