package inf;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ControllerStudent {
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private FileInputStream fis;
    private ObjectInputStream ois;

    public ControllerStudent() throws IOException {
    }

    //проверка на пустоту файла
    public boolean isEmpty() throws IOException {
        fis = new FileInputStream("src/students.txt");
        if (fis.available()==0) {
            fis.close();
            return true;
        } else return false;
    }

    //получение студента по имени и фамилии и вывод данных о нем
    public void getStudent() throws IOException, ClassNotFoundException {
        if (isEmpty()) {
            System.out.println("Ошибка! Файл пуст. Занесисе сначала данные");
            addStudent();
        } else {
            System.out.println("Введите имя студента");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            System.out.println("Введите фамилию студента");
            String sname = scanner.nextLine();
            ArrayList<Student> students = getStudents();
            for (Student student :
                    students) {
                if (student.getName().equals(name) && student.getSname().equals(sname)) {
                    System.out.println(student.toString());
                }
            }
        }
    }

    //получение всех студентов
    public ArrayList<Student> getStudents() throws IOException, ClassNotFoundException {
        if (!isEmpty()) {
            fis = new FileInputStream("src/students.txt");
            ois = new ObjectInputStream(fis);
            ArrayList<Student> students = new ArrayList<>();
            while (fis.available() > 0) {
                students.add((Student) ois.readObject());
            }
            ois.close();
            fis.close();
            return students;
        } else return null;
    }
    private void findDuplicateStudent() throws IOException, ClassNotFoundException {
        ArrayList<Student> students=getStudents();
        for (int i = 0; i < students.size(); i++) {
            for (int j = i+1; j < students.size(); j++) {
                if (students.get(i).equals(students.get(j)))
                    students.remove(j);
            }
        }
        writeStudents(students);
    }
    //запись студента
    public void writeStudents(ArrayList<Student> students) throws IOException, ClassNotFoundException {
        fos = new FileOutputStream("src/students.txt");
        oos = new ObjectOutputStream(fos);
        if (students != null) {
            Iterator<Student> iterator = students.iterator();
            while (iterator.hasNext()) {
                oos.writeObject(iterator.next());
            }
        }
        oos.flush();
        oos.close();
        fos.close();
    }

    //добавление нового студента
    public void addStudent() throws IOException, ClassNotFoundException {
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
        Scanner scanner1 = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("Введите название предмета:");
            subject = scanner1.next();
            System.out.println("Введите оценку: ");
            mark = scanner1.nextInt();
            student.addRating(subject, mark);
            System.out.println("Студент добавлен");
        }
        ArrayList<Student> students = getStudents();
        students.add(student);
        writeStudents(students);
        findDuplicateStudent();
    }

    //получение индекса студента  по имени и фамилии
    public int getStudentIndex(String name, String sname) throws IOException, ClassNotFoundException {
        int index = -1;
        ArrayList<Student> students = getStudents();
        Iterator<Student> studentIterator = students.iterator();
        Student student;
        int i = 0;
        while (studentIterator.hasNext()) {
            student = studentIterator.next();
            if (student.getName().equals(name) && student.getSname().equals(sname)) {
                index = i;
                return index;
            }
            i++;
        }
        return index;
    }

    //изменение имени
    public void createName(String name, String sname) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя на которое ходите изменить:\n");
        String newName = scanner.nextLine();
        int index = getStudentIndex(name, sname);
        ArrayList<Student> students = getStudents();
        Student student = students.get(index);
        student.setName(newName);
        students.set(index, student);
        writeStudents(students);
        System.out.println("Имя студента изменено");
    }

    //фамилии
    public void createSname(String name, String sname) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию на которую ходите изменить:\n");
        String newSname = scanner.nextLine();
        int index = getStudentIndex(name, sname);
        ArrayList<Student> students = getStudents();
        Student student = students.get(index);
        student.setSname(newSname);
        students.set(index, student);
        writeStudents(students);
        System.out.println("Фамилия студента изменена");
    }

    //возраста
    public void createAge(String name, String sname) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите возраст на который ходите изменить:");
        int newAge = scanner.nextInt();
        int index = getStudentIndex(name, sname);
        ArrayList<Student> students = getStudents();
        Student student = students.get(index);
        student.setAge(newAge);
        students.set(index, student);
        writeStudents(students);
        System.out.println("Возраст студента изменен");
    }

    //курса
    public void createCourse(String name, String sname) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите курс на который ходите изменить:");
        int newAge = scanner.nextInt();
        int index = getStudentIndex(name, sname);
        ArrayList<Student> students = getStudents();
        Student student = students.get(index);
        student.setCourse(newAge);
        students.set(index, student);
        writeStudents(students);
        System.out.println("Курс студента изменен");
    }

    //оценкаи по опред предмету
    public void createMark(String name, String sname) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название предмета:");
        String nameL = scanner.next();
        System.out.println("Введите оценку на которую ходите изменить:");
        int newMark = scanner.nextInt();
        int index = getStudentIndex(name, sname);
        ArrayList<Student> students = getStudents();
        Student student = students.get(index);
        student.setMark(nameL, newMark);
        students.set(index, student);
        writeStudents(students);
        System.out.println("Оценка по предмету изменена");
    }

    //добавление нового предмета
    public void addRating(String name, String sname) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название предмета:");
        String nameL = scanner.next();
        System.out.println("Введите оценку:");
        int newMark = scanner.nextInt();
        int index = getStudentIndex(name, sname);
        ArrayList<Student> students = getStudents();
        Student student = students.get(index);
        student.addRating(nameL, newMark);
        students.set(index, student);
        writeStudents(students);
        System.out.println("Новый предмет добавлен");
    }

    //изменение студента по имени и фамилии
    public void setStudent() throws IOException, ClassNotFoundException {
        System.out.println("Введите\n1 - l для изменения имени студента\n2 - для изменения фамилии\n3 - для изменения возраста\n" +
                "4 -для изменения курса\n5 - для изменения оценки по названию предмета\n6 - для добавления нового предмета и его " +
                "оценки");
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        System.out.println("Введите имя и фамилию студента, данные которого хотите изменить");
        System.out.println("Имя:");
        String name = scanner.next();
        System.out.println("Фамилия:");
        String sname = scanner.next();
        switch (cases) {
            case 1:
                createName(name, sname);
                break;
            case 2:
                createSname(name, sname);
                break;
            case 3:
                createAge(name, sname);
                break;
            case 4:
                createCourse(name, sname);
                break;
            case 5:
                createMark(name, sname);
                break;
            case 6:
                addRating(name, sname);
                break;
        }
    }

    //получение всех студентов
    public void getAllStudents() throws IOException, ClassNotFoundException {
        if (isEmpty()) {
            System.out.println("Ошибка! Файл пуст. Занесисе сначала данные");
            addStudent();
        } else {
            fis = new FileInputStream("src/students.txt");
            ois = new ObjectInputStream(fis);
            Student student;
            while (fis.available() > 0) {
                student = (Student) ois.readObject();
                System.out.println(student.toString());
            }
            ois.close();
            fis.close();
        }
    }

    //основное меню
    public void menu() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int cases;
        int count;
        System.out.println("Введите:\n1 - если хотите получить данные студента по его имени и фамилии\n" +
                "2 - если хотите добваить нового студента \n" +
                "3 - если хотите изменить данные студента по его имени и фамилии" +
                "\n4 - если хотите получить данные всех студентов" +
                "\n5 - для выхода из программы");
        cases = scanner.nextInt();
        switch (cases) {
            case 1:
                getStudent();
                break;
            case 2:
                System.out.println("Введите сколько студентов вы хотите добавить:\n");
                count = scanner.nextInt();
                while (count != 0) {
                    addStudent();
                    count--;
                }
                oos.close();
                fos.close();
                break;
            case 3:
                setStudent();
                break;
            case 4:
                getAllStudents();
                break;
            case 5:
                System.exit(0);
            default:
                System.out.println("Число задано неверно");
                menu();
                break;
        }
    }
}