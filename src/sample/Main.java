package sample;

import FileHandling.FileHandling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {

    public static StudentList studentList = new StudentList();
    public static Books books = new Books();
    private static Stage window;
    public static Management management = new Management();

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        this.loadBooks();
        this.loadStudent();
        this.loadIssued();
        this.loadProfiles();
        this.loadCover();
        System.out.println(studentList.toString());
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/MainGUI.fxml"));
        window.initStyle(StageStyle.TRANSPARENT);
        window.setScene(new Scene(root, 1000, 600));
        //window.getIcons().add(Icon of application logo);
        window.setOnCloseRequest(e -> {
            close();
        });
        window.show();
    }


    public void saveBooks(){
        if(books.size() == 0) return;
        FileHandling fileHandling = new FileHandling("Books","C:\\Users\\Public\\LM\\Books\\","csv");

        String books ="";
        for(int i = 0; i<this.books.size(); i++){
            books += this.books.get(i).toString();
        }

        fileHandling.write("Book ID,Shelf No, Title of the Book, author, release date, Is Issued?\n"+books);

    }
    public void loadProfiles(){
        if(studentList.totalStudent() == 0) return;
        for(int i = 0; i< studentList.totalStudent(); i++){
            FileHandling fileHandling = new FileHandling();
            Image img = fileHandling.loadImage("C:\\Users\\Public\\LM\\Profiles\\"+studentList.get(i).getRoll_no()+".jpg");
            if(img == null)
                img = fileHandling.loadImage("C:\\Users\\Public\\LM\\Profiles\\"+studentList.get(i).getRoll_no()+".png");
            if(img == null) return;
            studentList.get(i).setProfile(img);
        }
    }
    public void loadCover(){
        if(books.size() == 0) return;
        for(int i = 0; i< books.size(); i++){
            FileHandling fileHandling = new FileHandling();
            Image img = fileHandling.loadImage("C:\\Users\\Public\\LM\\Cover\\"+books.get(i).getId()+".jpg");
            if(img == null)
                img = fileHandling.loadImage("C:\\Users\\Public\\LM\\Cover\\"+books.get(i).getId()+".png");
            if(img == null) return;

            books.get(i).setCover(img);
        }
    }

    public void saveStudent(){
        if(studentList.totalStudent() == 0) return;
        FileHandling fileHandling = new FileHandling("Students","C:\\Users\\Public\\LM\\Students\\","csv");

        String stud = "";

        for(int s = 0; s < this.studentList.totalStudent(); s++){
            stud += this.studentList.get(s).toString();
        }

        fileHandling.write("Roll No,Full Name,Age,Address,Gender,Birthday,Book Issued\n"+stud);

    }

    public void saveIssued(){
        if(management.size() == 0) return;
        FileHandling fileHandling = new FileHandling("Issued","C:\\Users\\Public\\LM\\","csv");
        String issued = "";
        for(int i = 0; i< management.size(); i++){
            issued += management.get(i).toString();
        }
        fileHandling.write(("RollNo,BookID\n"+issued));
    }

    public void loadIssued(){
        FileHandling fileHandling = new FileHandling("Issued","C:\\Users\\Public\\LM\\","csv");
        if(fileHandling.hasNext()) fileHandling.getLine();
        while(fileHandling.hasNext()){
            String issued[] = fileHandling.getLine().split(",");
            management.issue_book(issued[0],issued[1]);

        }
    }

    public void loadBooks(){
        FileHandling fileHandling = new FileHandling("Books","C:\\Users\\Public\\LM\\Books\\","csv");
        if(fileHandling.hasNext()) fileHandling.getLine();
        while(fileHandling.hasNext()){
            String book[] = fileHandling.getLine().split(",");
            Book b = new Book(book[2],book[3],book[4],book[0]);
            b.setShelf(book[1]);
            b.setIssued(Boolean.parseBoolean(book[5]));
            books.add_book(b);
        }
    }
    public void loadStudent(){
        FileHandling fileHandling = new FileHandling("Students","C:\\Users\\Public\\LM\\Students\\","csv");
        if(fileHandling.hasNext()) fileHandling.getLine();
        while(fileHandling.hasNext()){
            String student[] = fileHandling.getLine().split(",");
            String name[] = student[1].split(" ");
            Student st = new Student(name[0],name[1],student[0]);
            st.setAge(Integer.parseInt(student[2]));
            st.setAddress(student[3]);
            st.setGender(student[4]);
            String[] bd = student[5].split("/");
            st.setBirthday(bd[1],bd[0],bd[2]);
            st.setIssued(Integer.parseInt(student[6]));
            studentList.add_Student(st);
        }
    }

    public static void close(){
        new Main().saveBooks();
        new Main().saveStudent();
        new Main().saveIssued();
        window.close();
    }

    public String inputDialog(String label){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setWidth(300);
        window.setHeight(175);
        window.initStyle(StageStyle.UTILITY);
        VBox root = new VBox();
        root.styleProperty().set("-fx-background-color: #001f6f");

        Text text = new Text(label);
        text.setFont(Font.font("Comic Sans MS",20));

        TextField input = new TextField();
        input.setPromptText("value");

        input.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER){
                window.close();
            }
        });

        root.getChildren().addAll(text,input);
        root.setAlignment(Pos.CENTER);

        // Set up the JavaFX button controls and listeners and the text fields
        // for the login info. The button listeners set the login values

        window.setScene(new Scene(root, 300, 175));
        window.showAndWait();
        return input.getText();
    }
    public void msgDialog(String msg){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.initStyle(StageStyle.TRANSPARENT);
        window.setWidth(300);
        window.setHeight(175);
        window.initStyle(StageStyle.UTILITY);
        VBox root = new VBox();
        root.styleProperty().set("-fx-background-color: #001f6f");

        Text text = new Text(msg);
        text.setFont(Font.font("Comic Sans MS",20));

        Button close = new Button("close");
        close.setFont(Font.font("Comic Sans MS",20));
        close.setOnAction(e -> {
            window.close();
        });

        root.getChildren().addAll(text,close);
        root.setAlignment(Pos.CENTER);

        // Set up the JavaFX button controls and listeners and the text fields
        // for the login info. The button listeners set the login values

        window.setScene(new Scene(root, 300, 175));
        window.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
