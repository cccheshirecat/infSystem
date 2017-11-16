package inf;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
    private String name;
    private ArrayList<Student> students;
    private Student leader;
    public Group(String name, ArrayList<Student> students, Student leader){
        this.name=name;
        this.students=students;
        this.leader=leader;
    }

    public String getName() {
        return name;
    }

    public Student getLeader() {
        return leader;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setLeader(Student leader) {
        this.leader = leader;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public void deleteStudent(String name, String sname){
        String curntName;
        String curntSname;
        int count=0;
        for (Student student :
                students) {
            curntName=student.getName();
            curntSname=student.getSname();
            if (curntSname.equals(sname)){
                if (curntName.equals(name)){
                    students.remove(count);
                    break;
                }
            }
            count++;
        }
    }
    public void addStudent(Student student){
        students.add(student);
    }
    public int studentsNum(){
        return students.size();
    }
}
