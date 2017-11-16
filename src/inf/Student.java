package inf;

import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
    private String name;
    private String sname;
    private int age;
    public int course;
    private HashMap<String,String> rating=new HashMap();
    public Student(String name, String sname, int age, int course){
        this.age=age;
        this.name=name;
        this.sname=sname;
    }
    public void setSubjectRating(String subject, String rating){
        this.rating.put(subject,rating);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSname() {
        return sname;
    }
    public String getRating(String subject){
        return  rating.get(subject);
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSname(String sname){
        this.sname=name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setRating(HashMap<String, String> rating) {
        this.rating = rating;
    }

    public HashMap<String, String> getRating() {
        return rating;
    }

    public int getCourse() {
        return course;
    }
}
