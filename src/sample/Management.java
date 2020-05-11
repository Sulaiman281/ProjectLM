package sample;

import java.util.ArrayList;
import java.util.Date;

public class Management {

    private class Issue{
        private String rollNo;
        private String bookID;
        private String issueDate;

        public Issue(String _rn, String _id){
            setRollNo(_rn);
            setBookID(_id);
        }

        public void setRollNo(String rollNo) {
            this.rollNo = rollNo;
        }

        public void setBookID(String bookID) {
            this.bookID = bookID;
        }

        public void setIssueDate(String issueDate) {
            this.issueDate = issueDate;
        }

        public String getRollNo() {
            return rollNo;
        }

        public String getBookID() {
            return bookID;
        }

        public String getIssueDate() {
            return issueDate;
        }

        @Override
        public String toString() {
            return getRollNo()+","+getBookID()+","+getIssueDate()+"\n";
        }
    }

    public ArrayList<Issue> issues = new ArrayList<>();

    public void issue_book(String _rn, String _id){
        Issue book = new Issue(_rn,_id);
        Date today = new Date();
        int date = today.getDate();
        int month = today.getMonth()+1;
        int year = today.getYear()+1900;
        book.setIssueDate(month+"/"+date+"/"+year);
        issues.add(book);
    }

    public boolean hasRollNo(String _rn){
        boolean b = false;
        for(int i = 0; i< size(); i++){
            if(issues.get(i).getRollNo().equals(_rn)){
                b = true;
                break;
            }
        }
        return b;
    }

    public boolean hasBookID(String _id){
        boolean b = false;
        for(int i = 0; i< size(); i++){
            if(issues.get(i).getBookID().equals(_id)){
                b = true;
                break;
            }
        }
        return b;
    }

    public String get_date(String _rn, String _id){
        String s = null;
        for(int i = 0; i< size(); i++){
            if(issues.get(i).getRollNo().equals(_rn) && issues.get(i).getBookID().equals(_id)){
                s = issues.get(i).getIssueDate();
                break;
            }
        }
        return s;
    }

    public boolean return_book(String _rn, String _id){
        boolean b = false;
        for(int i = 0; i< size(); i++){
            if(issues.get(i).getRollNo().equals(_rn) && issues.get(i).getBookID().equals(_id)){
                issues.remove(i);
                System.out.println("returned the book");
                b = true;
            }
        }
        return b;
    }

    public String get(int i){
        return issues.get(i).toString();
    }

    public String getIssuer(String _bookID){
        String rn = null;
        for(int i = 0; i< issues.size(); i++){
            if(issues.get(i).getBookID().equals(_bookID)){
                rn = issues.get(i).getRollNo();
                break;
            }
        }
        return rn;
    }
    public Books getBooks(String _rollNo){
        Books book = new Books();
        for(int i = 0; i< size(); i++){
            if(this.issues.get(i).getRollNo().equals(_rollNo)){
                book.add_book(Main.books.search(this.issues.get(i).getBookID()));
            }
        }
        return book;
    }

    public int size(){
        return issues.size();
    }

    @Override
    public String toString() {
        return issues.toString();
    }
}
