package inf;

import java.io.*;
import java.util.Scanner;

public class View {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ControllerStudent controllerStudent=new ControllerStudent();
        ControllerGroup controllerGroup=new ControllerGroup();
        Scanner scanner=new Scanner(System.in);
        int cases=scanner.nextInt();
        while(true) {
            System.out.println("Введите: \n1 - для работы с информацией о студентах\n2 - для работы с информацией о группах" +
                    "\n3 - для выхода");
            switch (cases) {
                case 1: {
                    System.out.println("Введите:\n1 - если хотите получить данные студента по его имени и фамилии\n" +
                            "2 - если хотите добваить нового студента \n" +
                            "3 - если хотите изменить данные студента по его имени и фамилии" +
                            "\n4 - для выхода из программы");
                    cases = scanner.nextInt();
                    switch (cases) {
                        case 1:
                            controllerStudent.getStudent();
                            break;
                        case 2:
                            controllerStudent.addStudent();
                            break;
                        case 3:
                            controllerStudent.setStudent();
                            break;
                        case 4: System.exit(0);
                        default:
                            System.out.println("Число задано неверно");
                            break;
                    }
                }
                case 2:
                    controllerGroup.menu();
                case 3:
                    System.exit(0);
            }
        }

    }
}
