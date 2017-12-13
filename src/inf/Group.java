package inf;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Group implements Serializable {
    private String name;
    private ArrayList<Student> students;
    private Student leader;
    public Group(String name, ArrayList<Student> students, Student leader){
        this.name=name;
        this.students=students;
        this.leader=leader;
        students.add(leader);
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
        students.remove(leader);
        this.leader = leader;
        students.add(leader);
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
    public int getStudentNum(){
       return students.size();
    }
    public void addStudent(Student student){
        students.add(student);
    }
    @Override
    public String toString() {
        StringBuffer sb=new StringBuffer("name Group= "+name+" ");
        for (Student student :
                students) {
            sb.append(student.toString()+" ");
        }
      return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        int num=0;
        Group group=(Group) obj;
        if (name.equals(group.name)){
            if (leader.equals(group.leader)){
                if (students.size()==group.getStudentNum()){
                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).equals(group.students.get(i))){
                            num++;
                        }
                    }
                    if (num==students.size()){
                        return true;
                    } else return false;
                } else  return false;
            } else return false;
        } else return false;
    }
}
