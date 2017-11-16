package inf;

import java.io.*;
import java.util.Scanner;

public class View {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ControllerStudent controllerStudent=new ControllerStudent();
        ControllerGroup controllerGroup=new ControllerGroup();
        System.out.println("Введите: \n1 - для работы с информацией о студентах\n 2 - для работы с информацией о группах");
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        switch (cases){
            case 1:{
                System.out.println("Введите:\n 1 - если хотите получить данные студента по его имени и фамилии\n" +
                    "2 - если хотите добваить нового студента \n" +
                    "3 - если хотите изменить данные студента по его имени и фамилии\n");
                cases = scanner.nextInt();
                switch (cases) {
                    case 1:
                        controllerStudent.getStudent();
                    case 2:
                        controllerStudent.addStudent();
                    case 3:
                        controllerStudent.setStudent();
                    default:
                        System.out.println("Число задано неверно");
                }}
            case 2: controllerGroup.menu();
        }

    }
}
