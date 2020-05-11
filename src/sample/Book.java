package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Book {
    private String title;
    private String author;
    private String release_date;
    private String id;
    private String shelf;
    private boolean issued;
    private Image cover;

    public Book(String _title, String _author, String _release_date,String _id){
        setTitle(_title);
        setAuthor(_author);
        setRelease_date(_release_date);
        setId(_id);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getId() {
        return id;
    }

    public String getShelf() {
        return shelf;
    }

    public boolean isIssued() {
        return issued;
    }

    public Image getCover() {
        return cover;
    }

    @Override
    public String toString() {
        return getId()+","+getShelf()+","+getTitle()+","+getAuthor()+","+getRelease_date()+","+isIssued()+"\n";
    }
}
