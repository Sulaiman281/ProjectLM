package Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Main;

public class ReturnBook {
    public TextField rollNo;
    public TextField bookID;
    public Text msg;

    public void return_book(ActionEvent actionEvent) {
        msg.setFill(Color.RED);
        if(rollNo.getText().length()== 0){
            msg.setText("Please Enter the roll No");
            return;
        }
        if(bookID.getText().length() == 0){
            msg.setText("Please Enter Book ID");
            return;
        }
        if(!Main.management.hasRollNo(rollNo.getText())){
            msg.setText("this roll number is not in the list  (invalid)");
            return;
        }
        if(!Main.management.getIssuer(bookID.getText()).equals(rollNo.getText())){
            msg.setText("this book is issued by "+Main.management.getIssuer(bookID.getText()));
            return;
        }
        if(!Main.management.hasBookID((bookID.getText()))){
            msg.setText("this book id is not in the list (invalid)");
            return;
        }
        if(Main.management.return_book(rollNo.getText(),bookID.getText())) {
            msg.setFill(Color.GREEN);
            msg.setText("Book has return successfully");
            Main.books.search(bookID.getText()).setIssued(false);
            Main.studentList.get_student(rollNo.getText()).setIssued(Main.studentList.get_student(rollNo.getText()).getIssued()-1);
        }

    }
}
