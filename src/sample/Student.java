package sample;

import javafx.scene.image.Image;

import java.util.Date;

public class Student {

    private String first_name;
    private String last_name;
    private int age;
    private String bd_month;
    private String bd_year;
    private String bd_day;
    private String roll_no; // optional
    private String address;
    private Image profile;
    private String gender;

    private int maxBooks = 3;

    private int issued;

    public Student(){

    }

    public Student(String _fname , String _lname, String _rollNo){
        setFirst_name(_fname);
        setLast_name(_lname);
        setRoll_no(_rollNo);
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAge() {
        if(bd_day.length() == 0 || bd_month.length() == 0 || bd_year.length() == 0) return;

        Date date = new Date();
        this.age = (date.getYear()+1900) -Integer.parseInt(bd_year);
    }

    public void setAge(int _age){
        this.age = _age;
    }

    public void setBirthday(String _day, String _month, String _year){
        this.bd_day = _day;
        this.bd_month = _month;
        this.bd_year = _year;
    }


    public boolean limit(){
        return maxBooks == issued;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfile(Image profile) {
        this.profile = profile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFullName(){
        return getFirst_name()+" "+last_name;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public Image getProfile() {
        return profile;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String get_birthday(){
        return bd_month+"/"+bd_day+"/"+bd_year;
    }

    public String getGender() {
        return gender;
    }

    public int getIssued() {
        return issued;
    }

    @Override
    public String toString() {
        return getRoll_no()+","+getFullName()+","+getAge()+","+getAddress()+","
                +getGender()+","+get_birthday()+","+String.valueOf(issued)+"\n";
    }
}
