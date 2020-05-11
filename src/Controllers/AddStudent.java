package Controllers;

import FileHandling.FileHandling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;
import sample.Student;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddStudent {

    @FXML
    public Rectangle profile;
    public TextField first_name;
    public TextField last_name;
    public DatePicker dob;
    public TextField roll_no;
    public TextArea address;
    public ChoiceBox gender;
    public Text msg;

    private Image img;

    @FXML
    void initialize(){
        ObservableList<String> opt = FXCollections.observableArrayList();
        opt.addAll("Male","Female");
        gender.setItems(opt);
        gender.show();
        img = new Image("/sample/images/profile.jpg");
        profile.setFill(new ImagePattern(img));
        msg.setText("");
    }

    public void browse(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Picture");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
        Stage stage = (Stage)profile.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if( file != null){
            img = new Image(file.toURI().toString());
            profile.setFill(new ImagePattern(img));
        }
    }

    public void add_student(ActionEvent actionEvent) {
        if(first_name.getText().length() == 0 || last_name.getText().length() == 0 || roll_no.getText().length() == 0) return;
        if(dob.getValue().toString().length() == 0) return;
        if(gender.getValue().toString().length() == 0) return;

        Student student = new Student(first_name.getText(),last_name.getText(),roll_no.getText());
        String[] date = this.dob.getValue().toString().split("-");
        student.setBirthday(date[2],date[1],date[0]);
        student.setGender(gender.getValue().toString());
        student.setAge();
        student.setAddress(address.getText());
        student.setProfile(img);

        Main main = new Main();
        if(main.studentList.add_Student(student)){
            new FileHandling().checkDir("C:\\Users\\Public\\LM\\Profiles\\");
            String ext = img.getUrl().substring(img.getUrl().indexOf(".")+1);
            new FileHandling().saveImage(student.getProfile(),"C:\\Users\\Public\\LM\\Profiles\\"+student.getRoll_no(),ext);
            msg.setFill(Color.GREEN);
            msg.setText("Student is successfully added");
        }else {
            msg.setFill(Color.RED);
            msg.setText("Student is already in the list");
        }
    }
}
