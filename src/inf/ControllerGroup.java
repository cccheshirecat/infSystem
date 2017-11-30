package inf;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerGroup {
    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private FileInputStream fis;
    private ObjectInputStream ois;

    public ControllerGroup() throws IOException {
    }

    //проверка на пустоту файла
    public boolean isEmpty() throws IOException {
        fis = new FileInputStream("groups.txt");
        if (fis.read() == 0) {
            fis.close();
            return true;
        } else return false;
    }
    //получение всех групп из файла
    private ArrayList<Group> getGroups() throws IOException, ClassNotFoundException {
        if (!isEmpty()) {
            fis = new FileInputStream("groups.txt");
            ois = new ObjectInputStream(fis);
            ArrayList<Group> groups = new ArrayList<>();
            while (fis.available() > 0) {
                groups.add((Group) ois.readObject());
            }
            return groups;
        } else return null;
    }
    //вывод студентов группы
    private void outStudent(Group group) throws IOException {
        fis = new FileInputStream("groups.txt");
        ois = new ObjectInputStream(fis);
        ArrayList<Student> students = group.getStudents();
        int i = 1;
        for (Student student :
                students) {
            System.out.println("" + i + " - " + student.toString());
        }
    }
    //получение студентов группы
    public void getGroupStudents() throws IOException, ClassNotFoundException {
        System.out.println("Введите имя группы: ");
        Scanner scanner = new Scanner(System.in);
        String nameG = scanner.next();
        ArrayList<Group> groups = getGroups();
        if (groups != null) {
            for (Group group :
                    groups) {
                if (group.getName().equals(nameG)) {
                    outStudent(group);
                } else {
                    System.out.println("Группа не найдена!");
                    menu();
            }
        }
    }else {
            System.out.println("ОШИБКА!!! Записи о группах отсутствуют!");
            menu();
        }

}
private Group addStudent(Group group){

}
//добавление студента в группу
    public void addStudent() throws IOException, ClassNotFoundException {
        System.out.println("Введите имя группы: ");
        Scanner scanner = new Scanner(System.in);
        String nameG = scanner.next();
        int i=0;
        int c=0;
        ArrayList<Group> groups = getGroups();
        if (groups != null) {
            for (Group group :
                    groups) {
                if (group.getName().equals(nameG)) {
                    group.
                    c++;
                }
                i++;
            }
        }else {
            System.out.println("ОШИБКА!!! Записи о группах отсутствуют!");
            menu();
        }
        if (c==1){
            System.out.println("Группа не найдена");
        }

    }


    private void writeGroup(ArrayList<Group> groups) throws IOException, ClassNotFoundException {
        fos = new FileOutputStream("groups.txt");
        oos = new ObjectOutputStream(fos);
        for (Group group :
                groups) {
            oos.writeObject(group);
            oos.flush();
        }
    }
    public void addGroup() throws IOException, ClassNotFoundException {
        ArrayList<Group> groups=getGroups();
        System.out.println("Сколько групп вы хотите добавить:");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        String gropuName;
        ArrayList<Student> students;
        int studentNum;
        String nameStudent;
        String snameStudent;
        int ageStudent;
        int courseS;
        int subjects;
        Group group;
        String leaderName;
        String leaderSname;
        int lesderAge;
        int lederCourse;
        String subjectName;
        Student leader;
        for (int i = 0; i < count; i++) {
            System.out.println("Введите имя группы:");
            gropuName = scanner.next();
            System.out.println("Введите количество студентов в группе:");
            studentNum = scanner.nextInt();
            students = new ArrayList<>(studentNum);
            for (int j = 0; j < studentNum; j++) {
                System.out.println("Введите имя студента");
                nameStudent = scanner.next();
                System.out.println("Введите фамилию студента");
                snameStudent = scanner.next();
                System.out.println("Введите возраст студента");
                ageStudent = scanner.nextInt();
                System.out.println("Введите курс студента");
                courseS = scanner.nextInt();
                Student student = new Student(nameStudent, snameStudent, ageStudent, courseS);
                System.out.print("Рейтинг студента\nВведите количество изучаемых преметов:\n");
                subjects = scanner.nextInt();

                int mark;
                for (int k = 0; k < subjects; k++) {
                    System.out.println("Введите название предмета:");
                    subjectName = scanner.next();
                    System.out.println("Введите оценку: ");
                    mark = scanner.nextInt();
                    student.addRating(subjectName, mark);
                }
                students.add(student);
            }
            System.out.println("Введите имя старосты");
            leaderName = scanner.next();
            System.out.println("Введите фамилию старосты");
            leaderSname = scanner.next();
            System.out.println("Введите возраст старосты");
            lesderAge = scanner.nextInt();
            System.out.println("Введите курс старосты");
            lederCourse = scanner.nextInt();
            leader = new Student(leaderName, leaderSname, lesderAge, lederCourse);
            System.out.print("Рейтинг студента\nВведите количество изучаемых преметов старостой:\n");
            subjects = scanner.nextInt();
            int mark;
            for (int k = 0; k < subjects; k++) {
                System.out.println("Введите название предмета:");
                subjectName = scanner.next();
                System.out.println("Введите оценку: ");
                mark = scanner.nextInt();
                leader.addRating(subjectName, mark);
            }
            students.add(leader);
            group = new Group(gropuName, students, leader);
            groups.add(group);
        }
        writeGroup(groups);
    }

    //основное меню
    public void menu() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int cases;
        int count;
        System.out.println("Введите:\n1 - если хотите получить данные о всех студентах группы\n" +
                "2 - если хотите добваить нового студента в группу\n" +
                "3 - если хотите получить данные старосты группы" +
                "\n4 - если хотите получить информацию о группе(студенты, староста, имя группы)" +
                "\n5 - если хотите изменить имя группы" +
                "\n6 - если хотите изменить старосту группы" +
                "\n7 - если хотите добавить новую группу" +
                "\n8 - для выхода из программы");
        cases = scanner.nextInt();
        switch (cases) {
            case 1:
                getGroupStudents();
                ois.close();
                fis.close();
                break;
            case 2:
                System.out.println("Введите сколько студентов вы хотите добавить:\n");
                count = scanner.nextInt();
                while (count != 0) {

                    count--;
                }
                oos.close();
                fos.close();
                break;
            case 3:

                break;
            case 4:

                break;
            case 5:
            case 6:

            case 7:
                addGroup();
                oos.close();
                fos.close();
                break;
            case 8:
            System.exit(0);
            default:
                System.out.println("Число задано неверно");
                menu();
                break;
        }
    }
}
