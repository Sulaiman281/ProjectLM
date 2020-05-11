package Controllers;

import FileHandling.FileHandling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.Student;

import java.io.File;
import java.io.IOException;


public class StudentInformation {

    @FXML
    public AnchorPane root;

    @FXML
    public Rectangle profile;

    @FXML
    public Text roll_no;

    @FXML
    public Text name;

    @FXML
    public Text age;

    @FXML
    public Text gender;

    @FXML
    public HBox issued_books;

    public Text address;

    private Student student;

    private boolean infoChange = false;
    private Image img;

    @FXML
    void initialize(){
        roll_no.setOnMouseClicked(e -> new Main().msgDialog("Student Roll No can not be edit sorry"));
        name.setOnMouseClicked(e -> {
            String name[] = new Main().inputDialog("Enter Full Name").split(" ");
            String n;
            if(name.length < 2) {
                n = new Main().inputDialog("Please Enter Full Name");
                if(n.split(" ").length < 2) return;
                name[0] = n.split(" ")[0];
                name[1] = n.split(" ")[1];
            }
            this.student.setFirst_name(name[0]);
            this.student.setLast_name(name[1]);
            this.name.setText(name[0]+" "+name[1]);
            infoChange = true;
        });
        age.setOnMouseClicked(e ->{
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            window.setMinWidth(250);
            Label label = new Label("Pick Birthdate");

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
            if(picker.getValue().toString().isEmpty()) return;
            this.student.setAge();
            this.age.setText(this.student.getAge() + "");
        });
        gender.setOnMouseClicked(e -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            window.setMinWidth(250);
            Label label = new Label("Select Gender");

            Button yesButton = new Button("Ok");
            ChoiceBox<String> genderList = new ChoiceBox<>();
            ObservableList<String> opt = FXCollections.observableArrayList();
            opt.addAll("Male","Female");
            genderList.setItems(opt);
            genderList.show();

            yesButton.setOnAction(a -> {
                window.close();
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label,genderList,yesButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
            if(genderList.getValue().isEmpty()) return;
            this.student.setGender(genderList.getValue().toString());
            this.gender.setText(this.student.getGender());
        });
        address.setOnMouseClicked(e -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);

            window.setMinWidth(350);
            Label label = new Label("Address");

            Button yesButton = new Button("Ok");
            TextArea textArea = new TextArea();

            yesButton.setOnAction(a -> {
                window.close();
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label,textArea,yesButton);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
            if(textArea.getText().isEmpty()) return;
            this.student.setAddress(textArea.getText());
            this.address.setText(textArea.getText());
        });
        profile.setOnMouseClicked(e ->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
            Stage stage = (Stage)profile.getScene().getWindow();
            File file = fileChooser.showOpenDialog(stage);
            if( file != null){
                img = new Image(file.toURI().toString());
                new FileHandling().checkDir("C:\\Users\\Public\\LM\\Profiles\\");
                String ext = img.getUrl().substring(img.getUrl().indexOf(".")+1);
                new FileHandling().saveImage(student.getProfile(),"C:\\Users\\Public\\LM\\Profiles\\"+student.getRoll_no(),ext);
                profile.setFill(new ImagePattern(img));
                this.student.setProfile(img);
            }
        });
    }

    @FXML
    void close_window(ActionEvent event) {
        Stage stage = (Stage)issued_books.getScene().getWindow();
        stage.close();
    }

    @FXML
    void edit_student(ActionEvent event) {

    }

    @FXML
    public void studentInfo(Student student){
        this.student = student;
        if(student.getProfile() != null)
            profile.setFill(new ImagePattern(student.getProfile()));
        else profile.setFill(Color.BLUE);
        roll_no.setText(student.getRoll_no());
        name.setText(student.getFullName());
        age.setText(String.valueOf(student.getAge()));
        gender.setText(student.getGender());
        address.setText(student.getAddress());
        if(student.getIssued() > 0){
            for(int i = 0; i< student.getIssued(); i++){
                VBox vBox = new VBox();
                vBox.setPrefWidth(100);
                vBox.setPrefHeight(200);
                vBox.setAlignment(Pos.TOP_CENTER);

                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(100);
                rectangle.setHeight(80);

                if(Main.management.getBooks(student.getRoll_no()).get(i).getCover() != null)
                    rectangle.setFill(new ImagePattern(Main.management.getBooks(student.getRoll_no()).get(i).getCover()));

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
                    if(Main.management.getBooks(student.getRoll_no()).get(finalI).getCover() != null)
                        bookInformation.setCover(Main.management.getBooks(student.getRoll_no()).get(finalI).getCover());
                    bookInformation.setTitle(Main.management.getBooks(student.getRoll_no()).get(finalI).getTitle());
                    bookInformation.setAuthor(Main.management.getBooks(student.getRoll_no()).get(finalI).getAuthor());
                    bookInformation.setId(Main.management.getBooks(student.getRoll_no()).get(finalI).getId());
                    bookInformation.setShelf(Main.management.getBooks(student.getRoll_no()).get(finalI).getShelf());
                    bookInformation.setRelease_date(Main.management.getBooks(student.getRoll_no()).get(finalI).getRelease_date());
                    // set issue msg here issuer name roll no issued date
                    String msg;
                    if(Main.management.getBooks(student.getRoll_no()).get(finalI).isIssued())
                        msg = "Issued on "+Main.management.get_date(student.getRoll_no(),
                            Main.management.getBooks(student.getRoll_no()).get(finalI).getId());
                    else
                        msg = "Not Issued yet";
                    bookInformation.setIssue_msg(msg);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(anchorPane));
                    stage.show();
                });
                Text text = new Text(Main.management.getBooks(student.getRoll_no()).get(i).getTitle());
                text.setFont(Font.font("Comic Sans MS",24));
                TextFlow textFlow = new TextFlow();
                textFlow.getChildren().add(text);
                vBox.getChildren().addAll(rectangle,textFlow);
                issued_books.getChildren().add(vBox);
            }
            System.out.println(Main.management.getBooks(student.getRoll_no()).toString());
        }else {
            Text msg = new Text("not issued yet.");
            msg.setFont(Font.font("Comic Sans MS",24));
            issued_books.getChildren().add(msg);
        }
    }

    public void remove_student(ActionEvent actionEvent) {
        Button yes = new Button("YES");
        Button no = new Button("NO");
        no.setOnAction(e -> {
            Stage stage = (Stage)no.getScene().getWindow();
            stage.close();
        });
        yes.setOnAction(e -> {
            Main.studentList.remove(this.student.getRoll_no());
            Stage stage = (Stage)yes.getScene().getWindow();
            Stage stage1 = (Stage)this.issued_books.getScene().getWindow();
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