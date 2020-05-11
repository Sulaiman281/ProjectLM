package Controllers;

import FileHandling.FileHandling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Book;
import sample.Main;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class AddBook {

    @FXML
    public TextField titleInput;

    @FXML
    public TextField author_input;

    @FXML
    public DatePicker release_date;

    @FXML
    public Rectangle cover;
    public TextField id;
    public TextField shelf;
    public Text msg;
    private Image img;

    private boolean id_generated;

    @FXML
    void initialize(){
        id_generated = false;
        msg.setText("");
    }

    @FXML
    void add_book(ActionEvent event) {
        if(titleInput.getText().length() == 0 || author_input.getText().length() == 0 ||
                release_date.getValue().toString().length() == 0 || id.getText().length() == 0 || shelf.getText().length() == 0) {
            msg.setText("Missing Text Fields");
            msg.setFill(Color.RED);
            return;
        }
        String[] date = release_date.getValue().toString().split("-");
        String rd = date[2]+","+date[1]+","+date[0];
        Book book = new Book(titleInput.getText(),author_input.getText(),rd,id.getText());
        book.setShelf(shelf.getText());
        if(img != null)
            book.setCover(img);
        if(Main.books.add_book(book)){
            new FileHandling().checkDir("C:\\Users\\Public\\LM\\Cover\\");
            String ext = img.getUrl().substring(img.getUrl().indexOf(".")+1);
            new FileHandling().saveImage(img,"C:\\Users\\Public\\LM\\Cover\\"+book.getId(),ext);
            msg.setText("Book is successfully added");
            msg.setFill(Color.GREEN);
        }else {
            msg.setFill(Color.RED);
            msg.setText("Book is already in the library");
        }
    }

    @FXML
    void browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Book Cover");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        Stage stage = (Stage)cover.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if( file != null){
            img = new Image(file.toURI().toString());
            cover.setFill(new ImagePattern(img));
        }
    }

    /*void generate_book(ActionEvent event) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        String y = String.valueOf(year);
        y = y.substring(2);
        int size = Main.books.total_books();
        String id = "#"+(day < 10 ? "0": "")+day+
                (month < 10 ? "0" : "")+month+
                (Integer.parseInt(y)<10 ? "0":"")+y+
                (size < 10 ? "0":"")+size;

        id_generated = true;
    }*/
}
