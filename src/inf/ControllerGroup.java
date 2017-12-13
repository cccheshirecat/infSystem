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
        fis = new FileInputStream("src/groups.txt");
        if (fis.available()==0) {
            fis.close();
            return true;
        } else return false;
    }

    //получение всех групп из файла
    private ArrayList<Group> getGroups() throws IOException, ClassNotFoundException {
        if (!isEmpty()) {
            fis = new FileInputStream("src/groups.txt");
            ois = new ObjectInputStream(fis);
            ArrayList<Group> groups = new ArrayList<>();
            while (fis.available() > 0) {
                groups.add((Group) ois.readObject());
            }
            ois.close();
            fis.close();
            return groups;
        } else return null;
    }

    //вывод студентов группы
    private void outStudent(Group group) throws IOException {
        fis = new FileInputStream("groups.txt");
        ois = new ObjectInputStream(fis);
        ArrayList<Student> students = group.getStudents();
        Student leader = group.getLeader();
        int i = 1;
        for (Student student :
                students) {
            System.out.println("" + i + " - " + student.toString());
        }
    }

    private Group findGroup(String name) {
        try {

            ArrayList<Group> groups = getGroups();
            if (groups != null) {
                for (Group group :
                        groups) {
                    if (group.getName().equals(name)) {
                        return group;
                    }
                }
            } else {
                System.out.println("ОШИБКА!!! Записи о группах отсутствуют!");
                menu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Student getStudentForWtite() {
        String nameStudent;
        Scanner scanner = new Scanner(System.in);
        String snameStudent;
        int ageStudent;
        int courseS;
        int subjects;
        String subjectName;
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
        return student;
    }

    private void writeGroup(ArrayList<Group> groups) throws IOException, ClassNotFoundException {
        fos = new FileOutputStream("src/groups.txt");
        oos = new ObjectOutputStream(fos);
        for (Group group :
                groups) {
            oos.writeObject(group);
            oos.flush();
        }
        oos.close();
        fos.close();
    }

    private void findDuplicateStudent() throws IOException, ClassNotFoundException {
        ArrayList<Group> groups = getGroups();
        ArrayList<Student> students;
        Student tempStudent;
        for (Group group : groups
                ) {
            students = group.getStudents();
            for (int i = 0; i < students.size(); i++) {
                tempStudent = students.get(i);
                for (int j = i + 1; j < students.size(); j++) {
                    if (tempStudent.equals(students.get(j))) {
                        students.remove(j);
                    }
                }
            }
        }
        writeGroup(groups);
    }

    private void findDuplicateGroup() {
        try {
            ArrayList<Group> groups = getGroups();
            for (int i = 0; i < groups.size(); i++) {
                for (int j = i + 1; j < groups.size(); j++) {
                    if (groups.get(i).equals(groups.get(j)))
                        groups.remove(j);
                }
            }
            writeGroup(groups);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //получение студентов группы
    public void getGroupStudents() throws IOException, ClassNotFoundException {
        System.out.println("Введите имя группы: ");
        Scanner scanner = new Scanner(System.in);
        String nameG = scanner.next();
        Group group = findGroup(nameG);
        if (group != null) {
            outStudent(group);
        } else {
            System.out.println("Группа не найдена!");
            menu();
        }
    }

    //добавление студента в группу
    public void addStudent() throws IOException, ClassNotFoundException {
        System.out.println("Введите имя группы: ");
        Scanner scanner = new Scanner(System.in);
        String nameG = scanner.next();
        int c = 0;
        ArrayList<Group> groups = getGroups();
        if (groups != null) {
            for (Group group :
                    groups) {
                if (group.getName().equals(nameG)) {
                    group.addStudent(getStudentForWtite());
                    c++;
                    System.out.println("Студент добавлен");
                }
            }
        } else {
            System.out.println("ОШИБКА!!! Записи о группах отсутствуют!");
            menu();
        }
        if (c != 1) {
            System.out.println("Группа не найдена");
        } else
            writeGroup(groups);
        findDuplicateStudent();
        findDuplicateGroup();
    }

    public void addGroup() throws IOException, ClassNotFoundException {
        ArrayList<Group> groups = getGroups();
        System.out.println("Сколько групп вы хотите добавить:");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        String gropuName;
        ArrayList<Student> students;
        int studentNum;
        Group group;
        Student leader;
        for (int i = 0; i < count; i++) {
            System.out.println("Введите имя группы:");
            gropuName = scanner.next();
            System.out.println("Введите количество студентов в группе:");
            studentNum = scanner.nextInt();
            students = new ArrayList<>(studentNum);
            for (int j = 0; j < studentNum; j++) {
                students.add(getStudentForWtite());
            }
            System.out.printf("Староста:");
            leader = getStudentForWtite();
            students.add(leader);
            group = new Group(gropuName, students, leader);
            if (groups != null) {
                groups.add(group);
                System.out.println("Группа добавлена");
            } else {
                groups = new ArrayList<>();
                groups.add(group);
                System.out.println("Группа добавлена");
            }
        }
        writeGroup(groups);
        findDuplicateStudent();
        findDuplicateGroup();
    }

    public void getGroupLeader() throws IOException, ClassNotFoundException {
        System.out.println("Введите имя группы: ");
        Scanner scanner = new Scanner(System.in);
        String nameG = scanner.next();
        int c = 0;
        Student leader;
        ArrayList<Group> groups = getGroups();
        if (groups != null) {
            for (Group group :
                    groups) {
                if (group.getName().equals(nameG)) {
                    leader = group.getLeader();
                    System.out.println(leader.toString());
                    c++;
                }
            }
        } else {
            System.out.println("ОШИБКА!!! Записи о группах отсутствуют!");
            menu();
        }
        if (c != 1) {
            System.out.println("Группа не найдена");
        }
    }

    public void getGroupInf() throws IOException, ClassNotFoundException {
        ArrayList<Group> groups = getGroups();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя группы");
        String name = scanner.next();
        for (Group group :
                groups) {
            if (group.getName().equals(name)) {
                System.out.println(group.toString());
            }
        }
    }

    public void setGroupName() throws IOException, ClassNotFoundException {
        ArrayList<Group> groups = getGroups();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя группы");
        String name = scanner.next();
        System.out.println("Введите новое имя группы");
        String newName = scanner.next();
        for (Group group :
                groups) {
            if (group.getName().equals(name)) {
                group.setName(newName);
                System.out.println("Имя изменено");
            }
        }
        writeGroup(groups);
    }

    public void setLeader() throws IOException, ClassNotFoundException {
        ArrayList<Group> groups = getGroups();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя группы");
        String name = scanner.next();
        System.out.println("Введите новые данные старосты:");
        Student leader = getStudentForWtite();
        for (Group group :
                groups) {
            if (group.getName().equals(name)) {
                group.setLeader(leader);
                System.out.println("Новый лидер установлен");
            }
        }
        writeGroup(groups);
    }

    public void getGroupsName() {
        try {
            ArrayList<Group> groups = getGroups();
            for (Group group :
                    groups) {
                System.out.print("" + group.getName() + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void getAllGroupsInf() {
        try {
            int i = 1;
            ArrayList<Group> groups = getGroups();
            for (Group group :
                    groups) {
                System.out.println("" + i + " " + group.toString());
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getStudentNum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя группы");
        String groupName = scanner.next();
        Group group = findGroup(groupName);
        System.out.println("Количесто студентов в группе: " + group.getStudentNum());
    }

    public void transferStudent() {
        try {
            ArrayList<Student> students = new ArrayList<>();
            ArrayList<Group> groups = getGroups();
            for (Group group : groups
                    ) {
                students.addAll(group.getStudents());
            }
            ControllerStudent controllerStudent = new ControllerStudent();
            controllerStudent.writeStudents(students);
            System.out.println("Студенты добавлены в бд студентов");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void deleteStudent() {
        try {
            Scanner scanner = new Scanner(System.in);
            ArrayList<Group> groups = getGroups();
            System.out.println("Введите имя группы");
            int count = 0;
            int i = 0;
            int j = 0;
            String nameG = scanner.next();
            String name;
            String sname;
            ArrayList<Student> students;
            for (Group group : groups) {
                if (group.getName().equals(nameG)) {
                    count++;
                    students = group.getStudents();
                    System.out.println("Введите имя студента");
                    name = scanner.next();
                    System.out.println("Введите фамилию студента");
                    sname = scanner.next();
                    for (Student student : students) {
                        if (student.getName().equals(name) && student.getSname().equals(sname)) {
                            students.remove(i);
                            group.setStudents(students);
                            groups.set(j, group);
                            writeGroup(groups);
                            System.out.println("Студент удален");
                            break;
                        }
                    }
                    i++;
                }
                j++;
            }
            if (count == 0) System.out.println("Неверное имя! Группа не найдена!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                "\n8 - если хотите получить все названия групп" +
                "\n9 - если хотите получить полную информацию о всех группах" +
                "\n10 - если хотите получить количество студентов в группе" +
                "\n11 - если хотите добавить сех студентов из групп в файл со списком студентов" +
                "\n12 - если хотите удалить студента из группы" +
                "\n13 - для выхода из программы");
        cases = scanner.nextInt();
        switch (cases) {
            case 1:
                getGroupStudents();
                break;
            case 2:
                System.out.println("Введите сколько студентов вы хотите добавить:\n");
                count = scanner.nextInt();
                while (count != 0) {
                    addStudent();
                    count--;
                }
                break;
            case 3:
                getGroupLeader();
                break;
            case 4:
                getGroupInf();
                break;
            case 5:
                setGroupName();
                break;
            case 6:
                setLeader();
                break;
            case 7:
                addGroup();
                break;
            case 8:
                getGroupsName();
                break;
            case 9:
                getAllGroupsInf();
                break;
            case 10:
                getStudentNum();
                break;
            case 11:
                transferStudent();
                break;
            case 12:
                deleteStudent();
                break;
            case 13:
                System.exit(0);
            default:
                System.out.println("Число задано неверно");
                menu();
                break;
        }
    }
}
