package sample;

import java.util.ArrayList;

public class StudentList {

    public ArrayList<Student> sList = new ArrayList<>();

    public boolean add_Student(Student student){
        boolean b = true;
        for(Student s : sList){
            if(s.getRoll_no().equals(student.getRoll_no())) {
                b = false;
                break;
            }
        }
        if(sList.size() == 0 || b == true){
            sList.add(student);
            return b;
        }
        return b;
    }

    public void remove(String roll_no){
        for(int i = 0; i< sList.size(); i++){
            if(sList.get(i).getRoll_no().equals(roll_no))
                sList.remove(i);
        }
    }

    public Student get_student(String _rNo){
        if(sList.size() <= 0) return null;

        for(int i = 0; i< sList.size(); i++){
            if(sList.get(i).getRoll_no().equals(_rNo))
                return sList.get(i);
        }
        return null;
    }

    public Student get(int i){
        return sList.get(i);
    }

    public int totalStudent(){
        return sList.size();
    }

    @Override
    public String toString() {
        return sList.toString();
    }
}
