package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Book;
import sample.Main;
import sample.Student;

public class IssueBook {
    public TextField rollNo;
    public TextField bookID;
    public Text msg;

    public void issue_book(ActionEvent actionEvent) {
        msg.setFill(Color.RED);

        if(rollNo.getText().length() == 0 || bookID.getText().length() == 0) {
            msg.setText("empty fields");
            return;
        }

        Student st = Main.studentList.get_student(rollNo.getText());
        Book b = Main.books.search(bookID.getText());
        if(st == null){
            msg.setText("No Member Found with this roll No (Invalid Roll No)");
            return;
        }
        if(b == null){
            msg.setText("there is not book with this ID (Invalid Book ID)");
            return;
        }
        if(st.limit()){
            msg.setText(st.getFullName() + " has already issued 3 books he can not issue another book");
            return;
        }
        if(b.isIssued()){
            msg.setText("This Book is already issued by "+Main.studentList.get_student(Main.management.getIssuer(b.getId())).getFullName());
            return;
        }
        if(Main.studentList.get_student(rollNo.getText()) == null){
            msg.setText("Member dose not exits (invalid roll no)");
            return;
        }
        if(Main.books.search(bookID.getText()) == null){
            msg.setText("Book does not exits ( invalid book id)");
            return;
        }
        msg.setFill(Color.GREEN);
        Main.studentList.get_student(rollNo.getText()).setIssued(Main.studentList.get_student(rollNo.getText()).getIssued()+1);
        Main.books.search(bookID.getText()).setIssued(true);
        Main.management.issue_book(rollNo.getText(),bookID.getText());
        msg.setText("successfully issued the book");
    }
}