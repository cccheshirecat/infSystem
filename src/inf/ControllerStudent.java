package inf;

import java.io.*;
import java.util.Scanner;

public class ControllerStudent {
    private FileInputStream fis;
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ControllerStudent(FileInputStream fis, FileOutputStream fos) throws IOException {
        this.fis = fis;
        ois = new ObjectInputStream(fis);
        this.fos = fos;
        oos = new ObjectOutputStream(fos);
    }

    public void menu() throws IOException, ClassNotFoundException {
        System.out.println("Введите:\n 1 - если хотите получить данные студента по его имени и фамилии\n" +
                "2 - если хотите добваить нового студента \n" +
                "3 - если хотите изменить данные студента по его имени и фамилии\n");
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        switch (cases) {
            case 1:
                getStudent();
            case 2:
                addStudent();
            case 3:
                setStudent();
            default:
                System.out.println("Число задано неверно");
        }
    }

    public void getStudent() throws IOException, ClassNotFoundException {
        Student student;
        System.out.println("Введите имя студента");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Введите фамилию студента");
        String sname = scanner.nextLine();
        while (fis.available() > 0) {
            student = (Student) ois.readObject();
            if (student.getName().equals(name) && student.getSname().equals(sname)) {
                System.out.println(student.toString());
            }
        }
    }

    public void addStudent() throws IOException {
        System.out.println("Введите имя студента");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Введите фамилию студента");
        String sname = scanner.nextLine();
        System.out.println("Введите возраст студента");
        int age = scanner.nextInt();
        System.out.println("Введите курс студента");
        int coursw = scanner.nextInt();
        Student student = new Student(name, sname, age, coursw);
        System.out.print("Рейтинг студента\nВведите количество изучаемых преметов:\n");
        int count = scanner.nextInt();
        String subject;
        int mark;
        for (int i = 0; i < count; i++) {
            System.out.println("Введите название предмета: ");
            subject = scanner.nextLine();
            System.out.println("\nВведите оценку(отл, хор, уд, неуд): ");
            mark = scanner.nextInt();
            student.addRating(subject, mark);
        }
        oos.writeObject(student);
    }
    public void createName(String name, String sname){

    }
    public void createSname(String name,String sname){

    }
    public void createAge(String name, String sname){

    }
    public void createCourse(String name, String sname){

    }
    public void createValue(String name, String sname){

    }
    public void addRating(String name, String sname){

    }
    public void setStudent() {
        System.out.println("Введите\n1 - l для изменения имени студента\n2 - для изменения фамилии\n3 - для изменения возраста\n" +
                "4 -для изменения курса\n5 - для изменения оценки по названию предмета\n6 - для добавления нового предмета и его " +
                "оценки");
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        System.out.println("Введите имя и фамилию студента, данные которого хотите изменить");
        System.out.println("Имя:");
        String name=scanner.nextLine();
        System.out.println("Фамилия:");
        String sname=scanner.nextLine();
        switch (cases){
            case 1: createName(name, sname);
            case 2: createSname(name,sname);
            case 3: createAge(name,sname);
            case 4: createCourse(name, sname);
            case 5: createValue(name, sname);
            case 6: addRating(name,sname);
        }
    }

}
