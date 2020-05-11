package sample;

import java.util.ArrayList;

public class Books {

    private ArrayList<Book> books = new ArrayList<>();

    public boolean add_book(Book book){
        boolean c = true;
        if(books.size() > 0)
        for(Book b : books){
            if(b.getId().equals(book.getId())){
                c = false;
                break;
            }
        }
        if(books.size() == 0 || c == true) {
            books.add(book);
        }

        return c;
    }
    public Book search(String _id){
        if(books.size() == 0) return null;

        for(int i = 0; i< books.size(); i++){
            if(books.get(i).getId().equals(_id))
                return books.get(i);
        }
        return null;
    }
    public int size(){
        return books.size();
    }
    public Book get(int i){
        return books.get(i);
    }
    public void remove(String _id){
        if(books.size() == 0) return;

        for(int i = 0; i<books.size(); i++){
            if(books.get(i).getId().equals(_id))
                books.remove(i);
        }
    }

    public int total_books(){
        return books.size();
    }

    @Override
    public String toString() {
        return books.toString();
    }
}
